package id.yusufrizalh.myintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    // (1) inisialisasi semua komponen dalam layout
    lateinit var txt_display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // (2) mengenali semua komponen dalam layout
        txt_display = findViewById(R.id.txt_display)

        // (3) event handling
        val name = intent.getStringExtra("name")
        txt_display.text = name
    }
}