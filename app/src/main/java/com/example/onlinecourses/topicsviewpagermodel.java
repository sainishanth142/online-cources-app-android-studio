package com.example.onlinecourses;

public class topicsviewpagermodel {
    String text,imagelink,weblink;

    public topicsviewpagermodel(String text, String imagelink, String weblink) {
        this.text = text;
        this.imagelink = imagelink;
        this.weblink = weblink;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }
}
