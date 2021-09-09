package ca.bcit.localhost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    EditText emailEdit, passwordEdit;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        loginBtn = findViewById(R.id.loginUser);

        emailEdit = findViewById(R.id.email_input);
        passwordEdit = findViewById(R.id.pass_input);
        fAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailInput = emailEdit.getText().toString().trim();
                String passwordInput = passwordEdit.getText().toString().trim();



//                if (TextUtils.isEmpty(emailInput)) {
//                    emailEdit.setError("Please provide an Email Address");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(passwordInput)) {
//                    passwordEdit.setError("Please provide a corresponding password to your Email");
//                    return;
//                }
//
//                if (passwordInput.length() <= 8) {
//                    passwordEdit.setError("Password should be at least 8 characters long");
//                    return;
//                }


//                fAuth.signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(LoginActivity.this, "User Logged In!", Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        } else {
//                            Toast.makeText(LoginActivity.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
////                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//                });

                // REMOVE THIS
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
    public void moveToRegister(View v) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}