package com.example.collegeappfirstassemble.OtpSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeappfirstassemble.HomeMain.HomePage;
import com.example.collegeappfirstassemble.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {
    Button submit;
    EditText otp;
    TextView mobilenumber;
    TextView resendotp;
    ProgressBar progressBarOtp;
    String getbackendotp;
    private DatabaseReference databaseReference;
    private DatabaseReference datbaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        submit = (Button) findViewById(R.id.submitotp);
        otp = (EditText) findViewById(R.id.otp);
        mobilenumber = (TextView) findViewById(R.id.mobilenumber);
        resendotp = (TextView) findViewById(R.id.resendotp);
        progressBarOtp = (ProgressBar) findViewById(R.id.progressbar_otp);


        String mobile = getIntent().getStringExtra("mobile");

        //Here we are pointing towards the database and checking mobile number inside required branch of db
        //and then value event listener is added inside the if statement below which check the number
        //and acts accordingly

        databaseReference = FirebaseDatabase.getInstance().getReference("StudentInfo");
        datbaseReference1 = FirebaseDatabase.getInstance().getReference("TeacherInfo");

        getbackendotp = getIntent().getStringExtra("backendotp");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking otp after tapping submit button
                if (!otp.getText().toString().trim().isEmpty()) {
                    if ((otp.getText().toString().trim()).length() == 6) {
                        String enterotp = otp.getText().toString();
                        if (getbackendotp != null) {
                            progressBarOtp.setVisibility(View.VISIBLE);
                            submit.setVisibility(View.INVISIBLE);

                            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getbackendotp, enterotp);

                            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {



                                            if (task.isSuccessful()) {
                                                progressBarOtp.setVisibility(View.GONE);
                                                submit.setVisibility(View.VISIBLE);

                  //if otp is verified succesfully then check whether the mobile number is in the database and if not then
                  //take the mobile number to Signup activity and then store it in DB there.
                 //If the mobile number exists then move to home page

                                                ValueEventListener eventListener = new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.hasChild(mobile)) {
                                                            Intent intent = new Intent(otp.this, HomePage.class);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                            startActivity(intent);
                                                        }else{

                                                            datbaseReference1.addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if (snapshot.hasChild(mobile)){
                                                                        Intent intent = new Intent(otp.this, HomePage.class);
                                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                        startActivity(intent);
                                                                    }else{
                                                                        Intent intent = new Intent(otp.this, SignUp.class);
                                                                        intent.putExtra("mobile", mobile);
                                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                        startActivity(intent);
                                                                    }
                                                                  }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });


                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {
                                                        Toast.makeText(otp.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                };
                                                databaseReference.addListenerForSingleValueEvent(eventListener);

                                            } else {
                                                Toast.makeText(com.example.collegeappfirstassemble.OtpSignup.otp.this, "Incorrect OTP entered.", Toast.LENGTH_LONG).show();
                                                progressBarOtp.setVisibility(View.GONE);
                                                submit.setVisibility(View.VISIBLE);
                                            }
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(com.example.collegeappfirstassemble.OtpSignup.otp.this, "Enter 6 digit otp", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(com.example.collegeappfirstassemble.OtpSignup.otp.this, "Enter an otp", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mobilenumber.setText(String.format("+91-%s", getIntent().getStringExtra("mobile")));
//        mobilenumber.setText("+91-");

        //Resends otp
        TextView resendlabel = findViewById(R.id.resendotp);
        resendlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"), 60, TimeUnit.SECONDS, com.example.collegeappfirstassemble.OtpSignup.otp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(com.example.collegeappfirstassemble.OtpSignup.otp.this, "Verification failed", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newotpbackend, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getbackendotp = newotpbackend;
                                Toast.makeText(com.example.collegeappfirstassemble.OtpSignup.otp.this, "New OTP sent", Toast.LENGTH_LONG).show();
                            }
                        }
                );
            }
        });


    }
}