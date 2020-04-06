package id.yusufrizalh.mysharedprefs

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    /*
        by @yusufrizalh
    */
private val sharedPrefFile = "ixsharedpreferences"

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // mengenali setiap komponen pada layout
    val inputUsername = findViewById<EditText>(R.id.edit_username)
    val inputPassword = findViewById<EditText>(R.id.edit_password)
    val outputUsername = findViewById<TextView>(R.id.txt_username)
    val outputPassword = findViewById<TextView>(R.id.txt_password)
    val btn_save = findViewById<Button>(R.id.btn_save)
    val btn_show = findViewById<Button>(R.id.btn_show)
    val btn_clear = findViewById<Button>(R.id.btn_clear)

    // memanggil metode shared preferences
    val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

    // event handling btn_save
    btn_save.setOnClickListener(View.OnClickListener {
        val username: String = inputUsername.text.toString()
        val password: String = inputPassword.text.toString()

        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("username_key", username)
        editor.putString("password_key", password)
        editor.apply()
        editor.commit()
    })

    // event handling btn_show
    btn_show.setOnClickListener {
        val sharedUsernameValue = sharedPreferences.getString("username_key", "defaultusername")
        val sharedpasswordValue = sharedPreferences.getString("password_key", "defaultpassword")

        if(sharedUsernameValue.equals("defaultusername") && sharedpasswordValue.equals("defaultpassword")) {
            outputUsername.setText("Default Username: ${sharedUsernameValue}").toString()
            outputPassword.setText("Default Password: ${sharedpasswordValue}").toString()
        } else {
            outputUsername.setText(sharedUsernameValue).toString()
            outputPassword.setText(sharedpasswordValue).toString()
        }
    }

    // event handling btn_clear
    btn_clear.setOnClickListener(View.OnClickListener {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        inputUsername.setText("").toString()
        inputPassword.setText("").toString()
        outputUsername.setText("").toString()
        outputPassword.setText("").toString()
        inputUsername.requestFocus()
    })
}

}
