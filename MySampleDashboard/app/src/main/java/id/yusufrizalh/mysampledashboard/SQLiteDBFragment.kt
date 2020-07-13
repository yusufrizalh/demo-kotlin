package id.yusufrizalh.mysampledashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SQLiteDBFragment : Fragment() {

    val dbHandler = this@SQLiteDBFragment.context?.let { DBHelper(it, null) }
    var dataList = ArrayList<HashMap<String, String>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.sqlitedb_layout, container, false)

        val fab = view.findViewById<FloatingActionButton>(R.id.fab_1)
        fab.setOnClickListener {
            val intent = Intent(activity, DetailsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

//    fun loadIntoList() {
//        dataList.clear()
//        val cursor = dbHandler?.getAllRow()
//        cursor?.moveToFirst()
//
//        if (cursor != null) {
//            while (!cursor.isAfterLast) {
//                val map = HashMap<String, String>()
//                map["id"] = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ID))
//                map["name"] = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME))
//                map["age"] = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_AGE))
//                map["email"] = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL))
//                dataList.add(map)
//
//                cursor.moveToNext()
//            }
//        }
//
//        view?.findViewById<ListView>(R.id.listView)!!.adapter =
//            this@SQLiteDBFragment.context?.let { CustomAdapter(it, dataList) }
//        view?.findViewById<ListView>(R.id.listView)!!.setOnItemClickListener { _, _, i, _ ->
//            val intent = Intent(activity!!, DetailsActivity::class.java)
//            intent.putExtra("id", dataList[+i]["id"])
//            intent.putExtra("name", dataList[+i]["name"])
//            intent.putExtra("age", dataList[+i]["age"])
//            intent.putExtra("email", dataList[+i]["email"])
//            startActivity(intent)
//        }
//    }

    fun fabClicked(v: View) {
        val intent = Intent(activity, DetailsActivity::class.java)
        startActivity(intent)
    }


    override fun onResume() {
        super.onResume()
//        loadIntoList()
    }
}