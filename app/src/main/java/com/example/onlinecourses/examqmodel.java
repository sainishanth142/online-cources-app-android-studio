package com.example.onlinecourses;

public class examqmodel {
    String qname;
    String id;

    public examqmodel(String qname, String id) {
        this.qname = qname;
        this.id = id;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
