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

public class MainActivity extends AppCompatActivity {

    EditText email,pass,sign_up;
    Button login;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.mail);
        pass = (EditText)findViewById(R.id.password);
        sign_up = (EditText)findViewById(R.id.signup);
        login = (Button)findViewById(R.id.login_id);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o = new Intent(getApplicationContext() , sign_up_pg.class);
                startActivity(o);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sign_mail , sign_pass;
                sign_mail = email.getText().toString();
                sign_pass = pass.getText().toString();


                if(!TextUtils.isEmpty(sign_mail)&& !TextUtils.isEmpty(sign_pass)){

                    mAuth.signInWithEmailAndPassword(sign_mail,sign_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                Intent i = new Intent(getApplicationContext(),homepage.class);
                                startActivity(i);
                                finish();

                            }

                            else{
                                String error = task.getException().getMessage() ;
                                Toast.makeText(getApplicationContext(),"Error"+error , Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

                else{

                    Toast.makeText(getApplicationContext() , "please Enter some value" , Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
