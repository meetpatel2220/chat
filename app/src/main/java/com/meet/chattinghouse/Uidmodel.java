package com.meet.chattinghouse;

public class Uidmodel {

    private String date;
    private String uid;



    public Uidmodel(){

    }

    public Uidmodel(String date, String uid) {
        this.date = date;
        this.uid = uid;

    }

       public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getUid() {
        return uid;
    }

    public void setUid(String myuid) {
        this.uid = myuid;
    }


}