package com.example.collegeappfirstassemble.SocialMain.Social1.SocialProjectModelAndAdapter;

public class ProjectModel {

    int comment , share , save;
    String needforprotv;
    String usernameoranyusertv;
    String briefideaprotv;
    String protopictv;
    String check;

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

    public String getNeedforprotv() {
        return needforprotv;
    }

    public void setNeedforprotv(String needforprotv) {
        this.needforprotv = needforprotv;
    }

    public String getUsernameoranyusertv() {
        return usernameoranyusertv;
    }

    public void setUsernameoranyusertv(String usernameoranyusertv) {
        this.usernameoranyusertv = usernameoranyusertv;
    }

    public String getBriefideaprotv() {
        return briefideaprotv;
    }

    public void setBriefideaprotv(String briefideaprotv) {
        this.briefideaprotv = briefideaprotv;
    }

    public String getProtopictv() {
        return protopictv;
    }

    public void setProtopictv(String protopictv) {
        this.protopictv = protopictv;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }



    public ProjectModel(int comment, int share, int save, String needforprotv, String usernameoranyusertv, String briefideaprotv, String protopictv, String check) {
        this.comment = comment;
        this.share = share;
        this.save = save;
        this.needforprotv = needforprotv;
        this.usernameoranyusertv = usernameoranyusertv;
        this.briefideaprotv = briefideaprotv;
        this.protopictv = protopictv;
        this.check = check;
    }


}
