package com.example.collegeappfirstassemble.OtpSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeappfirstassemble.HomeMain.HomePage;
import com.example.collegeappfirstassemble.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ClubLogin extends AppCompatActivity {
    EditText email, password;
    Button login;
    TextView register;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_login);

        email = findViewById(R.id.clbemail);
        password = findViewById(R.id.clbpass);
        login = findViewById(R.id.clbloginbtn);
        register = findViewById(R.id.createclub);
        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.clbloginprogress);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtxt = email.getText().toString().trim();
                String passwordtxt = password.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);
                login.setVisibility(View.INVISIBLE);

                signin(emailtxt, passwordtxt);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.go" +
                        "ogle.com/forms/d/e/1FAIpQLSfB6WYkKDq3S1I7LIm4jbeSM7dEcQXalVftIc0Q-JoOp4LQIw/viewform?embedded=true"));

                startActivity(intent);

            }
        });

    }

    private void signin(String emailtxt, String passwordtxt) {
        if (TextUtils.isEmpty(emailtxt)){
            email.setError("Email can't be empty");
            email.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
        }else if (TextUtils.isEmpty(passwordtxt)){
            password.setError("password is empty");
            password.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
        }else{

            auth.signInWithEmailAndPassword(emailtxt, passwordtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startActivity(new Intent(ClubLogin.this, HomePage.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    }else{
                        Toast.makeText(ClubLogin.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        login.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}