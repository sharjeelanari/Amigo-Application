package com.example.collegeappfirstassemble.SocialMain.Social1.SocialHomeModelAndAdapter;

public class SocialActivityModel {

    int profile , addimg ,comment , share , save;
    String name;
    String description;
    String check;

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getAddimg() {
        return addimg;
    }

    public void setAddimg(int addimg) {
        this.addimg = addimg;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }



    public SocialActivityModel(int profile, int addimg, int comment, int share, int save, String name, String description, String check) {
        this.profile = profile;
        this.addimg = addimg;
        this.comment = comment;
        this.share = share;
        this.save = save;
        this.name = name;
        this.description = description;
        this.check = check;
    }





}
