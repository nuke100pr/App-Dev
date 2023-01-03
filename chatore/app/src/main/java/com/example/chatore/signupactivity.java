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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.HashMap;

public class signupactivity extends AppCompatActivity {

    private TextInputEditText etName2 ,etEmail2,etPassword2,confirmPassword2;
    private String name ,email , password ,confirmPassword ;
    private FirebaseUser firebaseuser ;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        etEmail2 = findViewById(R.id.etEmail2);
        etName2 = findViewById(R.id.etName2);
        etPassword2= findViewById(R.id.etPassword2);
        confirmPassword2 = findViewById(R.id.confirmPassword2);


    }
    public void updateOnlyName()
    {
        UserProfileChangeRequest  request = new UserProfileChangeRequest.Builder()
                .setDisplayName(etName2.getText().toString().trim())
                .build();
        firebaseuser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                     String userID = firebaseuser.getUid();
                     databaseReference= FirebaseDatabase.getInstance().getReference().child(nodenames.USERS);

                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put(nodenames.NAME,etName2.getText().toString().trim());
                    hashMap.put(nodenames.EMAIL,etEmail2.getText().toString().trim());
                    hashMap.put(nodenames.ONLINE,"true");
                    hashMap.put(nodenames.PHOTO,"");

                    databaseReference.child(userID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(signupactivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signupactivity.this,loginactivity.class));

                        }
                    });

                }
                else
                {
                    Toast.makeText(signupactivity.this, getString(R.string.failed_to_update_profile,task.getException()), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
       else if(password.length()<6)
       {
           confirmPassword2.setError("Min length of password is 6");
       }
       else
       {
           FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

           firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       firebaseuser = firebaseAuth.getCurrentUser();
                       updateOnlyName();
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