package com.example.onlinecourses;

public class showdetailsmodal {
    String imglink,coursedetails;

    public showdetailsmodal(String imglink, String coursedetails) {
        this.imglink = imglink;
        this.coursedetails = coursedetails;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getCoursedetails() {
        return coursedetails;
    }

    public void setCoursedetails(String coursedetails) {
        this.coursedetails = coursedetails;
    }
}
