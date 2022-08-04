package com.example.onlinecourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText emailet,passwordet;
    RecyclerView rec;
    ArrayList<showdetailsmodal> list;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            Intent intent=new Intent(MainActivity.this,home.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("about");
        rec=findViewById(R.id.recyclerview);
        list=new ArrayList<>();
        showdetailsadapter adapter=new showdetailsadapter(list,this);
        rec.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    showdetailsmodal item1=new showdetailsmodal(snapshot.child("image").getValue(String.class),snapshot.child("data").getValue(String.class));
                    list.add(item1);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loginbt(View view) {
        Intent intent=new Intent(MainActivity.this,login.class);
        startActivity(intent);
    }

    public void signinbt(View view) {
        Intent intent=new Intent(MainActivity.this,signin.class);
        startActivity(intent);
    }
}