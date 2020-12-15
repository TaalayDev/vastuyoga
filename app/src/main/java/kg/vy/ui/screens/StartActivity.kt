package kg.vy.ui.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kg.vy.BaseActivity
import kg.vy.R
import kg.vy.controllers.LoginController
import kg.vy.controllers.MainController
import kg.vy.utils.Prefs
import kg.vy.utils.Utils.Companion.makeStatusBarTransparent
import kotlinx.android.synthetic.main.activity_start.*
import java.util.*


class StartActivity : BaseActivity() {

    val mainController = MainController.getInstance()

    private var swipeStarted = false
    private var imagesList: List<Int> = listOf(
        R.drawable.yantra_1,
        R.drawable.yantra_2,
        R.drawable.yantra_3,
        R.drawable.yantra_4,
        R.drawable.yantra_5,
        R.drawable.yantra_6,
        R.drawable.yantra_7
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(R.layout.activity_start)

        mainController!!.startVenergyEarnTimer(applicationContext, 600000)
        mainController.playAudioRaw(this, R.raw.enter)

        initPager()
        navigatePageTimer()

        loadingTextView.text = getString(R.string.loading) + "..."
        blinkAnimation(loadingTextView)
    }

    private fun navigatePageTimer() {
        Handler().postDelayed({
            if ( Prefs.getBoolean(this, Prefs.LANG_SET) && LoginController.isLogin(this) )
                startActivity(Intent(this, MainActivity::class.java))
            else if ( !Prefs.getBoolean(this, Prefs.LANG_SET) )
                startActivity(Intent(this, LangSelectActivity::class.java))
            else startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 8000)
    }

    private fun initPager() {
        viewPager.adapter = CustomPagerAdapter(this, imagesList)
        startSwipe()
    }

    private fun startSwipe() {
        val handler = Handler()
        val update = Runnable {
            try {
                var currentPage: Int = viewPager.getCurrentItem()
                if (currentPage == imagesList.size - 1) {
                    currentPage = 0
                } else {
                    currentPage++
                }
                viewPager.setCurrentItem(currentPage, true)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "swipe runnable exception " + e.message,
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("StartActivity.LOG", "swipe runnable exception " + e.message)
            }
        }
        if (!swipeStarted) {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    handler.post(update)
                }
            }, 3000, 3000)
            swipeStarted = true
        }
    }

    private fun blinkAnimation(view: View) {
        val anim: Animation = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000 // You can manage the blinking time with this parameter

        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        view.startAnimation(anim)
    }

    class CustomPagerAdapter(context: Context, list: List<Int>) : PagerAdapter() {

        private var context: Context = context
        private var imagesList: List<Int> = list

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)
            val imageL: View = inflater.inflate(R.layout.image_item, container, false)
            val imageView: ImageView = imageL.findViewById(R.id.imageItem)
            imageView.setImageResource(imagesList.get(position))
            // imageView.setBackgroundColor(getResources().getColor(colors[position]));
            (container as ViewPager).addView(imageL, 0)
            return imageL
        }

        override fun getCount(): Int {
            return imagesList.size
        }

        override fun isViewFromObject(
            view: View,
            `object`: Any
        ): Boolean {
            return view === `object` as View
        }

        override fun destroyItem(
            container: ViewGroup,
            position: Int,
            `object`: Any
        ) {
            (container as ViewPager).removeView(`object` as View)
        }
    }

}