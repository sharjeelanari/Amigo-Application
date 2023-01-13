package com.example.collegeappfirstassemble.EliteDivision.EliteAdapModel;

public class FreelanceModel {

    public FreelanceModel() {

    }

    String freelance_topic;
    String freelance_work;
    String freelance_desc;
    String freelance_deadline;
    String freelance_skills;
    String freelance_price;
    String freelance_name;

    public FreelanceModel(String freelance_topic, String freelance_work, String freelance_desc, String freelance_deadline, String freelance_skills
            , String freelance_price, String freelance_name) {
        this.freelance_topic = freelance_topic;
        this.freelance_work = freelance_work;
        this.freelance_desc = freelance_desc;
        this.freelance_deadline = freelance_deadline;
        this.freelance_skills = freelance_skills;
        this.freelance_price = freelance_price;
        this.freelance_name = freelance_name;
    }



    public String getFreelance_topic() {
        return freelance_topic;
    }

    public void setFreelance_topic(String freelance_topic) {
        this.freelance_topic = freelance_topic;
    }

    public String getFreelance_work() {
        return freelance_work;
    }

    public void setFreelance_work(String freelance_work) {
        this.freelance_work = freelance_work;
    }

    public String getFreelance_desc() {
        return freelance_desc;
    }

    public void setFreelance_desc(String freelance_desc) {
        this.freelance_desc = freelance_desc;
    }

    public String getFreelance_deadline() {
        return freelance_deadline;
    }

    public void setFreelance_deadline(String freelance_deadline) {
        this.freelance_deadline = freelance_deadline;
    }

    public String getFreelance_skills() {
        return freelance_skills;
    }

    public void setFreelance_skills(String freelance_skills) {
        this.freelance_skills = freelance_skills;
    }

    public String getFreelance_price() {
        return freelance_price;
    }

    public void setFreelance_price(String freelance_price) {
        this.freelance_price = freelance_price;
    }

    public String getFreelance_name() {
        return freelance_name;
    }

    public void setFreelance_name(String freelance_name) {
        this.freelance_name = freelance_name;
    }
}
