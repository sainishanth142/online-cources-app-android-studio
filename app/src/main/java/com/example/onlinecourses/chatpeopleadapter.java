package com.example.onlinecourses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class chatpeopleadapter extends RecyclerView.Adapter<chatpeopleadapter.viewholder> {
    ArrayList<chatpeoplemodel> list;
    Context context;

    public chatpeopleadapter(ArrayList<chatpeoplemodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.chatpeopleitem,parent,false);
        return new viewholder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        chatpeoplemodel details=list.get(position);
        holder.peoplename.setText(details.getName());
        Picasso.get().load(details.getImage()).into(holder.peopleimage);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,chatting.class);
                intent.putExtra("userid",details.getId());
                intent.putExtra("username",details.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView peopleimage;
        TextView peoplename;
        ConstraintLayout layout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            peopleimage=itemView.findViewById(R.id.peopleimage);
            peoplename=itemView.findViewById(R.id.peoplename);
            layout=itemView.findViewById(R.id.chatpeopleitemlayout);

        }
    }
}
