package dev.ogabek.java.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import dev.ogabek.java.R;

public class SignInActivity extends AppCompatActivity {

    EditText email, password;
    Button signIn;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();

    }

    private void initViews() {
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        signIn = findViewById(R.id.btn_sign_in);
        signUp = findViewById(R.id.tv_signup);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkProfile(email.getText().toString(), password.getText().toString());
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callSignUpActivity();
            }
        });

    }

    private void checkProfile(String email, String password) {
        if (email.toLowerCase().equals("ogabekdev@gmail.com") || password.equals("OgabekDev")) {
            callHomeActivity();
        } else {
            Toast.makeText(this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void callHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void callSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        resultActivity.launch(intent);
    }

    ActivityResultLauncher<Intent> resultActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            assert data != null;
            setReturnedData(data.getStringExtra("email"), data.getStringExtra("password"));
        } else {
            Toast.makeText(
                    this,
                    "You entered incorrect email and password :(\nPlease try again",
                    Toast.LENGTH_LONG
            ).show();
        }
    });

    private void setReturnedData(String email, String password) {
        this.email.setText(email);
        this.password.setText(password);
    }
}