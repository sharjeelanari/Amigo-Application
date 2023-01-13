package com.example.collegeappfirstassemble.SocialMain.Social1.FragmentsSocial.UserProfileAapterAndModel;

public class DetailsProfileProjectModel {
    String projectnm, projectdet, link;

    public DetailsProfileProjectModel(){

    }

    public String getProjectnm() {
        return projectnm;
    }

    public void setProjectnm(String projectnm, String projectdet, String link) {
        this.projectnm = projectnm;
        this.projectdet = projectdet;
        this.link = link;
    }

    public String getProjectdet() {
        return projectdet;
    }

    public void setProjectdet(String projectdet) {
        this.projectdet = projectdet;
    }

    public void setProjectnm(String projectnm) {
        this.projectnm = projectnm;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
