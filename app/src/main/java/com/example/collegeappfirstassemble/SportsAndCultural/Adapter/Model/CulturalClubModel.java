package com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model;

public class CulturalClubModel {
    public CulturalClubModel() {

    }

    public CulturalClubModel(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String uid;
    String name;
}
