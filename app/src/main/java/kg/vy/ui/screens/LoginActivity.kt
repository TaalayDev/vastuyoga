package kg.vy.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kg.vy.BaseActivity
import kg.vy.R
import kg.vy.controllers.LoginController
import kg.vy.controllers.MainController
import kg.vy.utils.LocaleHelper
import kg.vy.utils.Prefs
import kg.vy.utils.Utils.Companion.makeStatusBarTransparent
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginController.AuthListener {

    val mainController = MainController.getInstance()
    private lateinit var loginController: LoginController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginController = LoginController(this)
        makeStatusBarTransparent()
        setContentView(R.layout.activity_login)

        facebookLoginButton.setOnClickListener {
            loginController.facebookLogin()
            loadingLay.visibility = View.VISIBLE
        }
        googleLoginButton.setOnClickListener {
            loginController.googleLogin()
            loadingLay.visibility = View.VISIBLE
        }
        twitterLoginButton.setOnClickListener {
            loginController.twitterLogin()
            loadingLay.visibility = View.VISIBLE
        }
        loginButton.setOnClickListener {
            if ( editTextName.text.isNotEmpty() ) {
                mainController?.playAudioRaw(this@LoginActivity, R.raw.button)
                onAuthSuccess()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loginController.onActivityResult(requestCode, resultCode, data)
    }

    override fun onAuthSuccess() {
        loadingLay.visibility = View.GONE
        Prefs.putBoolean(this, Prefs.IS_LOGIN, true)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onAuthError(err: String) {
        loadingLay.visibility = View.GONE
    }

    override fun onAuthCancel() {
        loadingLay.visibility = View.GONE
    }

}