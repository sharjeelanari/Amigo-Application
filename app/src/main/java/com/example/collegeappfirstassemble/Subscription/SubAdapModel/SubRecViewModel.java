package com.example.collegeappfirstassemble.Subscription.SubAdapModel;

public class SubRecViewModel {

    public SubRecViewModel() {
    }
    String addcompanyname;
    String addscreentype;
    String adddevices;
    String add_mem_no_available;
    String add_mem_needed;
    String add_priceofsub;

    public SubRecViewModel(String addcompanyname, String addscreentype, String adddevices, String add_mem_no_available, String add_mem_needed, String add_priceofsub) {
        this.addcompanyname = addcompanyname;
        this.addscreentype = addscreentype;
        this.adddevices = adddevices;
        this.add_mem_no_available = add_mem_no_available;
        this.add_mem_needed = add_mem_needed;
        this.add_priceofsub = add_priceofsub;
    }




    public String getAddcompanyname() {
        return addcompanyname;
    }

    public void setAddcompanyname(String addcompanyname) {
        this.addcompanyname = addcompanyname;
    }

    public String getAddscreentype() {
        return addscreentype;
    }

    public void setAddscreentype(String addscreentype) {
        this.addscreentype = addscreentype;
    }

    public String getAdddevices() {
        return adddevices;
    }

    public void setAdddevices(String adddevices) {
        this.adddevices = adddevices;
    }

    public String getAdd_mem_no_available() {
        return add_mem_no_available;
    }

    public void setAdd_mem_no_available(String add_mem_no_available) {
        this.add_mem_no_available = add_mem_no_available;
    }

    public String getAdd_mem_needed() {
        return add_mem_needed;
    }

    public void setAdd_mem_needed(String add_mem_needed) {
        this.add_mem_needed = add_mem_needed;
    }

    public String getAdd_priceofsub() {
        return add_priceofsub;
    }

    public void setAdd_priceofsub(String add_priceofsub) {
        this.add_priceofsub = add_priceofsub;
    }





}
