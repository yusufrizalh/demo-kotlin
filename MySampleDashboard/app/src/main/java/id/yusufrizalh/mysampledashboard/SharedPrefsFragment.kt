@file:Suppress("UNREACHABLE_CODE")

package id.yusufrizalh.mysampledashboard

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SharedPrefsFragment : Fragment() {
    // (1) inisialisasi komponen
    private val prefName = "ixsharedpreferences"
    private val privateMode = 0

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // val x = inflater.inflate(R.layout.shared_prefs_layout, null)
        val view : View = inflater.inflate(R.layout.shared_prefs_layout, container, false)

        // mengenali setiap komponen pada layout
        val inputUsername = view.findViewById<EditText>(R.id.edit_username)
        val inputPassword = view.findViewById<EditText>(R.id.edit_password)
        val outputUsername = view.findViewById<TextView>(R.id.txt_username)
        val outputPassword = view.findViewById<TextView>(R.id.txt_password)
        val btn_save = view.findViewById<Button>(R.id.btn_save)
        val btn_show = view.findViewById<Button>(R.id.btn_show)
        val btn_clear = view.findViewById<Button>(R.id.btn_clear)


        // memanggil metode shared preferences
        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(prefName, privateMode)!!

        // event handling btn_save
        btn_save.setOnClickListener {
            val username: String = inputUsername.text.toString()
            val password: String = inputPassword.text.toString()

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username_key", username)
            editor.putString("password_key", password)
            editor.apply()
            editor.commit()

            Toast.makeText(activity?.applicationContext, "Data disimpan", Toast.LENGTH_SHORT).show()
        }

        // event handling btn_show
        btn_show.setOnClickListener {
            val sharedUsernameValue = sharedPreferences.getString("username_key", "defaultusername")
            val sharedpasswordValue = sharedPreferences.getString("password_key", "defaultpassword")

            if (sharedUsernameValue.equals("defaultusername") && sharedpasswordValue.equals("defaultpassword")) {
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

        return view
    }
}