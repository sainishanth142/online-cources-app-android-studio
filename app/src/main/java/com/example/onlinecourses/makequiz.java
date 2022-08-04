package com.example.onlinecourses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class makequiz extends AppCompatActivity {
    ViewPager vp;
    examviewpageradapterpush vpadapter;
    ArrayList<exammodelpush> list;
    RecyclerView rv;
    EditText title,time;
    int n=1;
    questionnopushadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makequiz);
        list=new ArrayList<>();
        title=findViewById(R.id.usname);
        vp=findViewById(R.id.viewpager);
        time=findViewById(R.id.time);
        rv=findViewById(R.id.recyclerView1);
        adapter=new questionnopushadapter(this,list,vp);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        vpadapter=new examviewpageradapterpush(this,list,vp,adapter);
        vp.setAdapter(vpadapter);
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
        exammodelpush i1=new exammodelpush("","","","","",1);
        list.add(i1);
        vpadapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }
    public void addquestion(View view) {
        n=n+1;
        exammodelpush i1=new exammodelpush("","","","","",n);
        list.add(i1);
        vpadapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
        vp.setCurrentItem(n-1);
    }

    public void sendquiz(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("exams").push();
        reference.child("date").setValue("today-demo");
        reference.child("name").setValue(title.getText().toString());
        reference.child("noofbits").setValue(list.size());
        reference.child("time").setValue(Integer.valueOf(time.getText().toString()));
        reference.child("totalmarks").setValue(list.size());
        for(int i=0;i<list.size();i++){
            reference.child("questions").child(String.valueOf(i+1)).child("answer").setValue("2");
            reference.child("questions").child(String.valueOf(i+1)).child("option1").setValue(list.get(i).getOpt1());
            reference.child("questions").child(String.valueOf(i+1)).child("option2").setValue(list.get(i).getOpt2());
            reference.child("questions").child(String.valueOf(i+1)).child("option3").setValue(list.get(i).getOpt3());
            reference.child("questions").child(String.valueOf(i+1)).child("option4").setValue(list.get(i).getOpt4());
            reference.child("questions").child(String.valueOf(i+1)).child("question").setValue(list.get(i).getQuestion());
        }
    }
    public void post(View view){
        sendquiz();
        Toast.makeText(this, "posted successfully", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,home.class);
        startActivity(intent);
        finish();
    }
}