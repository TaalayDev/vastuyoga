package kg.vy.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kg.vy.BaseActivity
import kg.vy.R
import kg.vy.controllers.MainController
import kg.vy.controllers.PurchaseController
import kg.vy.utils.LocaleHelper
import kg.vy.utils.Prefs
import kg.vy.utils.Utils.Companion.makeStatusBarTransparent
import kg.vy.views.loopview.LoopView
import kg.vy.views.loopview.OnItemScrollListener
import kg.vy.views.loopview.OnItemSelectedListener
import kotlinx.android.synthetic.main.activity_select_time.*


class SelectTimeActivity : BaseActivity(), OnItemScrollListener {

    val mainController = MainController.getInstance()

    private lateinit var rewardedAd: RewardedAd

    private var rewardEarned = false
    /*TEST REWARDED_VIDEO_AD ID "ca-app-pub-3940256099942544/5224354917"*/
    private val REWARD_VIDEO_AD_ID = "ca-app-pub-8802390173696057/4461309401"

    private val adLoadCallback = object: RewardedAdLoadCallback() {
        override fun onRewardedAdLoaded() {
            // Ad successfully loaded.
        }

        override fun onRewardedAdFailedToLoad(adError: LoadAdError) {
            // Ad failed to load.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(R.layout.activity_select_time)

        MobileAds.initialize(this)

        PurchaseController.liveData.observe(this) {
            if ( it == 0 ) {
                Prefs.putBoolean(this, Prefs.ADS_ON, false)
                runOnUiThread {
                    noAdsText.visibility = View.GONE
                }
            }
        }
        PurchaseController.buildBillingClient(this)

        textTitle.text = mainController?.getVideoPropsModel()!!.title

        val h = getString(R.string.hour_ch)
        val m = getString(R.string.minute_ch)
        val hList = listOf("0 $h", "1 $h", "2 $h", "3 $h")
        val mList = mutableListOf<String>()
        for ( i in 0..24 ) mList.add("$i $m")

        hourLoop.setItems(hList)
        hourLoop.setOnItemScrollListener(this)
        hourLoop.setNotLoop()
        minLoop.setItems(mList)
        minLoop.setOnItemScrollListener(this)
        minLoop.setNotLoop()

        selectButton.setOnClickListener {
            if ( hourLoop.selectedItem != 0 || minLoop.selectedItem != 0 ) {
                mainController.playAudioRaw(this@SelectTimeActivity, R.raw.button)
                loadLay.visibility = View.VISIBLE
                if ( Prefs.getBooleanDef(this, Prefs.ADS_ON, true) ) {
                    rewardEarned = false
                    showAd()
                }
                else navigate()
            }
        }

        if ( !Prefs.getBooleanDef(this, Prefs.ADS_ON, true) )
            noAdsText.visibility = View.GONE

        noAdsText.setOnClickListener {
            PurchaseController.launchBilling("noads30day")
        }

        loadAd()
    }

    private fun loadAd() {
        rewardedAd = RewardedAd(this, REWARD_VIDEO_AD_ID)
        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
    }

    fun navigate() {
        loadLay.visibility = View.GONE
        mainController?.getVideoPropsModel()?.hour = hourLoop.selectedItem
        mainController?.getVideoPropsModel()?.minute = minLoop.selectedItem
        startActivity(Intent(this@SelectTimeActivity, VideoPlayActivity::class.java))
        finish()
    }

    private fun showAd() {
        if (rewardedAd.isLoaded) {
            val adCallback = object: RewardedAdCallback() {
                override fun onRewardedAdOpened() {
                    // Ad opened.
                }
                override fun onRewardedAdClosed() {
                    loadLay.visibility = View.GONE
                    loadAd()
                    if ( rewardEarned )
                        navigate()
                }
                override fun onUserEarnedReward(@NonNull reward: RewardItem) {
                    rewardEarned = true
                }
                override fun onRewardedAdFailedToShow(adError: AdError) {
                    loadLay.visibility = View.GONE
                }
            }
            rewardedAd.show(this, adCallback)
        } else {
            navigate()
        }
    }

    override fun onItemScrollStateChanged(
        loopView: LoopView?,
        currentPassItem: Int,
        oldScrollState: Int,
        scrollState: Int,
        totalScrollY: Int
    ) {
        mainController?.playAudioRaw(this, R.raw.scroll)
    }

    override fun onItemScrolling(
        loopView: LoopView?,
        currentPassItem: Int,
        scrollState: Int,
        totalScrollY: Int
    ) {

    }


}