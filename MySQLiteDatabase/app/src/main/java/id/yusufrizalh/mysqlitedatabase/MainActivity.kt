package id.yusufrizalh.mysqlitedatabase

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // function untuk menyimpan record
    fun saveRecord(view: View) {
        val id = edit_id.text.toString()
        val name = edit_username.text.toString()
        val email = edit_email.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        // pengecekan tidak boleh ada yg kosong
        if (id.trim() != "" && name.trim() != "" && email.trim() != "") {
            val status =
                databaseHandler.addEmployee(EmpModelClass(Integer.parseInt(id), name, email))
            if (status > -1) {
                Toast.makeText(applicationContext, "Simpan Record!", Toast.LENGTH_LONG).show()
                clearText()
            }
        } else {
            Toast.makeText(applicationContext, "Tidak boleh ada fields kosong!", Toast.LENGTH_LONG)
                .show()
        }
    }

    // function untuk membaca record
    fun viewRecord(view: View) {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size) { "0" }
        val empArrayName = Array<String>(emp.size) { "null" }
        val empArrayEmail = Array<String>(emp.size) { "null" }

        var index = 0
        for (e in emp) {
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            index++
        }
        val myListAdapter = MyListAdapter(this, empArrayId, empArrayName, empArrayEmail)
        list_view_1.adapter = myListAdapter
    }

    // function untuk mengubah record
    fun updateRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val editId = dialogView.findViewById(R.id.updateId) as EditText
        val editName = dialogView.findViewById(R.id.updateName) as EditText
        val editEmail = dialogView.findViewById(R.id.updateEmail) as EditText

        dialogBuilder.setTitle("Ubah Record")
        dialogBuilder.setMessage("Tulis Data disini!")
        dialogBuilder.setPositiveButton("Ubah", DialogInterface.OnClickListener { _, _ ->
            val updateId = editId.text.toString()
            val updateName = editName.text.toString()
            val updateEmail = editEmail.text.toString()

            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (updateId.trim() != "" && updateName.trim() != "" && updateEmail.trim() != "") {
                val status = databaseHandler.updateEmployee(
                    EmpModelClass(
                        Integer.parseInt(updateId),
                        updateName,
                        updateEmail
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "Ubah Record!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Tidak boleh ada fields kosong!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        dialogBuilder.setNegativeButton("Batal", DialogInterface.OnClickListener { dialog, which ->
        })
        val b = dialogBuilder.create()
        b.show()
    }

    fun deleteRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Hapus Record")
        dialogBuilder.setMessage("Tuliskan ID disini")
        dialogBuilder.setPositiveButton("Hapus", DialogInterface.OnClickListener { _, _ ->
            val deleteId = dltId.text.toString()
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (deleteId.trim() != "") {
                val status = databaseHandler.deleteEmployee(
                    EmpModelClass(
                        Integer.parseInt(deleteId),
                        "",
                        ""
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "Hapus Record!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Tidak boleh ada fields kosong!",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
        dialogBuilder.setNegativeButton("Batal", DialogInterface.OnClickListener { _, _ ->
        })
        val b = dialogBuilder.create()
        b.show()
    }

    // function untuk membersihkan teks
    fun clearText() {
        edit_id.setText("").toString()
        edit_username.setText("").toString()
        edit_email.setText("").toString()
        edit_id.requestFocus()
    }
}
