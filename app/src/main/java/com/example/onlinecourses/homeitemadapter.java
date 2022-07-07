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

public class homeitemadapter extends RecyclerView.Adapter<homeitemadapter.viewholder> {
    Context context;
    ArrayList<homeitemmodel> list;
    String top;
    public homeitemadapter(Context context, ArrayList<homeitemmodel> list,String top) {
        this.context = context;
        this.list = list;
        this.top=top;
    }
    @NonNull
    @Override
    public homeitemadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.homeitem,parent,false);
        return new viewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull homeitemadapter.viewholder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView);
        holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(holder.getAdapterPosition()).getAs().equals("ho")){
                Intent intent=new Intent(context,topics.class);

                intent.putExtra("topic",holder.textView.getText());
                context.startActivity(intent);
                 }else {
                    Intent intent=new Intent(context,viewtopics.class);
                    intent.putExtra("topic",top);
                    intent.putExtra("topicid",list.get(holder.getAdapterPosition()).getId());

                    intent.putExtra("subtopic",holder.textView.getText());
                    context.startActivity(intent);
                }
        }
    });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ConstraintLayout lay;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
            lay=itemView.findViewById(R.id.lay);

        }
    }
}