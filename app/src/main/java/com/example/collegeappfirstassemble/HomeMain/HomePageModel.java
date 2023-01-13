package com.example.collegeappfirstassemble.HomeMain;

public class HomePageModel {
    int pic;
    String headline;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public HomePageModel(int pic, String headline) {
        this.pic = pic;
        this.headline = headline;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
