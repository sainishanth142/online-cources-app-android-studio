package com.example.onlinecourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class viewtopics extends AppCompatActivity {
    TextView subtopicnameTV;
    String topicname,subtopicname;
    ViewPager vp;
    topicsviewpageradapter vpadapter;
    ArrayList<topicsviewpagermodel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtopics);
        list=new ArrayList<>();
        topicname=getIntent().getStringExtra("topic");
        String subtopickey=getIntent().getStringExtra("topicid");
        subtopicname=getIntent().getStringExtra("subtopic");
        subtopicnameTV=findViewById(R.id.topicname);
        subtopicnameTV.setText(subtopicname);
        vp=findViewById(R.id.viewpager);
        list=setvp(subtopickey);
        vpadapter=new topicsviewpageradapter(viewtopics.this,list);
        vp.setAdapter(vpadapter);


    }

    private ArrayList<topicsviewpagermodel> setvp(String k) {
        ArrayList<topicsviewpagermodel> l = new ArrayList<>();
        FirebaseDatabase database1=FirebaseDatabase.getInstance();
        DatabaseReference ref1=database1.getReference("data").child(topicname).child(k).child("subtopic");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String image=snapshot.child("imagelink").getValue(String.class);
                    String text=snapshot.child("name").getValue(String.class);
                    String weblink=snapshot.child("videolink").getValue(String.class);
                    l.add(new topicsviewpagermodel(text,image,weblink));
                }
                vpadapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return l;
    }

    public void back(View view) {
        Intent intent=new Intent(viewtopics.this,topics.class);
        intent.putExtra("topic",topicname);
        startActivity(intent);
        finish();
    }
}