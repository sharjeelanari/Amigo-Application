package com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses;

public class ProjectaddpostModel {

    String basedon, skills, moreidea, otherdet, showuser, uid;
    public ProjectaddpostModel() {

    }

    public ProjectaddpostModel(String basedon, String skills, String moreidea, String otherdet, String showuser
    ,String uid) {
        this.basedon = basedon;
        this.skills = skills;
        this.moreidea = moreidea;
        this.otherdet = otherdet;
        this.showuser = showuser;
        this.uid = uid;
    }

    public String getBasedon() {
        return basedon;
    }

    public void setBasedon(String basedon) {
        this.basedon = basedon;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getMoreidea() {
        return moreidea;
    }

    public void setMoreidea(String moreidea) {
        this.moreidea = moreidea;
    }

    public String getOtherdet() {
        return otherdet;
    }

    public void setOtherdet(String otherdet) {
        this.otherdet = otherdet;
    }

    public String getShowuser() {
        return showuser;
    }

    public void setShowuser(String showuser) {
        this.showuser = showuser;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
