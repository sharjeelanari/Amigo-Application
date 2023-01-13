package com.example.collegeappfirstassemble.AdminPanel;

public class ClubAddModel {
    String email,name,lead,uid;

    public ClubAddModel(){

    }
    public ClubAddModel(String email, String name, String lead, String uid){
        this.email = email;
        this.name = name;
        this.lead = lead;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
