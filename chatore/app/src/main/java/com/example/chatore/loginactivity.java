package com.example.chatore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {
    private TextInputEditText etEmail ,etPassword ;
    private String email , password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        etEmail = findViewById(R.id.etEmail);
        etPassword= findViewById(R.id.etPassword);
    }


    public void tvSignupClick(View v)
    {
        startActivity(new Intent(this , signupactivity.class));
    }
    public void btnLoginClick(View v)
    {
        email = etEmail.getText().toString().trim();
        password= etPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            etEmail.setError(getString(R.string.enter_email));

        }
        else if (password.isEmpty())
        {
            etPassword.setError(getString(R.string.enter_password));
        }
        else
        {
            FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                    }
                    else
                    {
                        Toast.makeText(loginactivity.this, "Failure"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}