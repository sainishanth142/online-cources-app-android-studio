package com.example.onlinecourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.os.BuildCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.inputmethodservice.ExtractEditText;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class chatting extends AppCompatActivity {
    String id,userid;
    RecyclerView recyclerView;
    chattingadapter adapter;
    ArrayList<chatingmodel> list;
    SharedPreferences sh;
    EditText messageET;
    OutputStream outputStream;
    FirebaseStorage storage;
    File mypath;
    StorageReference storageReference;
    TextView username,time;
    SharedPreferences.Editor ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userid=getIntent().getStringExtra("userid");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        id= FirebaseAuth.getInstance().getUid();
        list=new ArrayList<>();
        setContentView(R.layout.activity_chatting);
        setRecyclerView();
        messageET=findViewById(R.id.chatET);
        sh = getSharedPreferences(id+userid,MODE_MULTI_PROCESS);
        ed = sh.edit();
        username=findViewById(R.id.usernameet);
        username.setText(getIntent().getStringExtra("username"));
        time=findViewById(R.id.activestate);
        setactivestate();
        settyping();
        gettypingstate();
        reloadallmessages();
    }

    public void reloadallmessages(){
        String premessages;
        premessages = getpremessages();
        if(premessages!=null) {
            String[] premessage = premessages.split(",");
            for (String i : premessage) {
                String[] mess = i.split("-");
                String[] mesges=mess[0].split("@");
                if(mesges.length>1) if (mess.length > 1) addmessagetorecyclerview(mesges[0],mesges[1], Integer.parseInt(mess[1]));
                else addmessagetorecyclerview(mesges[0],"", Integer.parseInt(mess[1]));
            }
        }
        getmessages();
    }
    private String getpremessages() {
        return sh.getString("messagesstr",null);
    }
    public void gettypingstate(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("chat").child(userid).child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null && !snapshot.getValue(String.class).equals("on this page")) time.setText(snapshot.getValue(String.class));
                else setactivestate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getmessages(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("chat").child(id);
        ArrayList<String> val=new ArrayList<>();
        val.add("ss");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    String message=snapshot1.child(userid).getValue(String.class);
                    String type=snapshot1.child(userid+"type").getValue(String.class);
                    String time=snapshot1.child(userid+"time").getValue(String.class);
                    if(!val.contains(snapshot1.getKey())) {
                        if (message != null && type!=null && time!=null) {
                            if (type.equals("0")) {
                                addmessagetorecyclerview(message, time, Integer.parseInt(type));
                                savemessagetomobile(message + "@" + time, type);
                                val.add(snapshot1.getKey());
                                reference.child(snapshot1.getKey()).removeValue();
                            }
                            if (type.equals("2")) {
                                String path=downloadfile(gettimestamp(),"images",".png",message,time,Integer.parseInt(type));
                                val.add(snapshot1.getKey());
                                reference.child(snapshot1.getKey()).removeValue();
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public String gettime(){
        DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }
    public void setactivestate(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("users").child(userid).child("active");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null) time.setText(snapshot.getValue(String.class));
                else time.setText("inactive");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setRecyclerView() {
        recyclerView=findViewById(R.id.chattingrecyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new chattingadapter(this,list);
        recyclerView.setAdapter(adapter);
    }
    public void settyping(){
        messageET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                String text=messageET.getText().toString();
                DatabaseReference reference=database.getReference("chat").child(id).child(userid);
                if(!text.equals("")) reference.setValue("typing");
                else reference.setValue("on this page");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public void back(View view) {
        finish();
    }
    public void send(View view) {
        String message=messageET.getText().toString();
        sendmessagetodatabase(id,userid,message,"0");
        savemessagetomobile(message+"@"+gettime(),"1");
        addmessagetorecyclerview(message,gettime(),1);
        messageET.setText("");
    }
    private void savemessagetomobile(String message,String i) {
        String mes;
        mes=getpremessages();
        if(mes==null){
            String mes1;
            mes1=(message+"-"+i);
            ed.putString("messagesstr",mes1);
            ed.apply();
        }else{
            mes=mes+","+(message+"-"+i);
            ed.putString("messagesstr",mes);
            ed.apply();
        }
    }
    public void sendmessagetodatabase(String id, String userid,String message,String type) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("chat").child(userid).push();
        reference.child(id).setValue(message);
        reference.child(id+"type").setValue(type);
        reference.child(id+"time").setValue(gettime());
    }
    public void addmessagetorecyclerview(String message,String time,int type){
        if(type==2||type==3){
            File file=new File(message);
            Uri uri=Uri.fromFile(file);
            chatingmodel item = new chatingmodel(time,type,uri,false);
            list.add(item);
            recyclerView.scrollToPosition(list.size() - 1);
            adapter.notifyDataSetChanged();
        }else {
            chatingmodel item = new chatingmodel(message, time, type);
            list.add(item);
            recyclerView.scrollToPosition(list.size() - 1);
            adapter.notifyDataSetChanged();
        }
    }
    public ArrayList<String> decodestrlist(String list){
        list=list.replace("[","");
        list=list.replace("]","");
        ArrayList<String> mes=new ArrayList<>();
        mes.addAll(Arrays.asList(list.split(",")));
        return mes;
    }
    public void clear(View view) {
        ed.putString("messagesstr",null);
        ed.apply();
        list.clear();
        addmessagetorecyclerview("clear",gettime(),1);
    }
    public void setre(View view) {

    }
    public void selecttypeoffile(View view) {
        PopupMenu pm = new PopupMenu(chatting.this, view);
        pm.getMenuInflater().inflate(R.menu.fileselectionmenu, pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.image) {
                    chooseimagefromcamera();
                }else if(item.getItemId() == R.id.video){
                    chooseimagefromgallery();
                }else if(item.getItemId() == R.id.document){

                }
                return true;
            }
        });
        pm.show();
    }
    public String gettimestamp(){
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return timeStamp;
    }
    public File getPictureFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "ZOFTINO_" + timeStamp;
        File storageDir = getExternalFilesDir("/images");
        File image = null;
        try {
            image = File.createTempFile(pictureFile,  ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mypath= image;
        return image;
    }
    public void chooseimagefromcamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File pictureFile = null;
            pictureFile = getPictureFile();
            if (pictureFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.zoftino.android.fileprovider",
                        pictureFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, 1);
            }
        }
    }
    public void chooseimagefromgallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Uri uri=data.getData();
            uploadfile(uri);
            String link = null;

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                String path1=saveToInternalStorage("images",".png",bitmap);
                savemessagetomobile(path1+"@"+gettime(),"3");
                addmessagetorecyclerview(path1,gettime(),3);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==1 && resultCode == Activity.RESULT_OK) {
            Uri userimage=Uri.fromFile(mypath);
            uploadfile(userimage);
            savemessagetomobile(mypath.getAbsolutePath()+"@"+gettime(),"3");
            addmessagetorecyclerview(mypath.getAbsolutePath(),gettime(),3);
        }
    }
    public Uri getImageUri(Bitmap src, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        src.compress(format, quality, os);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), src, "title", null);
        return Uri.parse(path);
    }
    public String downloadfile(String name,String folder,String type,String link,String time,int type1){
        DownloadManager downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(link);
        DownloadManager.Request request=new DownloadManager.Request(uri);
        request.setTitle("image");
        request.setDescription("downloding...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            request.setDestinationInExternalFilesDir(this,"/"+folder+ File.separator,name+type);
        }
        downloadManager.enqueue(request);
        BroadcastReceiver onComplete=new BroadcastReceiver() {
            public void onReceive(Context ctxt, Intent intent) {
                File h=getExternalFilesDir("/"+folder);
                String pat=h.getAbsolutePath()+"/"+name+type;
                addmessagetorecyclerview(pat, time, type1);
                savemessagetomobile(pat + "@" + time, String.valueOf(type1));
                adapter.notifyDataSetChanged();

            }
        };
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        File h=getExternalFilesDir("/"+folder);
        return h.getAbsolutePath()+"/"+name+type;
    }
    private String saveToInternalStorage(String folder,String type,Bitmap bitmapImage){
        File h=getExternalFilesDir("/");
        File dir=new File(h.getAbsolutePath()+"/"+folder);
        boolean mkdir = dir.mkdir();
        File mypath=new File(dir,getname()+type);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return mypath.getAbsolutePath();
    }
    public String getname(){
        String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        return timeStamp;
    }
    private void uploadfile(Uri file) {
        // Code for showing progressDialog while uploading
        ProgressDialog progressDialog
                = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        // Defining the child of storageReference
        StorageReference storageRef = storage.getReferenceFromUrl("gs://skilldevelopmentapp.appspot.com");
        StorageReference ref
                = storageReference
                .child(
                        "images/"
                                + getname());

        // adding listeners on upload
        // or failure of image
        ref.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(
                            UploadTask.TaskSnapshot taskSnapshot)
                    {
                       ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String path =task.getResult().toString();
                                sendmessagetodatabase(id,userid,path,"2");
                            }
                        });
                        // Image uploaded successfully
                        // Dismiss dialog
                        progressDialog.dismiss();
                        Toast
                                .makeText(chatting.this,
                                        "Image Uploaded!!",
                                        Toast.LENGTH_SHORT)
                                .show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {

                        // Error, Image not uploaded
                        progressDialog.dismiss();
                        Toast
                                .makeText(chatting.this,
                                        "Failed " + e.getMessage(),
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                    // Progress Listener for loading
                    // percentage on the dialog box
                    @Override
                    public void onProgress(
                            UploadTask.TaskSnapshot taskSnapshot)
                    {
                        double progress
                                = (100.0
                                * taskSnapshot.getBytesTransferred()
                                / taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage(
                                "Uploaded "
                                        + (int)progress + "%");
                    }
                });
        
        
        

    }
}