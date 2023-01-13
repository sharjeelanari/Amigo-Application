package com.example.collegeappfirstassemble.SocialMain.Social1.ProfilePage;

public class ClubMemModel {
    String memName, memdesignation;

    public ClubMemModel(){
    }

    public ClubMemModel(String memName, String memdesignation){
        this.memName = memName;
        this.memdesignation = memdesignation;
    }


    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemdesignation() {
        return memdesignation;
    }

    public void setMemdesignation(String memdesignation) {
        this.memdesignation = memdesignation;
    }
}
