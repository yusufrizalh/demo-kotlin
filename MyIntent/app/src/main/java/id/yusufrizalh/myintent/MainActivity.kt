package id.yusufrizalh.myintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // (1) inisialisasi semua komponen dalam layout
    lateinit var btn_1: Button
    lateinit var edit_name: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // (2) mengenali semua komponen dalam layout
        btn_1 = findViewById(R.id.btn_1)
        edit_name = findViewById(R.id.edit_name)

        // (3) membuat event handling
        btn_1.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // perintah untuk button
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("name", edit_name.text.toString())
        startActivity(intent)
    }
}