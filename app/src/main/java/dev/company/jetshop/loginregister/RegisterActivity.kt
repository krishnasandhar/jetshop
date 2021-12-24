package dev.company.jetshop.loginregister

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import dev.company.jetshop.R

class RegisterActivity: AppCompatActivity() {

    var firstname: EditText?=null
    var surname: EditText?=null
    var email: EditText?=null
    var mobile: EditText?=null
    var register: TextView?=null
    var sqlite: SQLiteDatabase?=null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firstname = findViewById(R.id.edt_firstname)
        surname = findViewById(R.id.edt_surname)
        email = findViewById(R.id.edt_email)
        mobile = findViewById(R.id.edt_mobile)
        register = findViewById(R.id.register)
        sqlite = this.openOrCreateDatabase("register.db",MODE_APPEND,null)

        sqlite?.execSQL("create table if not exists user_register(firstname varchar(20), surname varchar(20), email varchar(30), mobile varchar(20) )")

        register?.setOnClickListener {

            val firstname = firstname?.text.toString()
            val surname = surname?.text.toString()
            val email = email?.text.toString()
            val mobile = mobile?.text.toString()

            if (firstname.isEmpty()) {
                Toast.makeText(this, "Enter your firstname", Toast.LENGTH_SHORT).show()
            } else if (surname.isEmpty()) {
                Toast.makeText(this, "Enter your surname", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Enter the email", Toast.LENGTH_SHORT).show()
            } else if (mobile.isEmpty()) {
                Toast.makeText(this, "Enter your mobile", Toast.LENGTH_SHORT).show()
            } else {
                sqlite?.execSQL("insert into user_register(firstname,surname,email,mobile) values ('$firstname','$surname','$email','$mobile')")
                Toast.makeText(this, "Successfully register", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
    }
}