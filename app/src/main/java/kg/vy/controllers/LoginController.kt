package kg.vy.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import kg.vy.R
import kg.vy.utils.Prefs
import org.json.JSONException


class LoginController(private val context: Activity) {

    private var callbackManager: CallbackManager? = null
    private var twitterAuthClient: TwitterAuthClient
    private var googleSignInClient: GoogleSignInClient
    private var mFirebaseAuth: FirebaseAuth? = null
    private val listener = context as AuthListener

    private var loginCode = 0;

    interface AuthListener {
        fun onAuthSuccess()
        fun onAuthError(err: String)
        fun onAuthCancel()
    }

    companion object {
        private const val RC_SIGN_IN = 9001

        fun isLogin(context: Context): Boolean {
            return Prefs.getBooleanDef(context, Prefs.IS_LOGIN, false)
        }
    }

    init {
        mFirebaseAuth = FirebaseAuth.getInstance()

        callbackManager = CallbackManager.Factory.create()
        FacebookSdk.sdkInitialize(context.applicationContext)

        val config = TwitterConfig.Builder(context)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(TwitterAuthConfig(context.getString(R.string.twitter_consumer_key),
                context.getString(R.string.twitter_consumer_secret)))
            .debug(false)
            .build()
        Twitter.initialize(config)
        twitterAuthClient = TwitterAuthClient()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.def_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun authSuccess(uid: String) {
        Prefs.putString(context, Prefs.UID, uid)
        FireStoreController.checkUser(context, uid) {
            listener.onAuthSuccess()
        }
    }

    fun googleLogin() {
        val signInIntent = googleSignInClient.signInIntent
        context.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mFirebaseAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LoginController", "signInWithCredential:success")

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginController", "signInWithCredential:failure", task.exception)

                }
            }
    }

    fun twitterLogin() {
        twitterAuthClient.authorize(context, object: Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                authSuccess(result!!.data.userId.toString())
                // Toast.makeText(context, "Twitter Login Success", Toast.LENGTH_LONG).show()
            }

            override fun failure(exception: TwitterException?) {
                listener.onAuthError("Twitter Login Failure")
                // Toast.makeText(context, "Twitter Login Failure", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun signInToFirebaseWithTwitterSession(session: TwitterSession) {
        val credential = TwitterAuthProvider.getCredential(
            session.authToken.token,
            session.authToken.secret
        )
        mFirebaseAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(context, OnCompleteListener<AuthResult?> { task ->
                    Toast.makeText(context, "Signed in firebase twitter successful", Toast.LENGTH_LONG).show()
                    if (!task.isSuccessful) {
                        Toast.makeText(context, "Auth firebase twitter failed", Toast.LENGTH_LONG).show()
                    }
                })
    }

    fun facebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(context, listOf("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager, object:
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                // Toast.makeText(context, "FacebookLogin onSuccess", Toast.LENGTH_LONG).show()

                // handleFacebookAccessToken(result!!.accessToken)
                // Getting the user information

                authSuccess(result!!.accessToken.userId)

                // Getting the user information
                /*
                val request = GraphRequest.newMeRequest(
                    result.accessToken
                ) { `object`, response -> // Application code
                    Log.i("VastuYogaTag", "onCompleted: response: $response")
                    try {
                        val email = `object`.getString("email")
                        val birthday = `object`.getString("birthday")
                        Log.i("VastuYogaTag", "onCompleted: Email: $email")
                        Log.i("VastuYogaTag", "onCompleted: Birthday: $birthday")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Log.i("VastuYogaTag", "onCompleted: JSON exception")
                    }
                }

                val parameters = Bundle()
                parameters.putString("fields", "id,name,email,gender,birthday")
                request.parameters = parameters
                request.executeAsync()
                 */
            }

            override fun onCancel() {
                listener.onAuthCancel()
                // Toast.makeText(context, "FacebookLogin onCancel", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException?) {
                listener.onAuthError("Facebook Login Failure")
                // Toast.makeText(context, "FacebookLogin onError", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mFirebaseAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(context, object : OnCompleteListener<AuthResult?> {
                override fun onComplete(task: Task<AuthResult?>) {
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("VastuYogaTag", "signInWithCredential:success")
                        val user = mFirebaseAuth!!.currentUser
                        Log.i(
                            "VastuYogaTag",
                            "onComplete: login completed with user: " + user!!.displayName
                        )
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(
                            "VastuYogaTag",
                            "signInWithCredential:failure",
                            task.exception
                        )
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    // ...
                }
            })
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.addOnSuccessListener {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = it!!.account
                    Log.d("LoginController", "firebaseAuthWithGoogle:" + it.id)
                    // firebaseAuthWithGoogle(account.idToken!!)
                    authSuccess(it.id!!)
                }
                task.addOnCanceledListener {
                    listener.onAuthCancel()
                }
                task.addOnFailureListener {
                    listener.onAuthError("Google Auth Fail")
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("LoginController", "Google sign in failed", e)
                listener.onAuthError("Google Login Failure")
            }
        } else if ( requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE ) {
            twitterAuthClient.onActivityResult(requestCode, resultCode, data)
        } else if ( FacebookSdk.isFacebookRequestCode(requestCode) ) {
            callbackManager!!.onActivityResult(requestCode, resultCode, data)
        } else {
            listener.onAuthCancel()
        }
    }

}