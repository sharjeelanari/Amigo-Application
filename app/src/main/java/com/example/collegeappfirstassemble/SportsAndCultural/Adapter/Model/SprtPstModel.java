package com.example.collegeappfirstassemble.SportsAndCultural.Adapter.Model;

public class SprtPstModel {

    String Pasteventname;
    String Pastdescription;
    String Pasrextra;


    public SprtPstModel(String Pasteventname, String Pastdescription, String Pastextra) {
        this.Pasteventname = Pasteventname;
        this.Pastdescription = Pastdescription;
        this.Pasrextra = Pastextra;

    }

    public String getPasteventname() {
        return Pasteventname;
    }

    public void setPasteventname(String pasteventname) {
        Pasteventname = pasteventname;
    }

    public String getPastdescription() {
        return Pastdescription;
    }

    public void setPastdescription(String pastdescription) {
        Pastdescription = pastdescription;
    }

    public String getPasrextra() {
        return Pasrextra;
    }

    public void setPasrextra(String pasrextra) {
        Pasrextra = pasrextra;
    }
}
