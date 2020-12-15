package kg.vy

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import kg.vy.utils.ContextUtils
import kg.vy.utils.Prefs
import java.util.*

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        if ( Prefs.getBoolean(newBase!!, Prefs.LANG_SET) ) {
            // get chosen language from shread preference
            val localeToSwitchTo = Locale(Prefs.getLanguage(newBase).toString())
            val localeUpdatedContext: ContextWrapper =
                ContextUtils.updateLocale(newBase, localeToSwitchTo)
            super.attachBaseContext(localeUpdatedContext)
        } else super.attachBaseContext(newBase)
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            // update overrideConfiguration with your locale
            // setLocale(overrideConfiguration) // you will need to implement this
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

}