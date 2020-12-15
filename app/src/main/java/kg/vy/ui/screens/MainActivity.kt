package kg.vy.ui.screens

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kg.vy.BaseActivity
import kg.vy.R
import kg.vy.adapters.SectionsPagerAdapter
import kg.vy.ui.fragments.GameFragment
import kg.vy.ui.fragments.MenuFragment
import kg.vy.ui.fragments.ShopFragment
import kg.vy.utils.LocaleHelper
import kg.vy.utils.Prefs
import kg.vy.utils.Utils.Companion.makeStatusBarTransparent
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : BaseActivity() {

    val pages = listOf(
        MenuFragment(),
        ShopFragment(),
        GameFragment()
    )

    val colors = listOf(
        R.color.colorLightPink,
        R.color.colorGreen,
        R.color.colorGreen
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(R.layout.content_main)

        val adapter = SectionsPagerAdapter(supportFragmentManager, pages)
        pager.adapter = adapter

        pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                rootLay.setBackgroundColor(resources.getColor(colors[position]))
            }

        })

    }



}