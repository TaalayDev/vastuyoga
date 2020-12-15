package kg.vy.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kg.vy.BaseActivity
import kg.vy.R
import kg.vy.controllers.MainController
import kg.vy.utils.LocaleHelper
import kg.vy.utils.Prefs
import kg.vy.utils.Utils.Companion.makeStatusBarTransparent
import kg.vy.views.loopview.LoopView
import kg.vy.views.loopview.OnItemScrollListener
import kg.vy.views.loopview.OnItemSelectedListener
import kotlinx.android.synthetic.main.activity_lang_select.*

class LangSelectActivity : BaseActivity() {

    val mainController = MainController.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(R.layout.activity_lang_select)

        val map = mapOf<String, String>(
            getString(R.string.chinese) to "zn",
            getString(R.string.english) to "en",
            getString(R.string.russian) to "ru",
            getString(R.string.japanese) to "ja",
            getString(R.string.korean) to "ko"
        )

        val list = listOf(
            getString(R.string.chinese),
            getString(R.string.english),
            getString(R.string.russian),
            getString(R.string.japanese),
            getString(R.string.korean)
        )
        loopView.setItems(list)
        loopView.setCurrentPosition(1)
        loopView.setOnItemScrollListener(object: OnItemScrollListener {
            override fun onItemScrollStateChanged(
                loopView: LoopView?,
                currentPassItem: Int,
                oldScrollState: Int,
                scrollState: Int,
                totalScrollY: Int
            ) {
                mainController?.playAudioRaw(this@LangSelectActivity, R.raw.scroll)
            }

            override fun onItemScrolling(
                loopView: LoopView?,
                currentPassItem: Int,
                scrollState: Int,
                totalScrollY: Int
            ) {}
        })

        chooseLangButton.setOnClickListener {
            mainController?.playAudioRaw(this@LangSelectActivity, R.raw.button)
            val lang = map[list[loopView.selectedItem]]
            Log.e("LangSelectActivity", lang!!)
            Prefs.setLanguage(this@LangSelectActivity, lang)
            startActivity(Intent(this@LangSelectActivity, LoginActivity::class.java))
            finish()
        }

    }

    inner class Temp(val name: String, val value: String)

}