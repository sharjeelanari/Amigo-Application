package com.example.collegeappfirstassemble.OtpSignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.collegeappfirstassemble.HomeMain.HomePage;
import com.example.collegeappfirstassemble.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button Getotp;
    EditText mobile;
    Boolean ifClub;
    TextView clublogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        DatabaseReference userCheck = FirebaseDatabase.getInstance().getReference("AllUsers")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//
//
//            userCheck.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()){
//                        Intent intent = new Intent(MainActivity.this, HomePage.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                    }else {
//                        Intent intent = new Intent(MainActivity.this, SignUp.class);
//                        startActivity(intent);
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });






        Getotp = (Button) findViewById(R.id.getotp);
        mobile = (EditText) findViewById(R.id.mobile);
        clublogin = findViewById(R.id.clublogin);
        final ProgressBar progressBar = findViewById(R.id.progressbar_mobile);

        ifClub = false;


        clublogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ClubLogin.class));
            }
        });

//        String emailtxt = email.getText().toString().trim();

//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        Intent intent = getIntent();
//
//        if (intent.getData() != null) {
//            String emailink = intent.getData().toString();
//
//
//            if (auth.isSignInWithEmailLink(emailink)) {
//                auth.signInWithEmailLink(emailtxt, emailink).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        Toast.makeText(MainActivity.this, "Signed in", Toast.LENGTH_SHORT).show();
//
//                        if (authResult.getAdditionalUserInfo().isNewUser()) {
//
//
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        }
//
//        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder().setHandleCodeInApp(true)
//                .setUrl("play.google.com")
//                .setAndroidPackageName("com.example.collegeappfirstassemble", true, "12")
//                .build();

        Getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mobile.getText().toString().trim().isEmpty()) {
                    if ((mobile.getText().toString().trim()).length() == 10) {

                        progressBar.setVisibility(View.VISIBLE);
                        Getotp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + mobile.getText().toString().trim(), 60, TimeUnit.SECONDS, MainActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        Getotp.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        Getotp.setVisibility(View.VISIBLE);
                                        Toast.makeText(MainActivity.this, e.getMessage() + "Verification failed", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        Getotp.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(getApplicationContext(), otp.class);
                                        intent.putExtra("mobile", mobile.getText().toString().trim());
                                        intent.putExtra("backendotp", backendotp);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    }

                                }
                        );


                    } else {
                        Toast.makeText(MainActivity.this, "Enter correct mobile number", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Enter your mobile number", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser != null){
            startActivity(new Intent(this, HomePage.class));
        }

    }
}


//                if (TextUtils.isEmpty(emailtxt)) {
//
//                    if (emailtxt.contains("@jainuniversity.ac.in")) {
//
//                        FirebaseAuth auth = FirebaseAuth.getInstance();
//                        auth.sendSignInLinkToEmail(emailtxt, actionCodeSettings).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(MainActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
//
//
//
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(MainActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//
//                    }
//                }else{
//
//                    email.setError("Email cannot be empty");
//                    email.requestFocus();
//
//
//                }
//