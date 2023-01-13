package com.example.collegeappfirstassemble.SocialMain.Social1.Reaches;

public class ReachPageModel {
    String name, message;

    public ReachPageModel(){

    }

    public ReachPageModel(String name, String message){
        this.message = message;
        this.name = name;
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
