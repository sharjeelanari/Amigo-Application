package com.example.collegeappfirstassemble.SocialMain.Social1.SocialInstructorModelAndAdapter;

public class InstructorModel {
    String ins_name;
    String dp;
    String ins_domain;
    String ins_bio;
    String ins_button_reach;

    public InstructorModel() {
    }

    public InstructorModel(String ins_name, String ins_domain, String ins_bio, String ins_button_reach, String dp) {
        this.ins_name = ins_name;
        this.ins_domain = ins_domain;
        this.ins_bio = ins_bio;
        this.ins_button_reach = ins_button_reach;
        this.dp = dp;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getIns_name() {
        return ins_name;
    }

    public void setIns_name(String ins_name) {
        this.ins_name = ins_name;
    }

    public String getIns_domain() {
        return ins_domain;
    }

    public void setIns_domain(String ins_domain) {
        this.ins_domain = ins_domain;
    }

    public String getIns_bio() {
        return ins_bio;
    }

    public void setIns_bio(String ins_bio) {
        this.ins_bio = ins_bio;
    }

    public String getIns_button_reach() {
        return ins_button_reach;
    }

    public void setIns_button_reach(String ins_button_reach) {
        this.ins_button_reach = ins_button_reach;
    }



}
