package com.example.onlinecourses;

import android.graphics.Bitmap;
import android.net.Uri;

public class chatingmodel {
    String message,time;
    int type;
    Uri uri;
    Boolean selected=false;

    public chatingmodel(String message, String time, int type) {
        this.message = message;
        this.time = time;
        this.type = type;
    }

    public chatingmodel(String time, int type, Uri uri, Boolean selected) {
        this.time = time;
        this.type = type;
        this.uri = uri;
        this.selected = selected;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
