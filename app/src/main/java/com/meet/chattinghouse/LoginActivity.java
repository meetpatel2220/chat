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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button done;
    private ProgressDialog mRegProgress;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mRegProgress = new ProgressDialog(this);

        mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");



        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        done=findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                if (email1.isEmpty() || password1.isEmpty()) {

                    Toast.makeText(LoginActivity.this, "Please Fil-up Detail", Toast.LENGTH_SHORT).show();

                } else {



                    mRegProgress.setTitle("Loging In");
                    mRegProgress.setMessage("Please wait while we create your account !");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();


                    mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mRegProgress.dismiss();
                            String deviceToken = FirebaseInstanceId.getInstance().getToken();
                            String current_user_id = mAuth.getCurrentUser().getUid();

                            mUserDatabase.child(current_user_id).child("device_token").setValue(deviceToken).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent in=new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(in);
                                    finish();

                                    Toast.makeText(LoginActivity.this, "done", Toast.LENGTH_SHORT).show();

                                }
                            });

                        } else {

                            mRegProgress.hide();

                            Toast.makeText(LoginActivity.this, "Somthing went wrong", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }



            }
        });


    }
}
