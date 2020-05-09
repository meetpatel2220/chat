package com.meet.chattinghouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

   private Button mRagBtn,mloginBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mRagBtn=findViewById(R.id.start_rag_btn);
        mloginBtn=findViewById(R.id.start_login_btn);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser!=null){

            Intent in=new Intent(StartActivity.this,MainActivity.class);
            startActivity(in);
            finish();
        }

        mRagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(StartActivity.this,RagisterActivity.class);
                startActivity(in);
                finish();


            }
        });


        mloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(StartActivity.this,LoginActivity.class);
                startActivity(in);
                finish();


            }
        });



    }
}
