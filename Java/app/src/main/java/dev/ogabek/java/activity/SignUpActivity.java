package dev.ogabek.java.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dev.ogabek.java.R;

public class SignUpActivity extends AppCompatActivity {

    EditText fullName, email, password, confirmPassword;
    Button signUp;
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
    }

    private void initViews() {
        fullName = findViewById(R.id.et_full_name);
        email = findViewById(R.id.et_new_email);
        password = findViewById(R.id.et_new_password);
        confirmPassword = findViewById(R.id.et_confirm_password);

        signUp = findViewById(R.id.btn_sign_up);
        signIn = findViewById(R.id.tv_sign_in);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "You have successfully registered.\nPlease Log In now", Toast.LENGTH_SHORT).show();
                    closeActivity(email.getText().toString(), password.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "You entered wrong password\nPlease try again", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    confirmPassword.setText("");
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void closeActivity(String email, String password) {
        Intent intent = new Intent();
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}