package com.example.onlinecourses;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
public class chattingadapter extends RecyclerView.Adapter<chattingadapter.viewholder> {
    Context context;
    ArrayList<chatingmodel> list;

    public chattingadapter(Context context, ArrayList<chatingmodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lefttextview= LayoutInflater.from(context).inflate(R.layout.chattingleftitem,parent,false);
        View righttextview= LayoutInflater.from(context).inflate(R.layout.chattingrightitem,parent,false);
        View leftimageview= LayoutInflater.from(context).inflate(R.layout.leftchattingimage,parent,false);
        View rightimageview= LayoutInflater.from(context).inflate(R.layout.rightchattingimage,parent,false);
        if(viewType==0){
            return new viewholder(lefttextview,0);
        }else if(viewType==1){
            return new viewholder(righttextview,1);
        }else if(viewType==2){
            return new viewholder(leftimageview,2);
        }else{
            return new viewholder(rightimageview,3);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        chatingmodel message=list.get(position);
        if(holder.position1==0||holder.position1==1){
            holder.chatingtextitem.setText(message.getMessage());
            holder.time.setText(message.getTime());
            holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(message.getSelected()){
                            holder.layout.setBackgroundColor(Color.TRANSPARENT);
                            message.setSelected(false);
                        }
                        else {
                            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.teal_700));
                            message.setSelected(true);
                        }
                    }
                });
        }
        if(holder.position1==2){
            holder.chatimage.setImageURI(message.getUri());
            //Picasso.get().load(message.getUri()).into(holder.chatimage);
            holder.imagetime.setText(message.getTime());
        }else if(holder.position1==3){
            holder.chatimage.setImageURI(message.getUri());
            //Picasso.get().load(message.getUri()).into(holder.chatimage);
            holder.imagetime.setText(message.getTime());
        }
    }


    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView chatingtextitem,time,imagetime;
        ConstraintLayout layout;
        ImageView chatimage;
        int position1;

        public viewholder(@NonNull View itemView,int position) {
            super(itemView);
            position1=position;
            if(position==0 || position==1) {
                chatingtextitem = itemView.findViewById(R.id.chattingtextitem);
                time = itemView.findViewById(R.id.time);
                layout = itemView.findViewById(R.id.chattextlayout);
            }
            if(position==2||position==3){
                chatimage=itemView.findViewById(R.id.chatimage);
                imagetime=itemView.findViewById(R.id.chatimagetime);
            }
        }
    }
}
