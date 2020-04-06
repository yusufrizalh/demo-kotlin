package id.yusufrizalh.myretrievejson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import okhttp3.OkHttpClient
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var progress: ProgressBar
    lateinit var listview_details: ListView
    var arraylist_details: ArrayList<Model> = ArrayList()

    // memanggil OkHttpClient untuk membuat koneksi antara client dan server
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress = findViewById(R.id.my_progress)
        progress.visibility = View.VISIBLE
        listview_details = findViewById<ListView>(R.id.listView) as ListView
        run("http://172.16.4.51/myphp/index.html")
    }

    fun run(url: String) {
        progress.visibility = View.VISIBLE
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                progress.visibility = View.GONE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body!!.string()
                // membuat objek json
                val json_contact: JSONObject = JSONObject(str_response)
                // membuat json array
                var jsonarray_info: JSONArray = json_contact.getJSONArray("info")
                var i: Int = 0
                var size: Int = jsonarray_info.length()
                arraylist_details = ArrayList()

                for (i in 0..size - 1) {
                    var jsonobject_details: JSONObject = jsonarray_info.getJSONObject(i)
                    var model: Model = Model()
                    model.id = jsonobject_details.getString("id")
                    model.name = jsonobject_details.getString("name")
                    model.email = jsonobject_details.getString("email")
                    arraylist_details.add(model)
                }

                runOnUiThread {
                    val object_adapter: CustomAdapter
                    object_adapter = CustomAdapter(applicationContext, arraylist_details)
                    listview_details.adapter = object_adapter
                }
                progress.visibility = View.GONE
            }
        })
    }
}
