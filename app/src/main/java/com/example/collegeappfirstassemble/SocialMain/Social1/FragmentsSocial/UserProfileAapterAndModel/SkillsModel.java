package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel;

public class SkillsModel {

    String skillname, skilldet;

    public SkillsModel(){

    }

    public SkillsModel(String skillname, String skilldet){
        this.skillname = skillname;
        this.skillname = skilldet;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public String getSkilldet() {
        return skilldet;
    }

    public void setSkilldet(String skilldet) {
        this.skilldet = skilldet;
    }
}
