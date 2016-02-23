package org.ligi.gobandroid_hd.ui

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import org.ligi.gobandroid_hd.R
import org.ligi.gobandroid_hd.ui.application.GobandroidFragmentActivity
import org.ligi.gobandroid_hd.ui.application.GobandroidSettings
import java.util.*

/**
 * Class to care about the Board-Sounds

 * @author [Marcus -LiGi- Bueschleb ](http://ligi.de)
 * *
 *
 *
 * *         This software is licenced with GPLv3
 */
class GoSoundManager(private val context: GobandroidFragmentActivity, private val settings: GobandroidSettings) {

    enum class Sound(val resID: Int) {
        START(R.raw.go_start),
        END(R.raw.go_clearboard),
        PLACE1(R.raw.go_place1),
        PLACE2(R.raw.go_place2),
        PICKUP1(R.raw.go_pickup1),
        PICKUP2(R.raw.go_pickup2)
    }

    private val soundMap = HashMap<Sound, Int>()

    private val STREAM = AudioManager.STREAM_MUSIC
    private val mSoundPool: SoundPool by lazy { SoundPool(4, STREAM, 0) }
    private val mAudioManager: AudioManager by lazy { context.getSystemService(Context.AUDIO_SERVICE) as AudioManager }

    fun playSound(sound: Sound) {
        if (settings.isSoundEnabled) {
            if (soundMap[sound] == null) {
                soundMap[sound] = mSoundPool.load(context, sound.resID, 1)
            }
            var streamVolume = mAudioManager.getStreamVolume(STREAM).toFloat()
            streamVolume /= mAudioManager.getStreamMaxVolume(STREAM)
            mSoundPool.play(soundMap[sound]!!, streamVolume, streamVolume, 1, 0, 1f)
        }
    }

}
