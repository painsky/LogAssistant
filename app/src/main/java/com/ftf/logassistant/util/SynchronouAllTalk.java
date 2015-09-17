package com.ftf.logassistant.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import com.ftf.logassistant.bean.TalkBean;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import java.util.ArrayList;

public class SynchronouAllTalk {

    private static ArrayList<TalkBean> list=new ArrayList<TalkBean>();
    private static Context context;
    private static  String lastTalkTime="0";
    public static void getDataFromPhone(Context context) throws DbException {
        ContentResolver contentResolver=context.getContentResolver();
        Uri uri=Uri.parse("content://call_log/calls");
        Cursor cursor=contentResolver.query(uri, null, null, null, null);
        DbUtils.DaoConfig config = new DbUtils.DaoConfig(context);
        config.setDbName("db_log");
        DbUtils db = DbUtils.create(config);
        db.createTableIfNotExist(TalkBean.class);
        while (cursor.moveToNext()) {
            String talkname=cursor.getString(cursor.getColumnIndex("name"));
            String talknumber=cursor.getString(cursor.getColumnIndex("number"));
            int talktype=cursor.getInt(cursor.getColumnIndex("type"));
            int talkduration = cursor.getInt(cursor.getColumnIndex("duration"));
            String talkdate= cursor.getString(cursor.getColumnIndex("date"));
            if (talkdate.compareTo(lastTalkTime)>0){
                lastTalkTime=talkdate;
            }
            TalkBean talkBean=new TalkBean(talknumber,talktype,talkdate,talkduration,talkname);
            db.save(talkBean);
        }
        SharedPreferences sharedPreferences=context.getSharedPreferences("config.inf",0);
        sharedPreferences.edit().putString("last_talk",lastTalkTime).commit();
            cursor.close();
        lastTalkTime="0";
    }

}
