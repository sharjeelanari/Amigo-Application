package com.example.collegeappfirstassemble.Subscription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.Subscription.SubAdapModel.SubRecViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostSubAddActivity<SubRecViewModel> extends AppCompatActivity {


    com.example.collegeappfirstassemble.Subscription.SubAdapModel.SubRecViewModel subRecViewModel;

    DatabaseReference databaseReference;

    EditText addcompanyname,addscreentype,adddevices,add_mem_no_available,add_mem_needed,add_priceofsub;
    Button postad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_sub_add_activity);

        addcompanyname = (EditText)findViewById(R.id.addcompanyname);
        addscreentype=(EditText)findViewById(R.id.addscreentype);
        adddevices=(EditText)findViewById(R.id.adddevices);
        add_mem_no_available=(EditText)findViewById(R.id.add_mem_no_avaiable);
        add_mem_needed=(EditText)findViewById(R.id.add_mem_needed);
        add_priceofsub=(EditText)findViewById(R.id.add_priceofsub);
        postad=(Button)findViewById(R.id.postad);

        subRecViewModel =
                new com.example.collegeappfirstassemble.Subscription.SubAdapModel.SubRecViewModel();

        postad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String comp_name = addcompanyname.getText().toString();
                String screen_type = addscreentype.getText().toString();
                String devices = adddevices.getText().toString();
                String memavailable = add_mem_no_available.getText().toString();
                String memneeded = add_mem_needed.getText().toString();
                String price = add_priceofsub.getText().toString();

                if (comp_name.trim().isEmpty() && screen_type.trim().isEmpty() && devices.trim().isEmpty() &&
                        memavailable.trim().isEmpty() && memneeded.trim().isEmpty() && price.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_LONG).show();
                }

                else {
                      databaseReference= FirebaseDatabase.getInstance().getReference("Subscriptionpostadded").child(comp_name);
                      addtodatabse(comp_name,screen_type,devices,memavailable,memneeded,price);
                    Intent intent=new Intent(PostSubAddActivity.this, SubscriptionActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

        });

    }

    private void addtodatabse(String comp_name, String screen_type, String devices, String memavailable, String memneeded, String price) {
        subRecViewModel.setAddcompanyname(comp_name);
        subRecViewModel.setAddscreentype(screen_type);
        subRecViewModel.setAdddevices(devices);
        subRecViewModel.setAdd_mem_no_available(memavailable);
        subRecViewModel.setAdd_mem_needed(memneeded);
        subRecViewModel.setAdd_priceofsub(price);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(subRecViewModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}