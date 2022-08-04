package com.example.onlinecourses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class questionnopushadapter extends RecyclerView.Adapter<questionnopushadapter.viewholder> {
    Context context;
    ArrayList<exammodelpush> list;
    ViewPager vp;
    int pos;
    public questionnopushadapter(Context context, ArrayList<exammodelpush> list, ViewPager vp) {
        this.context = context;
        this.list = list;
        this.vp=vp;
    }
    @NonNull
    @Override
    public questionnopushadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.examquestionshowlayoutpost,parent,false);
        return new questionnopushadapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull questionnopushadapter.viewholder holder, int position) {
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
