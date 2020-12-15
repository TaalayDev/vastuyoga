package kg.vy.utils

import android.content.Context

class Prefs {

    companion object {

        const val LANG_SET = "LANG_SET"
        const val IS_LOGIN = "IS_LOGIN"
        const val VENERGY = "VENERGY"
        const val ADS_ON = "ADS_ON"
        const val UID = "UID"
        const val LANG = "LANG"

        const val SHARED_PREF = "SHARED_PREF"
        const val DEFAULT_BOOLEAN = false
        const val DEFAULT_STRING = ""
        const val DEFAULT_INT = 0
        const val DEFAULT_LONG: Long = 0

        @JvmStatic
        fun setLanguage(context: Context, lang: String) {
            LocaleHelper.setLocale(context, lang)
            putString(context, LANG, lang)
            putBoolean(context, LANG_SET, true)
        }

        @JvmStatic
        fun getLanguage(context: Context): String? {
            return getStringDef(context, LANG, "en")
        }

        @JvmStatic
        fun getBoolean(c: Context, key: String?): Boolean {
            val sp = c.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            return sp.getBoolean(key, DEFAULT_BOOLEAN)
        }

        @JvmStatic
        fun getBooleanDef(c: Context, key: String?, def: Boolean): Boolean {
            val sp = c.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            return sp.getBoolean(key, def)
        }

        @JvmStatic
        fun getString(context: Context, key: String?): String? {
            val sp = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            return sp.getString(key, DEFAULT_STRING)
        }

        @JvmStatic
        fun getStringDef(context: Context, key: String?, def: String?): String? {
            val sp = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            return sp.getString(key, def)
        }

        @JvmStatic
        fun getInt(context: Context, key: String?): Int {
            val sp = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            return sp.getInt(key, DEFAULT_INT)
        }

        @JvmStatic
        fun getIntDef(context: Context, key: String?, def: Int): Int {
            val sp = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            return sp.getInt(key, def)
        }

        @JvmStatic
        fun getLong(context: Context, key: String?): Long {
            val sp = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            return sp.getLong(key, DEFAULT_LONG)
        }

        @JvmStatic
        fun putBoolean(c: Context, key: String?, value: Boolean) {
            val sp = c.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            sp.edit().putBoolean(key, value).commit()
        }

        @JvmStatic
        fun putString(context: Context, key: String?, value: String?) {
            val sp = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            sp.edit().putString(key, value).commit()
        }

        @JvmStatic
        fun putInt(context: Context, key: String?, value: Int) {
            val sp = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            sp.edit().putInt(key, value).commit()
        }

        @JvmStatic
        fun putLong(context: Context, key: String?, value: Long) {
            val sp = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            sp.edit().putLong(key, value).commit()
        }

    }

}