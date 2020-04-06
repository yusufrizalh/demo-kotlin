package id.yusufrizalh.myaudioplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    private var pause: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // tombol play
        btn_play.setOnClickListener {
            if (pause) {
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
                Toast.makeText(this, "Media Playing", Toast.LENGTH_SHORT).show()
            } else {
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.eternalrain)
                mediaPlayer.start()
                Toast.makeText(this,"Media Playing",Toast.LENGTH_SHORT).show()
            }
            initializeSeekbar()
            btn_play.isEnabled = false
            btn_pause.isEnabled = true
            btn_stop.isEnabled = true

            mediaPlayer.setOnCompletionListener {
                btn_play.isEnabled = true
                btn_pause.isEnabled = false
                btn_stop.isEnabled = false
                Toast.makeText(this,"Media Stoped",Toast.LENGTH_SHORT).show()
            }
        }

        // tombol pause
        btn_pause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                pause = true
                btn_play.isEnabled = true
                btn_pause.isEnabled = false
                btn_stop.isEnabled = true
                Toast.makeText(this,"Media Paused",Toast.LENGTH_SHORT).show()
            }
        }

        // tombol stop
        btn_stop.setOnClickListener {
            if (mediaPlayer.isPlaying || pause.equals(true)) {
                pause = false
                seek_bar.setProgress(0)

                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                handler.removeCallbacks(runnable)

                btn_play.isEnabled = true
                btn_pause.isEnabled = false
                btn_stop.isEnabled = false
                txt_pass.text = ""
                txt_due.text = ""
                Toast.makeText(this,"Media Stoped",Toast.LENGTH_SHORT).show()
            }
        }

        // progress selama musik bermain
        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mediaPlayer.seekTo(i * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    private fun initializeSeekbar() {
        seek_bar.max = mediaPlayer.seconds

        runnable = Runnable {
            seek_bar.progress = mediaPlayer.currentSeconds

            txt_pass.text = "${mediaPlayer.currentSeconds} sec"
            val diff = mediaPlayer.seconds - mediaPlayer.currentSeconds
            txt_due.text = "$diff sec"
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }

    val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / 1000
        }
}
