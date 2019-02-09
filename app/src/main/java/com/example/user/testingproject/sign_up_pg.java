package com.example.user.testingproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class sign_up_pg extends AppCompatActivity {

    EditText signup_email,signup_pass;
    Button signup_btn;

    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_pg);

        mauth = FirebaseAuth.getInstance();

    signup_email= (EditText)findViewById(R.id.mail_id);
    signup_pass = (EditText)findViewById(R.id.password_id);
    signup_btn = (Button)findViewById(R.id.signup_btn_id);

    signup_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String email ,pass;

            email =signup_email.getText().toString();
            pass = signup_pass.getText().toString();

            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){

                mauth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Intent i = new Intent(getApplicationContext(),homepage.class);
                            startActivity(i);
                            finish();
                        }

                        else{
                            String e = task.getException().getMessage();
                            Toast.makeText(getApplicationContext(),"Error"+e, Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
            else{

                Toast.makeText(getApplicationContext(),"Please Enter Some Value" , Toast.LENGTH_SHORT).show();


            }

        }
    });

    }
}
