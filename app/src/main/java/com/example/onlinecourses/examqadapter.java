package com.example.onlinecourses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class examqadapter extends RecyclerView.Adapter<examqadapter.viewholder> {
    Context context;
    ArrayList<examqmodel> list;

    public examqadapter(Context context, ArrayList<examqmodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public examqadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.itemtextview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull examqadapter.viewholder holder, int position) {
        holder.tv.setText(list.get(position).getQname());
        holder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,exam.class);
                intent.putExtra("examno",list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        LinearLayout l;
        TextView tv;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.text);
            l=itemView.findViewById(R.id.lay);
        }
    }
}
