package com.example.onlinecourses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;

public class examviewpageradapterpush extends PagerAdapter {
    Context context;
    ArrayList<exammodelpush> list;
    LayoutInflater lf;
    ViewPager vp;
    questionnopushadapter adapter;


    public examviewpageradapterpush(Context context, ArrayList<exammodelpush> list, ViewPager vp, questionnopushadapter adapter) {
        this.context = context;
        this.list = list;
        this.vp=vp;

        this.adapter=adapter;
        lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(ConstraintLayout) object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = lf.inflate(R.layout.exambitslayoutpost, container, false);
        EditText ques=itemView.findViewById(R.id.question);
        EditText option1=itemView.findViewById(R.id.opt1);
        EditText option2=itemView.findViewById(R.id.opt2);
        EditText option3=itemView.findViewById(R.id.opt3);
        EditText option4=itemView.findViewById(R.id.opt4);
        itemView.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(list.get(position).getQusno()-2);
                adapter.pos=list.get(position).getQusno()-2;
                adapter.notifyDataSetChanged();
            }
        });
        itemView.findViewById(R.id.sn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(list.get(position).getQusno());
                list.get(position).question=ques.getText().toString();
                list.get(position).opt1=option1.getText().toString();
                list.get(position).opt2=option2.getText().toString();
                list.get(position).opt3=option3.getText().toString();
                list.get(position).opt4=option4.getText().toString();
                adapter.pos=list.get(position).getQusno();
                adapter.notifyDataSetChanged();
            }
        });

        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
