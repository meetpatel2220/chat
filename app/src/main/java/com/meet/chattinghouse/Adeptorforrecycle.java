package com.meet.chattinghouse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Adeptorforrecycle extends RecyclerView.Adapter<Adeptorforrecycle.ViewHolder> {


    private  Context fcontext;
    private  List<Users> fupload=new ArrayList<>() ;


   public void add(Users s){
       fupload.add(s);
   }

    public Adeptorforrecycle(Context context,List<Users> user){

        fcontext=context;
        fupload=user;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(fcontext).inflate(R.layout.users_single_layout,parent,false);

        return new ViewHolder(view);


    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.t1.setText(fupload.get(position).getName());

        holder.t2.setText(fupload.get(position).getStatus());

        final String x=fupload.get(position).getMyuid();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(fcontext, x, Toast.LENGTH_SHORT).show();
                Intent in=new Intent(fcontext,ProfileActivity.class);
                in.putExtra("others_uid",x);
                fcontext.startActivity(in);


            }
        });



    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.name);
            t2=itemView.findViewById(R.id.status);



        }
    }
}

