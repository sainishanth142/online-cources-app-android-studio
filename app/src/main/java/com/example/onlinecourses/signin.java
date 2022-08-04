package com.example.onlinecourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signin extends AppCompatActivity {
    EditText fsname,lsname,colname,regno,mobno,emailid,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        fsname=findViewById(R.id.firstname);lsname=findViewById(R.id.lastname);colname=findViewById(R.id.collegename);regno=findViewById(R.id.registernumber);mobno=findViewById(R.id.mobilenumber);emailid=findViewById(R.id.email);pass=findViewById(R.id.password);
    }

    public void register(View view) {
        register(fsname.getText().toString(),lsname.getText().toString(),colname.getText().toString(),regno.getText().toString(),mobno.getText().toString(),emailid.getText().toString(),pass.getText().toString());
    }
    public void register(String fsname,String lsname,String colname,String regno,String mobno,String email,String pass){
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String id=auth.getUid();
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference ref=database.getReference();
                    HashMap<String,String> data=new HashMap<>();
                    data.put("first name",fsname);
                    data.put("last name",lsname);
                    data.put("college name",colname);
                    data.put("register number",regno);
                    data.put("mobile number",mobno);
                    data.put("email",email);
                    data.put("userid",id);
                    ref.child("users").child(id).setValue(data);
                    Toast.makeText(signin.this, "registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(signin.this,login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(signin.this,login.class);
        startActivity(intent);
        finish();
    }
}