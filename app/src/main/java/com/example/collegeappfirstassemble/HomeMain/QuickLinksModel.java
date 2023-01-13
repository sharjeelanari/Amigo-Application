package com.example.collegeappfirstassemble.HomeMain;

public class QuickLinksModel {

    String head, body, position, context;

    public QuickLinksModel(){

    }

    public QuickLinksModel(String head, String body, String position, String context){
        this.head = head;
        this.body = body;
        this.position = position;
        this.context = context;

    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
