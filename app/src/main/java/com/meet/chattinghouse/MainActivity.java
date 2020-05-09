package com.meet.chattinghouse;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    ViewPager viewPager;
    PagerViewAdaptor pagerViewAdaptor;

    TextView request,chat,friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Chatting House");

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
request=findViewById(R.id.requests);
chat=findViewById(R.id.chats);
friend=findViewById(R.id.friends);

        viewPager=findViewById(R.id.viewpager);
        pagerViewAdaptor=new PagerViewAdaptor(getSupportFragmentManager(),1);
        viewPager.setAdapter(pagerViewAdaptor);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);


            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);


            }
        });

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);



            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int position) {

                onChangeTab(position);


            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.main_logout_btn){


            FirebaseAuth.getInstance().signOut();
              Intent in=new Intent(MainActivity.this,StartActivity.class);
              startActivity(in);
              finish();


        }

        if(item.getItemId() == R.id.main_settings_btn){

            Intent settingsIntent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(settingsIntent);

        }

        if(item.getItemId() == R.id.main_all_btn){

            Intent settingsIntent = new Intent(MainActivity.this, UsersActivity.class);
            startActivity(settingsIntent);

        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onChangeTab(int position) {

        if(position==0){


            request.setTextSize(25);
            request.setTextColor(getColor(R.color.bright_color));

            chat.setTextSize(20);
            chat.setTextColor(getColor(R.color.light_color));

            friend.setTextSize(20);
            friend.setTextColor(getColor(R.color.light_color));


        }
        if(position==1){


            request.setTextSize(20);
            request.setTextColor(getColor(R.color.light_color));

            chat.setTextSize(25);
            chat.setTextColor(getColor(R.color.bright_color));

            friend.setTextSize(20);
            friend.setTextColor(getColor(R.color.light_color));


        }
        if(position==2){


            request.setTextSize(20);
            request.setTextColor(getColor(R.color.light_color));

            chat.setTextSize(20);
            chat.setTextColor(getColor(R.color.light_color));

            friend.setTextSize(25);
            friend.setTextColor(getColor(R.color.bright_color));


        }


    }


}
