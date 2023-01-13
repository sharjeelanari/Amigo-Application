package com.example.collegeappfirstassemble.OtpSignup;

public class StudentInfo {
    public StudentInfo(){

    }

    private String mobile;
    private String StudentEmail;
    private String name;
    private String StudentUSN;
    private String CollegeName, uid;

    public StudentInfo(String mobile, String studentEmail,
                       String name, String studentUSN, String collegeName, String uid) {
        this.mobile = mobile;
        this.StudentEmail = studentEmail;
        this.name = name;
        this.StudentUSN = studentUSN;
        this.CollegeName = collegeName;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String userid) {
        this.uid = userid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentUSN() {
        return StudentUSN;
    }

    public void setStudentUSN(String studentUSN) {
        StudentUSN = studentUSN;
    }

    public String getCollegeName() {
        return CollegeName;
    }

    public void setCollegeName(String collegeName) {
        CollegeName = collegeName;
    }
}
