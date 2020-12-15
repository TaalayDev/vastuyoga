package kg.vy.controllers

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import kg.vy.utils.Prefs

class FireStoreController {

    companion object {

        private val store = FirebaseFirestore.getInstance()

        fun updateUser(id: String, venergy: Int? = null, adsOn: Boolean? = null, callBack: () -> Unit) {
            val data: HashMap<String, Any> = hashMapOf()
            if ( venergy != null )
                data["venergy"] = venergy
            if ( adsOn != null )
                data["adsOn"] = if ( adsOn ) 1 else 0
            if ( data.size > 0 )
                store.collection("users").document(id).set(data)
            callBack()
        }

        fun checkUser(context: Context, id: String, callBack: () -> Unit) {
            store.collection("users").document(id).get().addOnSuccessListener { snapshot ->
                if ( snapshot.exists() ) {
                    val venergy = snapshot.get("venergy").toString()
                    val adsOn = snapshot.get("ads_on").toString() == "1"
                    Prefs.putInt(context, Prefs.VENERGY, venergy.toInt())
                    Prefs.putBoolean(context, Prefs.ADS_ON, adsOn)
                    callBack()
                } else {
                    updateUser(id, 0, false, callBack)
                }
            }
        }

    }

}