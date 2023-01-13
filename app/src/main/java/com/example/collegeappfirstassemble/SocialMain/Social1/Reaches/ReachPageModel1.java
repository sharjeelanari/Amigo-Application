package com.example.collegeappfirstassemble.SocialMain.Social1.Reaches;

public class ReachPageModel1 {
    String name, message;


    public ReachPageModel1(){

    }

    public ReachPageModel1(String name, String message){

        this.name = name;
        this.message = message;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
