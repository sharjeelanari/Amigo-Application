package com.example.collegeappfirstassemble.SocialMain.AddPost.ModelClasses;

public class SocialAddpostModel {
    private String imageurl;
    private String time;
    private String PostDescription, userid;
    private String heading, fileuri;
    private int approves;

    public SocialAddpostModel() {

    }

    public SocialAddpostModel(String imageurl, String postDescription, String time, String heading,String userid, String
                              fileuri) {
        this.imageurl = imageurl;
        this.PostDescription = postDescription;
        this.time = time;
        this.heading = heading;
        this.userid = userid;
        this.fileuri = fileuri;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getApproves() {
        return approves;
    }

    public void setApproves(int approves) {
        this.approves = approves;
    }

    public String getPostDescription() {
        return PostDescription;
    }

    public void setPostDescription(String postDescription) {
        PostDescription = postDescription;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    public String getFileuri() {
        return fileuri;
    }

    public void setFileuri(String fileuri) {
        this.fileuri = fileuri;
    }
}
