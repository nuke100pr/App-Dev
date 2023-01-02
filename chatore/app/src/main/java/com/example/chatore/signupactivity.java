package com.example.chatore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupactivity extends AppCompatActivity {

    private TextInputEditText etName2 ,etEmail2,etPassword2,confirmPassword2;
    private String name ,email , password ,confirmPassword ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        etEmail2 = findViewById(R.id.etEmail2);
        etName2 = findViewById(R.id.etName2);
        etPassword2= findViewById(R.id.etPassword2);
        confirmPassword2 = findViewById(R.id.confirmPassword2);


    }

    public void btnSignupClick(View v)
    {
       email = etEmail2.getText().toString().trim();
       name = etName2.getText().toString().trim();
       password = etPassword2.getText().toString().trim();
       confirmPassword = confirmPassword2.getText().toString().trim();

       if(email.isEmpty())
       {
           etEmail2.setError(getString(R.string.enter_email));

       }
       else if (name.isEmpty())
       {
           etName2.setError(getString(R.string.enter_name));
       }
       else if(password.isEmpty())
       {
           etPassword2.setError(getString(R.string.enter_password));
       }
       else if(confirmPassword.isEmpty())
       {
           confirmPassword2.setError(getString(R.string.confirm_password));
       }
       else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
       {
           etEmail2.setError(getString(R.string.enter_correct_email));
       }
       else if(!password.equals(confirmPassword))
       {
           confirmPassword2.setError(getString(R.string.confirm_password_not_equal_to_password));
       }
       else
       {
           FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

           firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       Toast.makeText(signupactivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(signupactivity.this,loginactivity.class));

                   }
                   else
                   {
                       Toast.makeText(signupactivity.this, getString(R.string.signup_failed,task.getException()), Toast.LENGTH_SHORT).show();
                   }
               }
           });
       }
    }
}