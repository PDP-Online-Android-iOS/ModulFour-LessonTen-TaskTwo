package dev.ogabek.kotlin.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import dev.ogabek.kotlin.R
import java.util.*

class SignInActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signIn: Button
    private lateinit var signUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initViews()

    }

    private fun initViews() {
        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        signIn = findViewById(R.id.btn_sign_in)
        signUp = findViewById(R.id.tv_signup)

        signUp.setOnClickListener {
            callSignUpActivity()
        }

        signIn.setOnClickListener {
            checkProfile(email.text.toString(), password.text.toString())
        }

    }

    private fun checkProfile(email: String, password: String) {
        if (email.lowercase(Locale.getDefault()) == "ogabekdev@gmail.com" || password == "OgabekDev") {
            callHomeActivity()
        } else {
            Toast.makeText(this, "Something went wrong :(", Toast.LENGTH_SHORT).show()
        }
    }

    private fun callHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun callSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        resultActivity.launch(intent)
    }

    var resultActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            setReturnedData(
                data!!.getStringExtra("email").toString(),
                data.getStringExtra("password").toString()
            )
        } else {
            Toast.makeText(
                this,
                "You entered incorrect email and password :(\nPlease try again",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setReturnedData(email: String, password: String) {
        this.email.setText(email)
        this.password.setText(password)
    }

}