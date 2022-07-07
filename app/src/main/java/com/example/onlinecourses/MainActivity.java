package com.example.onlinecourses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

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
        rec=findViewById(R.id.recyclerview);
        list=new ArrayList<>();
        showdetailsadapter adapter=new showdetailsadapter(list,this);
        rec.setAdapter(adapter);
        showdetailsmodal item1=new showdetailsmodal("https://cdn.educba.com/academy/wp-content/uploads/2019/10/Python-Features.png","Python is an interpreted, object-oriented, high-level programming language with dynamic semantics. ... Python's simple, easy to learn syntax emphasizes readability and therefore reduces the cost of program maintenance. Python supports modules and packages, which encourages program modularity and code reuse.");
        list.add(item1);
        list.add(item1);list.add(item1);list.add(item1);list.add(item1);
        adapter.notifyDataSetChanged();
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