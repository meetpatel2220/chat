package com.meet.chattinghouse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdeptorforrecycleStr extends RecyclerView.Adapter<AdeptorforrecycleStr.ViewHolder> {


    private  Context fcontext;
    private  List<String> fupload;
    private  List<String> fupload1;


   public void add(String s,String s1){
       fupload.add(s);
       fupload1.add(s1);

   }

    public AdeptorforrecycleStr(Context context, List<String> user, List<String> user1){

        fcontext=context;
        fupload=user;
        fupload1=user1;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(fcontext).inflate(R.layout.users_single_layout,parent,false);

        return new ViewHolder(view);


    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


       holder.t1.setText(fupload.get(position));
        holder.t2.setText(fupload1.get(position));




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

