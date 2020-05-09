package com.meet.chattinghouse;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PagerViewAdaptor extends FragmentPagerAdapter {


    public PagerViewAdaptor(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new RequestsFragment();
                break;
            case 1:
                fragment=new ChatFragment();
                break;
            case 2:
                fragment=new FriendsFragment();
                break;


        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
