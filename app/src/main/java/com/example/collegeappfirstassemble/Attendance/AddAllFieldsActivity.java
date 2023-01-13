package com.example.collegeappfirstassemble.Attendance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeappfirstassemble.Attendance.AdapterAndModel.BatchModel;
import com.example.collegeappfirstassemble.Attendance.AdapterAndModel.StudentModel;
import com.example.collegeappfirstassemble.Attendance.AdapterAndModel.SubjectModel;
import com.example.collegeappfirstassemble.Attendance.AdapterAndModel.TeacherModel;
import com.example.collegeappfirstassemble.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddAllFieldsActivity extends AppCompatActivity {

    EditText addstudentname;
    EditText addstudentbatch;
    EditText addstudentusn;
    EditText teachername;
    EditText sem;
    EditText subject;
    Button addstuddetailsbtn;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    DatabaseReference databaseReference2;
    DatabaseReference databaseReference3;


    Context context;

    StudentModel studentModel;
    SubjectModel subjectModel;
    TeacherModel teacherModel;
    BatchModel batchModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addallfieldsactivity);

        addstudentname = (EditText) findViewById(R.id.addstudentname);
        addstudentbatch = (EditText) findViewById(R.id.addstudentbatch);
        addstudentusn = (EditText) findViewById(R.id.addstudentusn);
        teachername = (EditText) findViewById(R.id.addteachername);
        sem = (EditText) findViewById(R.id.addsemester);
        subject = (EditText) findViewById(R.id.addsubject);
        addstuddetailsbtn = (Button) findViewById(R.id.addstuddetailsbtn);

        studentModel = new StudentModel();
        subjectModel = new SubjectModel();
        teacherModel = new TeacherModel();
        batchModel = new BatchModel();

        addstuddetailsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saddname = addstudentname.getText().toString();
                String addbatch = addstudentbatch.getText().toString();
                String saddusn = addstudentusn.getText().toString();
                String teacher = teachername.getText().toString();
//                String semester = sem.getText().toString();
                String sub = subject.getText().toString();

                if (saddname.trim().isEmpty() && addbatch.trim().isEmpty() && saddusn.trim().isEmpty() && teacher.trim().isEmpty() &&
                        sub.trim().isEmpty()) {
                    Toast.makeText(context, "Fill all the fields", Toast.LENGTH_LONG);

                } else {
                    databaseReference = FirebaseDatabase.getInstance().getReference("teacherName").child(addbatch).child(saddusn);
                    databaseReference1 = FirebaseDatabase.getInstance().getReference("TeacherBatches").child("teacherName").child(addbatch);
//                    databaseReference2 = databaseReference1.child()
                    addtoattendancedatabase(saddname, addbatch, saddusn);
                    Intent intent = new Intent(AddAllFieldsActivity.this, Attendanceactivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }
        });
    }

    private void addtoattendancedatabase(String saddname, String addbatch, String saddusn) {
        studentModel.setAddstudentname(saddname);
        studentModel.setAddstudentusn(saddusn);
        studentModel.setAddBatch(addbatch);
        batchModel.setAddbatch(addbatch);

        databaseReference1.setValue(batchModel);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(studentModel);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
