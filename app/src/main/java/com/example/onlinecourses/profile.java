package com.example.onlinecourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    EditText usnametv,lusnametvnb,fusnametvnb,emtvnv,mobno,medtvnv,colnam,regno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fusnametvnb=findViewById(R.id.fusername);
        lusnametvnb=findViewById(R.id.lusername);
        emtvnv=findViewById(R.id.email);
        mobno = findViewById(R.id.mobileno);
        colnam=findViewById(R.id.collegename);
        regno=findViewById(R.id.rollno);
        setdetails();
    }

    public void back(View view) {
        Intent intent=new Intent(profile.this,home.class);
        startActivity(intent);
        finish();
    }
    public void setdetails(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("users").child(getIntent().getStringExtra("userid"));

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fusername=dataSnapshot.child("first name").getValue(String.class);
                String lusername=dataSnapshot.child("last name").getValue(String.class);
                String email=dataSnapshot.child("email").getValue(String.class);
                String mobileno=dataSnapshot.child("mobile number").getValue(String.class);String regn=dataSnapshot.child("register number").getValue(String.class);String coln=dataSnapshot.child("college name").getValue(String.class);
                fusnametvnb.setText(fusername);
                lusnametvnb.setText(lusername);
                emtvnv.setText(email);
                mobno.setText(mobileno);
                regno.setText(regn);
                colnam.setText(coln);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void savechanges(View view) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("users").child(getIntent().getStringExtra("userid"));
        ref.child("first name").setValue(fusnametvnb.getText().toString());
        ref.child("last name").setValue(lusnametvnb.getText().toString());
        ref.child("email").setValue(emtvnv.getText().toString());
        ref.child("mobile number").setValue(mobno.getText().toString());
        ref.child("register number").setValue(regno.getText().toString());
        ref.child("college name").setValue(colnam.getText().toString());
        Toast.makeText(this, "saved successfully", Toast.LENGTH_SHORT).show();
    }
}