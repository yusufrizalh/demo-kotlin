package id.yusufrizalh.mysimplesqlite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val dbHandler = DBHelper(this, null)
    var dataList = ArrayList<HashMap<String, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun loadIntoList(){
        dataList.clear()
        val cursor = dbHandler.getAllRow()
        cursor!!.moveToFirst()

        while (!cursor.isAfterLast) {
            val map = HashMap<String, String>()
            map["id"] = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ID))
            map["name"] = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME))
            map["age"] = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_AGE))
            map["email"] = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL))
            dataList.add(map)

            cursor.moveToNext()
        }
        findViewById<ListView>(R.id.listView).adapter = CustomAdapter(this@MainActivity, dataList)
        findViewById<ListView>(R.id.listView).setOnItemClickListener { _, _, i, _ ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("id", dataList[+i]["id"])
            intent.putExtra("name", dataList[+i]["name"])
            intent.putExtra("age", dataList[+i]["age"])
            intent.putExtra("email", dataList[+i]["email"])
            startActivity(intent)
        }
    }

    fun fabClicked(v:View){
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }


    public override fun onResume() {
        super.onResume()
        loadIntoList()
    }
}