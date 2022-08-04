package com.example.onlinecourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chat extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<chatpeoplemodel> list;
    chatpeopleadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        list=new ArrayList<>();
        recyclerView=findViewById(R.id.chatpeoplerecyclerview);
        adapter=new chatpeopleadapter(list,this);
        recyclerView.setAdapter(adapter);
        addpeople();
        
    }
    public void addpeople(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("users");
        FirebaseAuth auth=FirebaseAuth.getInstance();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    if(!auth.getUid().equals(snapshot1.getKey())){
                        chatpeoplemodel item1=new chatpeoplemodel(snapshot1.getKey(),snapshot1.child("register number").getValue(String.class),"kaka");
                        list.add(item1);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}