package com.meet.chattinghouse;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {

    private View mMainView;

    RecyclerView rv;
    private Context fcontext;
    DatabaseReference mUsersDatabase;
    FirebaseAuth mAuth;
    private LinearLayoutManager mLayoutManager;
    List<Uidmodel> listdata = new ArrayList<>();
    AdeptorforrecycleFriend adaptor;

    ArrayList<String> list=new ArrayList<>();

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView= inflater.inflate(R.layout.fragment_friends, container, false);

       rv=mMainView.findViewById(R.id.users_list);

        mLayoutManager = new LinearLayoutManager(getContext());

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));



//        AdeptorforrecycleFriend aff=new AdeptorforrecycleFriend(getActivity());
//        rv.setAdapter(aff);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference mfriend = FirebaseDatabase.getInstance().getReference().child("Friends");
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        String sa = mAuth.getCurrentUser().getUid().toString();

        mfriend.child(sa).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ss : dataSnapshot.getChildren()) {

                    Uidmodel uidmodel = ss.getValue(Uidmodel.class);
                    listdata.add(uidmodel);


                }

                adaptor = new AdeptorforrecycleFriend(getContext(), listdata);
                rv.setAdapter(adaptor);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Toast.makeText(getContext(), list.size(), Toast.LENGTH_SHORT).show();


        return mMainView;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
