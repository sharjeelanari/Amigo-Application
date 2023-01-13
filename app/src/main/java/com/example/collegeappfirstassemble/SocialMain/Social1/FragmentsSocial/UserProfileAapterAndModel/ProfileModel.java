package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel;

public class ProfileModel {
    String name, Uid, USN, college;

    public ProfileModel(){

    }

    public ProfileModel(String name, String Uid, String USN, String college){
        this.name = name;
        this.Uid = Uid;
        this.USN = USN;
        this.college = college;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getUSN() {
        return USN;
    }

    public void setUSN(String USN) {
        this.USN = USN;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
