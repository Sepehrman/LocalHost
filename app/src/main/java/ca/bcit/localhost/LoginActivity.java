package ca.bcit.localhost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    EditText emailInput, passwordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        loginBtn = findViewById(R.id.loginUser);

        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.pass_input);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });



    }



    public void moveToRegister(View v) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}