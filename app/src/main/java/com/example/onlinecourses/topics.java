package com.example.onlinecourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class topics extends AppCompatActivity {
    TextView topicnameTV;
    String topicname;
    RecyclerView re;
    ArrayList<homeitemmodel> list;
    homeitemadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        list=new ArrayList<>();
        topicname=getIntent().getStringExtra("topic");
        topicnameTV=findViewById(R.id.topicname);
        topicnameTV.setText(topicname);
        re=findViewById(R.id.recyclerview);
        re.setNestedScrollingEnabled(false);
        re.setLayoutManager(new GridLayoutManager(this, 2));
        adapter=new homeitemadapter(this,list,topicname);
        re.setAdapter(adapter);
        setdata();
    }

    private void setdata() {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("data").child(topicname);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("topicname").getValue(String.class) != null) {
                        String subtopicname = snapshot.child("topicname").getValue(String.class);
                        homeitemmodel i1 = new homeitemmodel("tp", "jhef", subtopicname,snapshot.getKey());
                        list.add(i1);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(topics.this,home.class);
        startActivity(intent);

    }
}