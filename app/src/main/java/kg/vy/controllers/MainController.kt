package kg.vy.controllers

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import kg.vy.R
import kg.vy.models.VideoPropsModel
import kg.vy.utils.Prefs
import java.util.*

class MainController {

    private lateinit var timer: Timer
    private var videoModel: VideoPropsModel? = null
    private var backgroundAudioPlayer: MediaPlayer? = null

    private var _venergyLiveData = MutableLiveData<Int>()

    val venergyLiveData: MutableLiveData<Int>
        get() = _venergyLiveData

    fun setVideoPropsModel(model: VideoPropsModel) {
        videoModel = model
    }

    fun getVideoPropsModel() = videoModel

    companion object {
        private var instance: MainController? = null

        fun getInstance(): MainController? {
            if ( instance == null )
                instance =
                    MainController()
            return instance
        }

    }

    fun playBgAudio(context: Context) {
        if ( backgroundAudioPlayer == null ) {
            backgroundAudioPlayer = MediaPlayer.create(context, R.raw.audiomane)
            backgroundAudioPlayer?.isLooping = true
        }
        if ( !backgroundAudioPlayer!!.isPlaying ) {
            backgroundAudioPlayer?.start()
        }
    }

    fun pauseBgAudio() {
        if ( backgroundAudioPlayer != null ) {
            backgroundAudioPlayer?.pause()
        }
    }

    fun playAudioRaw(context: Context, source: Int) {
        val player = MediaPlayer.create(context, source)
        player.start()
    }

    fun onAppBackgrounded() {
        pauseBgAudio()
    }

    fun onAppForegrounded(context: Context) {
        playBgAudio(context)
    }

    fun startVenergyEarnTimer(context: Context, delay: Long) {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                val venergy = Prefs.getInt(context, Prefs.VENERGY) + 1
                Prefs.putInt(context, Prefs.VENERGY, venergy)
                // Get a handler that can be used to post to the main thread
                venergyLiveData.value = venergy
                mainHandler.postDelayed(this, delay)
            }
        })
    }

}