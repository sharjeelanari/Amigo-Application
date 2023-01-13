package com.example.collegeappfirstassemble.TechClubs.TechAdapterModel;

public class TechclubModel {


    String name,lead, uid;

    public TechclubModel() {

    }

    public TechclubModel(String name, String lead, String uid) {
        this.name = name;
        this.lead = lead;
        this.uid = uid;
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
