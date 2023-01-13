package com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model;

public class SportsModel {
    String sportName;
    String capName,name,type;

    public SportsModel(String sportName, String capName){
        this.sportName=sportName;
        this.capName=capName;
    }


    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getCapName() {
        return capName;
    }

    public void setCapName(String capName) {
        this.capName = capName;
    }
}
