package com.meet.chattinghouse;

public class Users {

    private String name;
    private String status;
    private String myuid;



    public Users(){

    }

    public Users(String name, String status, String myuid) {
        this.name = name;
         this.status = status;
        this.myuid = myuid;

    }

       public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getMyuid() {
        return myuid;
    }

    public void setMyuid(String myuid) {
        this.myuid = myuid;
    }


}