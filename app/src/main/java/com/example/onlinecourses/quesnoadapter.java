package com.example.onlinecourses;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class quesnoadapter extends RecyclerView.Adapter<quesnoadapter.viewholder>{
    Context context;
    ArrayList<exammodel> list;
    ViewPager vp;
    int pos;
    public quesnoadapter(Context context, ArrayList<exammodel> list, ViewPager vp) {
        this.context = context;
        this.list = list;
        this.vp=vp;
    }

    @NonNull
    @Override
    public quesnoadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.examquesshowlayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull quesnoadapter.viewholder holder, int position) {

        holder.no.setText(String.valueOf(list.get(position).getQusno()));
        holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos=holder.getAdapterPosition();
                vp.setCurrentItem(list.get(holder.getAdapterPosition()).getQusno()-1);
                notifyDataSetChanged();
            }
        });
        if(pos==position){
            holder.no.setTextColor(context.getResources().getColor(R.color.tolcol));
        }
        else
        {
            holder.no.setTextColor(context.getResources().getColor(R.color.black));
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView no;
        ConstraintLayout lay;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            no=itemView.findViewById(R.id.no);
            lay=itemView.findViewById(R.id.layo);
        }
    }
}
