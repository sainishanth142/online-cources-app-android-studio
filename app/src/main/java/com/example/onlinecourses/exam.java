package com.example.onlinecourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class exam extends AppCompatActivity {
    ViewPager vp;
    examviewpageradapter vpadapter;
    ArrayList<exammodel> list;
    RecyclerView rv;
    quesnoadapter adapter;
    int seconds=60,minutes=9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        list=new ArrayList<>();
        vp=findViewById(R.id.viewpager);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView tv = (TextView) findViewById(R.id.timer);
                        tv.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                        seconds -= 1;

                        if(seconds == 0)
                        {
                            tv.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));

                            seconds=60;
                            minutes=minutes-1;
                        }
                    }
                });
            }
        }, 0, 1000);

        rv=findViewById(R.id.recyclerView1);
        adapter=new quesnoadapter(this,list,vp);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        vpadapter=new examviewpageradapter(this,list,vp,adapter,exam.this);
        vp.setAdapter(vpadapter);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("exams").child(getIntent().getStringExtra("examno")).child("questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String question=snapshot.child("question").getValue(String.class);
                    String opt1=snapshot.child("option1").getValue(String.class);
                    String opt2=snapshot.child("option2").getValue(String.class);
                    String opt3=snapshot.child("option3").getValue(String.class);
                    String opt4=snapshot.child("option4").getValue(String.class);
                    int qn=Integer.parseInt(snapshot.getKey());
                    exammodel i1=new exammodel(String.valueOf(qn)+".) "+question,opt1,opt2,opt3,opt4,qn);
                    list.add(i1);
                }
                vpadapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                adapter.pos=position;
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}