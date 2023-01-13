package com.example.collegeappfirstassemble.OtpSignup;

public class TeacherModel {
    String name, mobile, TeacherId, College, TeacherEmail, uid;


    public TeacherModel(){

    }

    public TeacherModel(String Name, String Mobile, String teacherid, String college, String email
    ,String uid){
        this.name = Name;
        this.mobile = Mobile;
        this.TeacherId= teacherid;
        this.College = college;
        this.TeacherEmail = email;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        TeacherId = teacherId;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }

    public String getTeacherEmail() {
        return TeacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        TeacherEmail = teacherEmail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
