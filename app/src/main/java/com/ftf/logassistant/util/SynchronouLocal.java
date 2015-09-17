package com.ftf.logassistant.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.ftf.logassistant.bean.SmsBean;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;

public class SynchronouLocal {

    private static ArrayList<SmsBean> list=new ArrayList<SmsBean>();
    private static Context context;
    private static String lastSmsTime="0";
    public static void getSmsFromPhone(Context context) throws DbException {
        ContentResolver contentResolver=context.getContentResolver();
        Uri uri=Uri.parse("content://sms/");
        Cursor cursor=contentResolver.query(uri, null, null, null, null);
        DbUtils.DaoConfig config = new DbUtils.DaoConfig(context);
        config.setDbName("db_log");
        DbUtils db = DbUtils.create(config);
        db.createTableIfNotExist(SmsBean.class);
        while (cursor.moveToNext()) {
            String smsnumber=cursor.getString(cursor.getColumnIndex("address"));
            String smsname=getContactNameByAddr(context.getApplicationContext(), smsnumber);
            int smstype=cursor.getInt(cursor.getColumnIndex("type"));
            String smsbody = cursor.getString(cursor.getColumnIndex("body"));
            String smsdate= cursor.getString(cursor.getColumnIndex("date"));
            if (smsdate.compareTo(lastSmsTime)>0){
                lastSmsTime=smsdate;
            }
           SmsBean smsBean=new SmsBean(smsnumber,smstype,smsbody,smsdate,smsname);
            db.save(smsBean);
        }
        SharedPreferences sharedPreferences=context.getSharedPreferences("config.inf", 0);
        sharedPreferences.edit().putString("last_sms",lastSmsTime).commit();
            cursor.close();
        lastSmsTime="0";
    }
//这个静态函数是用来获取短信联系人名称的
    private static String getContactNameByAddr(Context context, String smsnumber) {
        Uri personUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,  Uri.encode(smsnumber));
        Cursor cur = context.getContentResolver().query(personUri,
                new String[] { ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);
        if (cur.moveToFirst()) {
            int nameIdx = cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String name = cur.getString(nameIdx);
            cur.close();
            return name;
        }  else
            cur.close();
        return null;
    }

}
