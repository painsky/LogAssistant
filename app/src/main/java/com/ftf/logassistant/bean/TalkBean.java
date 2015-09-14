package com.ftf.logassistant.bean;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Transient;

/**
 * Created by gzu524 on 2015/9/14.
 */
public class TalkBean {
    @Id
    private int id;
    private String talkaddress;
    private int talktype;
    private String talkdate;
    private int talkduration;
    private String talkname;

    public String getTalkaddress() {
        return talkaddress;
    }

    public void setTalkaddress(String talkaddress) {
        this.talkaddress = talkaddress;
    }

    public int getTalktype() {
        return talktype;
    }

    public void setTalktype(int talktype) {
        this.talktype = talktype;
    }

    public String getTalkdate() {
        return talkdate;
    }

    public void setTalkdate(String talkdate) {
        this.talkdate = talkdate;
    }

    public String getTalkname() {
        return talkname;
    }

    public void setTalkname(String talkname) {
        this.talkname = talkname;
    }

    public int getTalkduration() {
        return talkduration;
    }

    public void setTalkduration(int talkduration) {
        this.talkduration = talkduration;
    }

    public TalkBean(String talkaddress, int talktype, String talkdate, int talkduration, String talkname) {
        this.talkaddress = talkaddress;
        this.talktype = talktype;
        this.talkdate = talkdate;
        this.talkduration = talkduration;
        this.talkname = talkname;
    }
}
