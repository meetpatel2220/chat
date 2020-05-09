package com.meet.chattinghouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Objects;

public class RagisterActivity extends AppCompatActivity {

    private EditText displayname,email,password;
    private Button done;

    private FirebaseAuth mAuth;
    private Toolbar mToolbar;

    private ProgressDialog mRegProgress;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragister);
        mAuth = FirebaseAuth.getInstance();

        displayname=findViewById(R.id.display_name);

        mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Account");


        mRegProgress = new ProgressDialog(this);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        done=findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email2 = email.getText().toString();
                String password2 = password.getText().toString();
                final String displayname2 = displayname.getText().toString();

                if (!email2.isEmpty() && !password2.isEmpty() && !displayname2.isEmpty()) {

                    mRegProgress.setTitle("Registering User");
                    mRegProgress.setMessage("Please wait while we create your account !");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();



                    mAuth.createUserWithEmailAndPassword(email2,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                assert current_user != null;
                                String uid = current_user.getUid();

                                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                                String device_token = FirebaseInstanceId.getInstance().getToken();

                                HashMap<String, String> userMap = new HashMap<>();
                                userMap.put("name", displayname2);
                                userMap.put("status", "Hi there I'm using Chatting House");
                                userMap.put("myuid", uid);
                                userMap.put("device_token", device_token);


                                mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            mRegProgress.dismiss();
                                            Intent in=new Intent(RagisterActivity.this,MainActivity.class);
                                            startActivity(in);
                                            finish();

                                        }else {

                                            mRegProgress.hide();

                                            Toast.makeText(RagisterActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();

                                        }


                                    }
                                });


                            }
                            else {

                                mRegProgress.hide();

                                Toast.makeText(RagisterActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }



            }
        });

    }
}
