package com.ftf.logassistant.bean;

import com.lidroid.xutils.db.annotation.Id;

/**
 * Created by gzu524 on 2015/9/14.
 */
public class SmsBean {
    @Id
    private int id;
    private String smsaddress;
    private int smstype;
    private String smsbody;
    private String smsdate;
    private String smsname;
    public int getSmstype() {
        return smstype;
    }

    public void setSmstype(int smstype) {
        this.smstype = smstype;
    }

    public String getSmsaddress() {
        return smsaddress;
    }

    public void setSmsaddress(String smsaddress) {
        this.smsaddress = smsaddress;
    }

    public String getSmsbody() {
        return smsbody;
    }

    public void setSmsbody(String smsbody) {
        this.smsbody = smsbody;
    }

    public String getSmsdate() {
        return smsdate;
    }

    public void setSmsdate(String smsdate) {
        this.smsdate = smsdate;
    }

    public String getSmsname() {
        return smsname;
    }

    public void setSmsname(String smsname) {
        this.smsname = smsname;
    }

    public SmsBean(String smsaddress, int smstype, String smsbody, String smsdate,String smsname) {
        this.smsaddress = smsaddress;
        this.smstype = smstype;
        this.smsbody = smsbody;
        this.smsdate = smsdate;
        this.smsname=smsname;
    }
}
