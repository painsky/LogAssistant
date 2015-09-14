package com.ftf.logassistant.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

import com.ftf.logassistant.bean.TalkBean;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SynchronouTalk {

    private static ArrayList<TalkBean> list=new ArrayList<TalkBean>();
    private static Context context;
    public static void getDataFromPhone(Context context) throws DbException {
        ContentResolver contentResolver=context.getContentResolver();
        Uri uri=Uri.parse("content://call_log/calls");
        Cursor cursor=contentResolver.query(uri, null, null, null, null);
        DbUtils.DaoConfig config = new DbUtils.DaoConfig(context);
        config.setDbName("db_log");
        DbUtils db = DbUtils.create(config);
        db.createTableIfNotExist(TalkBean.class);
        int i=0;
        while (cursor.moveToNext()) {
            String talkname=cursor.getString(cursor.getColumnIndex("name"));
            String talknumber=cursor.getString(cursor.getColumnIndex("number"));
            int talktype=cursor.getInt(cursor.getColumnIndex("type"));
            int talkduration = cursor.getInt(cursor.getColumnIndex("duration"));
            String talkdata= cursor.getString(cursor.getColumnIndex("date"));
            TalkBean talkBean=new TalkBean(talknumber,talktype,talkdata,talkduration,talkname);
            db.save(talkBean);
        }
            cursor.close();
    }

}
