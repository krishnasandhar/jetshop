package dev.company.jetshop.loginregister

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import dev.company.jetshop.R

class AccountDetails: AppCompatActivity() {

    var firstname:EditText?=null
    var surname:EditText?=null
    var email:EditText?=null
    var mobile:EditText?=null
    var update:TextView?=null
    var sqlite: SQLiteDatabase?=null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)

        firstname = findViewById(R.id.edt_firstname)
        surname = findViewById(R.id.edt_surname)
        email = findViewById(R.id.edt_mail)
        mobile = findViewById(R.id.edt_mobile)
        update = findViewById(R.id.update)

        update?.setOnClickListener {

            val firstname = firstname?.text.toString()
            val surname = surname?.text.toString()
            val email = email?.text.toString()
            val mobile = mobile?.text.toString()

            if (firstname.length == 0) {
                Toast.makeText(this, "Enter your firstname", Toast.LENGTH_SHORT).show()
            } else if (surname.length == 0) {
                Toast.makeText(this, "Enter your surname", Toast.LENGTH_SHORT).show()
            } else if (email.length==0) {
                Toast.makeText(this, "Enter the email", Toast.LENGTH_SHORT).show()
            } else if (mobile.length==0) {
                Toast.makeText(this, "Enter your mobile", Toast.LENGTH_SHORT).show()
            } else {
                sqlite = this.openOrCreateDatabase("register.db",MODE_APPEND,null)
                sqlite?.execSQL(" update user_register set firstname = '$firstname' ,  surname = '$surname',email = '$email' where mobile =$mobile ")
                Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
    }
}