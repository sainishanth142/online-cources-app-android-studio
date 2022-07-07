package com.example.onlinecourses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class examviewpageradapter extends PagerAdapter {
    Context context;
    ArrayList<exammodel> list;
    LayoutInflater lf;
    ViewPager vp;
    quesnoadapter adapter;

    public examviewpageradapter(Context context, ArrayList<exammodel> list, ViewPager vp,quesnoadapter adapter) {
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
        View itemView = lf.inflate(R.layout.exambitslayout, container, false);
        TextView ques=itemView.findViewById(R.id.question);
        ques.setText(list.get(position).getQuestion());
        RadioButton option1=itemView.findViewById(R.id.opt1);
        RadioButton option2=itemView.findViewById(R.id.opt2);
        RadioButton option3=itemView.findViewById(R.id.opt3);
        RadioButton option4=itemView.findViewById(R.id.opt4);
        option1.setText(list.get(position).getOpt1());
        option2.setText(list.get(position).getOpt2());
        option3.setText(list.get(position).getOpt3());
        option4.setText(list.get(position).getOpt4());
        if(list.get(position).getChoosenopt()==1) option1.setChecked(true);
        else if(list.get(position).getChoosenopt()==2) option2.setChecked(true);
        else if(list.get(position).getChoosenopt()==3) option3.setChecked(true);
        else if(list.get(position).getChoosenopt()==4) option4.setChecked(true);
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
                RadioGroup rg = itemView.findViewById(R.id.radgro);
                int id=rg.getCheckedRadioButtonId();
                if (id == -1) {
                    list.get(position).choosenopt=0;
                }
                else{
                    if (id == R.id.opt1) {
                        list.get(position).choosenopt=1;
                    }else if(id==R.id.opt2){
                        list.get(position).choosenopt=2;
                    }else if(id==R.id.opt3){
                        list.get(position).choosenopt=3;
                    }else{
                        list.get(position).choosenopt=4;
                    }
                }
                adapter.pos=list.get(position).getQusno();
                adapter.notifyDataSetChanged();
            }
        });
        itemView.findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option1.setChecked(false);
                option2.setChecked(false);
                option3.setChecked(false);
                option4.setChecked(false);
                list.get(position).choosenopt=0;
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
