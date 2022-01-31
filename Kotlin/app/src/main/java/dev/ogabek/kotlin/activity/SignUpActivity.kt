package dev.ogabek.kotlin.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import dev.ogabek.kotlin.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var fullName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var signUp: Button
    private lateinit var signIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initViews()

    }

    private fun initViews() {
        fullName = findViewById(R.id.et_full_name)
        email = findViewById(R.id.et_new_email)
        password = findViewById(R.id.et_new_password)
        confirmPassword = findViewById(R.id.et_confirm_password)

        signUp = findViewById(R.id.btn_sign_up)
        signIn = findViewById(R.id.tv_sign_in)

        signUp.setOnClickListener {
            if (password.text.toString() == confirmPassword.text.toString()) {
                Toast.makeText(
                    this,
                    "You have successfully registered.\nPlease Log In now",
                    Toast.LENGTH_SHORT
                ).show()
                closeActivity(email.text.toString(), password.text.toString())
            } else {
                Toast.makeText(this, "You entered wrong password\nPlease try again", Toast.LENGTH_SHORT).show()
                password.setText("")
                confirmPassword.setText("")
            }
        }

        signIn.setOnClickListener {
            finish()
        }

    }

    private fun closeActivity(email: String, password: String) {
        val intent = Intent()
        intent.putExtra("email", email)
        intent.putExtra("password", password)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}