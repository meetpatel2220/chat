package com.meet.chattinghouse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import com.theartofdev.edmodo.cropper.CropImage;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SettingActivity extends AppCompatActivity {

    private Toolbar mToolbar;


    public static final int GELLRY_PIC = 1;
    private StorageReference sr;

    private ImageView profile_pic;
    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;
    private TextView displayname, userstatus;
    private Button changestatus, changeimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        mToolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");


        sr = FirebaseStorage.getInstance().getReference();

        changeimage = findViewById(R.id.B_changeimage);

        changestatus = findViewById(R.id.b_changestatus);

        displayname = findViewById(R.id.Display_name);
        userstatus = findViewById(R.id.user_Status);
        profile_pic = findViewById(R.id.profile_pic);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        String current_uid = mCurrentUser.getUid();

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
        mUserDatabase.keepSynced(true);


        changeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent gatllryIntent = new Intent();
                gatllryIntent.setType("image/*");
                gatllryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gatllryIntent, "SELECT IMAGE"), GELLRY_PIC);


            }
        });

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String name = dataSnapshot.child("name").getValue().toString();
                String status = dataSnapshot.child("status").getValue().toString();

                displayname.setText(name);

                userstatus.setText(status);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        changestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status_value = userstatus.getText().toString();

                Intent status_intent = new Intent(SettingActivity.this, StatusActivity.class);
                status_intent.putExtra("status_value", status_value);
                startActivity(status_intent);


            }
        });

    }
}
