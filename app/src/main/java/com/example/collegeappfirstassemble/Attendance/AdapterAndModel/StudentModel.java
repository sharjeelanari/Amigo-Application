package com.example.collegeappfirstassemble.Attendance.AdapterAndModel;

public class StudentModel {

    //student details

    String addstudentname;
    String addstudentusn;
    String addBatch;

    public StudentModel(String addBatch, String addstudentname, String addstudentusn) {

        this.addstudentname = addstudentname;
        this.addstudentusn = addstudentusn;
        this.addBatch = addBatch;
    }




    public String getAddstudentname() {
        return addstudentname;
    }

    public void setAddstudentname(String addstudentname) {
        this.addstudentname = addstudentname;
    }

    public String getAddstudentusn() {
        return addstudentusn;
    }

    public void setAddstudentusn(String addstudentusn) {
        this.addstudentusn = addstudentusn;
    }

    public String getAddBatch() {
        return addBatch;
    }

    public void setAddBatch(String addBatch) {
        this.addBatch = addBatch;
    }

    public StudentModel() {

    }



}
