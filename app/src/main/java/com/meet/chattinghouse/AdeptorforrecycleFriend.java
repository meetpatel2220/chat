package com.meet.chattinghouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdeptorforrecycleFriend extends RecyclerView.Adapter<AdeptorforrecycleFriend.ViewHolder> {

    private View mMainView;

    private Context fcontext;
    DatabaseReference mUsersDatabase;
    FirebaseAuth mAuth;
    private LinearLayoutManager mLayoutManager;
    private  List<Uidmodel> fupload=new ArrayList<>() ;


    List<Integer> xx = new ArrayList<>();
    List<String> name1 = new ArrayList<>();
    ArrayList<String> status1 = new ArrayList<>();

    String[] n = new String[2];

    public AdeptorforrecycleFriend(Context context,List<Uidmodel> fupload1) {

        fcontext = context;
        fupload=fupload1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {


        View view = LayoutInflater.from(fcontext).inflate(R.layout.users_single_layout, parent, false);


        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        // holder.t1.setText(name1);

        //  holder.t2.setText((CharSequence) status1);
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mUsersDatabase.child(fupload.get(position).getUid().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 String userName = dataSnapshot.child("name").getValue().toString();

                 holder.setName(userName);

                String status = dataSnapshot.child("status").getValue().toString();

                holder.setStatus(status);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

    @Override
    public int getItemCount() {
        return 4;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public void setName(String name) {

            TextView userNameView = mView.findViewById(R.id.name);
            userNameView.setText(name);

        }

        public void setStatus(String status) {

            TextView userStatus = mView.findViewById(R.id.status);
            userStatus.setText(status);

        }

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;


        }
    }
}

