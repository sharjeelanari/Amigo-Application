package com.example.collegeappfirstassemble.SocialMain.Social1.Reaches;

public class ReachModel {

    String sendertext,currentUid, currentname,time;

    public ReachModel(){

    }

    public ReachModel(String sendertext, String currentUid, String currentname, String time){
        this.sendertext = sendertext;
        this.currentUid = currentUid;
        this.currentname = currentname;
        this.time =time;
    }

    public String getSendertext() {
        return sendertext;
    }

    public void setSendertext(String sendertext) {
        this.sendertext = sendertext;
    }

    public String getCurrentUid() {
        return currentUid;
    }

    public void setCurrentUid(String currentUid) {
        this.currentUid = currentUid;
    }

    public String getCurrentname() {
        return currentname;
    }

    public void setCurrentname(String currentname) {
        this.currentname = currentname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
