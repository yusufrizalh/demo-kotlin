package id.yusufrizalh.myvideoplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    private var btn_once: Button? = null
    private var btn_continu: Button? = null
    private var btn_stop: Button? = null
    private var video_view: VideoView? = null
    private var mediaController: MediaController? = null
    private var uri: Uri? = null
    private var isContinuously = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_once = findViewById(R.id.btn_once) as Button
        btn_continu = findViewById(R.id.btn_continu) as Button
        btn_stop = findViewById(R.id.btn_stop) as Button
        video_view = findViewById(R.id.video_view) as VideoView

        mediaController = MediaController(this)
        mediaController!!.setAnchorView(video_view)
        val uriPath = "android.resource://id.yusufrizalh.myvideoplayer/" + R.raw.bloomingflowerstimelapse
        uri = Uri.parse(uriPath)

        video_view!!.setOnCompletionListener {
            if (isContinuously) {
                video_view!!.start()
            }
        }

        btn_stop!!.setOnClickListener {
            video_view!!.stopPlayback()
        }

        btn_once!!.setOnClickListener {
            isContinuously = false
            video_view!!.setMediaController(mediaController)
            video_view!!.setVideoURI(uri)
            video_view!!.requestFocus()
            video_view!!.start()
        }

        btn_continu!!.setOnClickListener {
            isContinuously = true
            video_view!!.setMediaController(mediaController)
            video_view!!.setVideoURI(uri)
            video_view!!.requestFocus()
            video_view!!.start()
        }
    }
}
