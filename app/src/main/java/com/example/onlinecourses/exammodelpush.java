package com.example.onlinecourses;

public class exammodelpush {
    String question,opt1,opt2,opt3,opt4;
    int choosenopt;
    int currques;
    public int getCurrques() {
        return currques;
    }
    public void setCurrques(int currques) {
        this.currques = currques;
    }

    public int getChoosenopt() {
        return choosenopt;
    }

    public void setChoosenopt(int choosenopt) {
        this.choosenopt = choosenopt;
    }
    public exammodelpush(String question, String opt1, String opt2, String opt3, String opt4, int qusno) {
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.qusno = qusno;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public int getQusno() {
        return qusno;
    }

    public void setQusno(int qusno) {
        this.qusno = qusno;
    }

    public int getOpt() {
        return opt;
    }

    public void setOpt(int opt) {
        this.opt = opt;
    }

    public Boolean getSave() {
        return save;
    }

    public void setSave(Boolean save) {
        this.save = save;
    }

    int qusno;
    int opt;
    Boolean save;
}
