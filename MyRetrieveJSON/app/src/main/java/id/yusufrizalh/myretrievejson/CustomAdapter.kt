package id.yusufrizalh.myretrievejson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class CustomAdapter(context: Context, arrayListDetails: ArrayList<Model>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater
    private val arrayListDetails: ArrayList<Model>

    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails = arrayListDetails
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.adapter_layout, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        listRowHolder.txt_id.text = arrayListDetails.get(position).id
        listRowHolder.txt_name.text = arrayListDetails.get(position).name
        listRowHolder.txt_email.text = arrayListDetails.get(position).email
        return view
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayListDetails.size
    }

}

private class ListRowHolder(row: View?) {
    public val txt_id: TextView
    public val txt_name: TextView
    public val txt_email: TextView
    public val linearLayout: LinearLayout

    init {
        this.txt_id = row?.findViewById<TextView>(R.id.txt_id) as TextView
        this.txt_name = row?.findViewById<TextView>(R.id.txt_name) as TextView
        this.txt_email = row?.findViewById<TextView>(R.id.txt_email) as TextView
        this.linearLayout = row?.findViewById<LinearLayout>(R.id.linearLayout) as LinearLayout
    }
}
