package com.example.collegeappfirstassemble.SocialMain.AddPost;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import com.example.collegeappfirstassemble.R;
import com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses.SocialAddpostModel;
import com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.HomemainFragment;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialActivity;
import com.example.collegeappfirstassemble.SocialMain.Social1.SocialHomeModelAndAdapter.SocialActivityAdapter;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Social_addpost extends Fragment {
    private ImageView addimage;
    private EditText description,heading;
    private ProgressDialog progressDialog;
    TextView attach;

//    VideoView videoView;
//    EditText description;
    Button post;
    Uri uri, uri2;

    SocialAddpostModel socialAddpostModel;

    StorageReference storageReference;
    DatabaseReference db, dbuser;



    public Social_addpost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_social_addpost, container, false);

        addimage = view.findViewById(R.id.add_post_image);
        description = view.findViewById(R.id.add_postdescription_details);
        progressDialog = new ProgressDialog(getContext());
        attach = view.findViewById(R.id.attachments);
//        videoView = view.findViewById(R.id.video);
//        description = view.findViewById(R.id.add_postdescription_details);
        post = view.findViewById(R.id.socialpostbutton);
        heading = view.findViewById(R.id.editheading);



        socialAddpostModel = new SocialAddpostModel();

        db = FirebaseDatabase.getInstance().getReference("SocialPosts");
        db.keepSynced(true);
        storageReference = FirebaseStorage.getInstance().getReference();



        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Social_addpost.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

//                Intent intent = new Intent();
//                intent.setType("video/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent, 1);
            }
        });


        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        dbuser = FirebaseDatabase.getInstance().getReference("UserSocialPosts").child(userid);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Desc = description.getText().toString().trim();
                String Timestamp = String.valueOf(System.currentTimeMillis());
                String headingtext = heading.getText().toString().trim();

                if(headingtext != null){
                    if (Desc != null || uri != null) {
                        uploadDetailstodb1(uri2, uri, Desc, Timestamp, headingtext, userid);
                    }else{
                        Toast.makeText(getContext(), "Both image and description can't be null", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Heading is necessary", Toast.LENGTH_SHORT).show();
                }
            }
        });

        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, 2);
            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==2){
            uri2 = data.getData();

            if (uri2 != null){
                attach.setText("Attached!");
            }

        }else {
            uri = data.getData();
            addimage.setImageURI(uri);
        }
    }


    //this method uploads data to the database
    private void uploadDetailstodb1(Uri muri2, Uri myuri, String Desc, String Timestamp, String heading, String userid){
        progressDialog.setMessage("Your post is being uploaded");
        progressDialog.show();

        if (uri!=null) {


            //here we are taking storage reference and creating a separate child in it with the name o image upload time and extension

            StorageReference fileref = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(myuri));



            fileref.putFile(myuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //here we are downloading the url of the image and adding on success listener to go forward and the url to the db
                    fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
//                            socialAddpostModel.setPostDescription(Desc);
//                            socialAddpostModel.setTime(Timestamp);
//                            socialAddpostModel.setImageurl(uri.toString());
//                            socialAddpostModel.setHeading(heading);
//                            socialAddpostModel.setUserid(userid);

                            if (uri2!=null){
                                StorageReference attachref = storageReference.child(System.currentTimeMillis()+ "." +
                                        getFileExtension(muri2));

                                socialAddpostModel.setImageurl(uri.toString());
                                attachref.putFile(muri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        attachref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                socialAddpostModel.setFileuri(uri.toString());
                                                socialAddpostModel.setPostDescription(Desc);
                                                socialAddpostModel.setTime(Timestamp);

                                                socialAddpostModel.setHeading(heading);
                                                socialAddpostModel.setUserid(userid);

                                                String modelid = db.push().getKey();

                                                db.child(modelid).setValue(socialAddpostModel);
                                                dbuser.child(modelid).setValue(socialAddpostModel);
                                                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();


                                                startActivity(new Intent(getContext(), SocialActivity.class));

                                                progressDialog.dismiss();
                                            }
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(), "Failed to add file", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                socialAddpostModel.setPostDescription(Desc);
                                socialAddpostModel.setTime(Timestamp);
                                socialAddpostModel.setImageurl(uri.toString());
                                socialAddpostModel.setHeading(heading);
                                socialAddpostModel.setUserid(userid);

                                String modelid = db.push().getKey();

                                db.child(modelid).setValue(socialAddpostModel);
                                dbuser.child(modelid).setValue(socialAddpostModel);
                                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();


                                startActivity(new Intent(getContext(), SocialActivity.class));

                                progressDialog.dismiss();
                            }



                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();

                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }else{

            if (uri2 != null) {

                StorageReference attachref = storageReference.child(System.currentTimeMillis()+ "." +
                        getFileExtension(muri2));

                attachref.putFile(muri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        attachref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                socialAddpostModel.setPostDescription(Desc);
                                socialAddpostModel.setTime(Timestamp);
                                socialAddpostModel.setHeading(heading);
                                socialAddpostModel.setUserid(userid);
                                socialAddpostModel.setFileuri(uri.toString());

                                String modelid = db.push().getKey();

                                db.child(modelid).setValue(socialAddpostModel);
                                dbuser.child(modelid).setValue(socialAddpostModel);

                                startActivity(new Intent(getContext(), SocialActivity.class));

                                progressDialog.dismiss();
                            }
                        });



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                    }
                });

            }else{
                socialAddpostModel.setPostDescription(Desc);
                socialAddpostModel.setTime(Timestamp);
                socialAddpostModel.setHeading(heading);
                socialAddpostModel.setUserid(userid);

                String modelid = db.push().getKey();

                db.child(modelid).setValue(socialAddpostModel);
                dbuser.child(modelid).setValue(socialAddpostModel);

                startActivity(new Intent(getContext(), SocialActivity.class));

                progressDialog.dismiss();

            }


        }

    }
    //Method to get the extension of the image
    private String getFileExtension(Uri muri){

        ContentResolver cr = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cr.getType(muri));
    }
}