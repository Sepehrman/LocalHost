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

public class RegisterActivity extends AppCompatActivity {

    Button registerBtn;
    EditText firstnameEdit, lastnameEdit, emailEdit, passwordEdit, confirmPassEdit;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();


        registerBtn = findViewById(R.id.registerUser);
        firstnameEdit = findViewById(R.id.firstnameInput);
        lastnameEdit = findViewById(R.id.lastnameInput);
        emailEdit = findViewById(R.id.emailInput);
        passwordEdit = findViewById(R.id.passwordInput);
        confirmPassEdit = findViewById(R.id.passConfirmInput);

        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailInput = emailEdit.getText().toString().trim();
                String passInput = passwordEdit.getText().toString().trim();
                String passConfirm = confirmPassEdit.getText().toString().trim();


                if (TextUtils.isEmpty(emailInput)) {
                    emailEdit.setError("Email is Required");
                }
                if (TextUtils.isEmpty(passInput)) {
                    passwordEdit.setError("Password is Empty");
                }

                if (TextUtils.isEmpty(passConfirm)) {
                    confirmPassEdit.setError("Confirm Password should not be empty");
                }

                if (!passInput.equals(passConfirm)) {
                    confirmPassEdit.setError("Should be matching with password Confirmation");
                    confirmPassEdit.setError("Should be matching with password");
                }

                firebaseAuth.createUserWithEmailAndPassword(emailInput, passInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registration is successful!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

    }


    public void moveToLogin(View v) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }




}