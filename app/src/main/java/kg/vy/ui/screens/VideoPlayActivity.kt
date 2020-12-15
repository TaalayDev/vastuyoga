package kg.vy.ui.screens

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.observe
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.PlayerUiController
import kg.vy.BaseActivity
import kg.vy.R
import kg.vy.controllers.MainController
import kg.vy.controllers.PurchaseController
import kg.vy.utils.Prefs
import kotlinx.android.synthetic.main.activity_video_play.*


class VideoPlayActivity : BaseActivity() {

    val mainController = MainController.getInstance()
    var youtubePlayer: YouTubePlayer? = null
    var volume = 1f
    var isPlaying = false
    var ready = false

    override fun onResume() {
        super.onResume()
        mainController?.pauseBgAudio()
        if ( youtubePlayer != null && isPlaying )
            youtubePlayer?.play()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // makeStatusBarTransparent()
        setContentView(R.layout.activity_video_play)

        mainController!!.pauseBgAudio()

        PurchaseController.liveData.observe(this) {
            if ( it == 0 ) {
                Prefs.putBoolean(this, Prefs.ADS_ON, false)
                runOnUiThread {
                    noAdsText.visibility = View.GONE
                }
            }
        }
        PurchaseController.buildBillingClient(this)

        titleText?.text = mainController.getVideoPropsModel()?.title
        nameText?.text = mainController.getVideoPropsModel()?.name

        try {
            lifecycle.addObserver(youtubePlayerView)
            youtubePlayerView.addYouTubePlayerListener(object : YouTubePlayerListener {
                override fun onApiChange(youTubePlayer: YouTubePlayer) {}

                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {}

                override fun onError(
                    youTubePlayer: YouTubePlayer,
                    error: PlayerConstants.PlayerError
                ) {
                }

                override fun onPlaybackQualityChange(
                    youTubePlayer: YouTubePlayer,
                    playbackQuality: PlayerConstants.PlaybackQuality
                ) {
                }

                override fun onPlaybackRateChange(
                    youTubePlayer: YouTubePlayer,
                    playbackRate: PlayerConstants.PlaybackRate
                ) {
                }

                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youtubePlayer = youTubePlayer
                    val videoId = mainController.getVideoPropsModel()?.link!!
                    youTubePlayer.loadVideo(videoId, 0f)
                    youTubePlayer.play()
                    progressLay.visibility = View.GONE
                    isPlaying = true
                    ready = true
                }

                override fun onStateChange(
                    youTubePlayer: YouTubePlayer,
                    state: PlayerConstants.PlayerState
                ) {
                    if (state == PlayerConstants.PlayerState.ENDED) {
                        Log.d("VideoPlayActivity", "state: ended")
                        youTubePlayer.seekTo(0f)
                        youTubePlayer.play()
                    }
                }

                override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
                    Log.d("VideoPlayActivity", "duration: $duration")
                }

                override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {}

                override fun onVideoLoadedFraction(
                    youTubePlayer: YouTubePlayer,
                    loadedFraction: Float
                ) {
                }

            })
            youtubePlayerView.setOnTouchListener { a, b -> false }
            val uiCont = youtubePlayerView.getPlayerUiController()
            uiCont.showPlayPauseButton(false)
            uiCont.showVideoTitle(false)
            uiCont.showCustomAction1(false)
            uiCont.showCustomAction2(false)
            uiCont.showMenuButton(false)
            uiCont.showUi(false)
        } catch (exception: Exception) {  }

        progressLay.visibility = View.VISIBLE

        menubt.setOnClickListener {
            Log.d("VideoPlayActivity", "menubt $ready $isPlaying")
            showMenu()
        }

        if ( !Prefs.getBooleanDef(this, Prefs.ADS_ON, true) )
            noAdsText.visibility = View.GONE
        noAdsText.setOnClickListener {
            PurchaseController.launchBilling("noads30day")
        }

        textMute.setOnClickListener {
            try {
                Log.d("VideoPlayActivity", "mute button $ready $isPlaying")
                if (ready) {
                    volume = if (volume == 0f) {
                        youtubePlayer?.unMute()
                        textMute.text = "MUTE"
                        1f
                    } else {
                        youtubePlayer?.mute()
                        textMute.text = "UNMUTE"
                        0f
                    }
                }
            } catch(e: Exception) { }
        }
        playAndPauseIcon.setOnClickListener {
            try {
                Log.d("VideoPlayActivity", "playpause button $ready $isPlaying")
                if (ready) {
                    if (isPlaying) {
                        youtubePlayer?.pause()
                        playAndPauseIcon.setImageResource(R.drawable.play)
                    } else {
                        youtubePlayer?.play()
                        playAndPauseIcon.setImageResource(R.drawable.pause)
                    }
                    isPlaying = !isPlaying
                }
            } catch (e: Exception) {  }
        }
        textExit.setOnClickListener { onBackPressed() }

        mainController.venergyLiveData.observe(this) {
            runOnUiThread { venergyCountText.text = it.toString() }
        }

        startTimer()
    }

    override fun onPause() {
        super.onPause()
        Log.d("VideoPlayActivity", "onPause")
        if ( youtubePlayer != null && isPlaying )
            youtubePlayer?.pause()
    }

    override fun onRestart() {
        super.onRestart()
        if ( youtubePlayer != null && isPlaying )
            youtubePlayer?.play()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun showMenu() {
        contrLay.visibility = View.VISIBLE
        menubt.isClickable = false
        Handler().postDelayed(Runnable {
            contrLay.visibility = View.GONE
            menubt.isClickable = true
        }, 3000)
    }
    
    fun startTimer() {
        var hours = mainController!!.getVideoPropsModel()?.hour!!
        var minutes = mainController.getVideoPropsModel()?.minute!!
        var seconds = 59
        timeText.text = "$hours:${__cc(minutes)}:${__cc(seconds)}"

        val mainHandler = Handler(mainLooper)
        mainHandler.post(object : Runnable {
            override fun run() {
                if ( isPlaying ) {
                    if (seconds == 0) {
                        if (minutes == 0) {
                            if (hours == 0) {
                                onBackPressed()
                                return
                            } else hours--
                            minutes = 59;
                        } else {
                            minutes--
                            val venergy = Prefs.getInt(this@VideoPlayActivity, Prefs.VENERGY) + 1
                            Prefs.putInt(this@VideoPlayActivity, Prefs.VENERGY, venergy)
                            venergyCountText.text = venergy.toString()
                        }
                        seconds = 59
                    } else seconds--
                    timeText.text = "$hours:${__cc(minutes)}:${__cc(seconds)}"
                }
                mainHandler.postDelayed(this, 1000)
            }
        })
    }

    fun __cc(s: Int): String {
        return if (s < 10) "0$s" else "$s"
    }

    override fun onBackPressed() {
        // super.onBackPressed()
        mainController?.playBgAudio(applicationContext)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}