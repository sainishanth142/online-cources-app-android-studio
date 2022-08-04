package com.example.onlinecourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class home extends AppCompatActivity{
    ViewPager vp;
    String[] images;
    imageviewpageradapter vpadapter;
    DrawerLayout d;
    TextView usnametv,usnametvnb,emtvnv,mobno,medtvnv,colnam,regno;
    String userid,username,email,rank="23111",g="22",s="234",b="22332";
    NavigationView nav;
    WebView cweb;
    int i=0;
    RecyclerView re;
    ArrayList<homeitemmodel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getlatestnews();
        list=new ArrayList<>();
        nav=findViewById(R.id.navd);
        re=findViewById(R.id.recyclerview);
        re.setNestedScrollingEnabled(false);
        re.setLayoutManager(new GridLayoutManager(this, 2));
        homeitemadapter adapter=new homeitemadapter(this,list,"r");
        re.setAdapter(adapter);
        usnametv=findViewById(R.id.usname);
        View h = nav.getHeaderView(0);
        nav.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent=new Intent(home.this,selectexam.class);
                startActivity(intent);
                return true;
            }
        });
        nav.getMenu().getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent=new Intent(home.this,makequiz.class);
                startActivity(intent);
                return true;
            }
        });
        nav.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent=new Intent(home.this,profile.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
                return true;
            }
        });
        usnametvnb=h.findViewById(R.id.username);
        emtvnv=h.findViewById(R.id.email);
        mobno = h.findViewById(R.id.mobilenumber);
        colnam=h.findViewById(R.id.collegename);
        regno=h.findViewById(R.id.registerno);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        userid = auth.getCurrentUser().getUid();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("users").child(userid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username=dataSnapshot.child("first name").getValue(String.class)+" "+dataSnapshot.child("last name").getValue(String.class);
                email=dataSnapshot.child("email").getValue(String.class);
                String mobileno=dataSnapshot.child("mobile number").getValue(String.class);String regn=dataSnapshot.child("register number").getValue(String.class);String coln=dataSnapshot.child("college name").getValue(String.class);
                usnametv.setText(username);
                usnametvnb.setText(username);
                emtvnv.setText(email);
                mobno.setText(mobileno);
                regno.setText(regn);
                colnam.setText(coln);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database1=FirebaseDatabase.getInstance();
        DatabaseReference ref1=database.getReference("data");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String image=snapshot.child("image").getValue(String.class);
                    homeitemmodel i1=new homeitemmodel("ho", image,snapshot.getKey(),"ddd");
                    list.add(i1);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        vp=findViewById(R.id.viewpager);

        d=findViewById(R.id.dra);
    }

    private void getlatestnews() {

        FirebaseDatabase database1=FirebaseDatabase.getInstance();
        DatabaseReference ref1=database1.getReference("latest");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i=0;
                ArrayList<String> l=new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String image=snapshot.getValue(String.class);
                    l.add(image);
                    i=i+1;
                }
                vpadapter=new imageviewpageradapter(home.this,l);
                vp.setAdapter(vpadapter);
                vpadapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void opennav(View view) {
        d.openDrawer(Gravity.LEFT);
    }
    public void openmenu(View view) {
        ImageButton bt=findViewById(R.id.menubt);
        PopupMenu menu=new PopupMenu(home.this,bt);
        menu.getMenuInflater().inflate(R.menu.menu,menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.logout){
                    Intent intent=new Intent(home.this,login.class);
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    auth.signOut();
                    startActivity(intent);
                    finish();
                }
                if(menuItem.getItemId()==R.id.profile){
                    Intent intent=new Intent(home.this,profile.class);
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                }

                return false;
            }
        });
        menu.show();
    }
    public void messages(View view) {
        Intent intent=new Intent(home.this,chat.class);
        startActivity(intent);
    }
}