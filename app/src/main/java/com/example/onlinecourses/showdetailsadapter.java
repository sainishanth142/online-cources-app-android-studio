package com.example.onlinecourses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class showdetailsadapter extends RecyclerView.Adapter<showdetailsadapter.viewholder> {
    ArrayList<showdetailsmodal> list;
    Context context;
    public showdetailsadapter(ArrayList<showdetailsmodal> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public showdetailsadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.showdetailsofcoursesre,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull showdetailsadapter.viewholder holder, int position) {
        holder.text.setText(list.get(position).getCoursedetails());
        Picasso.get().load(list.get(position).getImglink()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.courseimage);
            text=itemView.findViewById(R.id.coursedetails);
        }
    }
}
