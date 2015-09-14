package com.ftf.logassistant.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;

import com.ftf.logassistant.bean.SmsBean;
import com.ftf.logassistant.bean.TalkBean;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SynchronouSms {

    private static ArrayList<SmsBean> list=new ArrayList<SmsBean>();
    private static Context context;
    public static void getSmsFromPhone(Context context) throws DbException {
        ContentResolver contentResolver=context.getContentResolver();
        Uri uri=Uri.parse("content://sms/");
        Cursor cursor=contentResolver.query(uri, null, null, null, null);
        DbUtils.DaoConfig config = new DbUtils.DaoConfig(context);
        config.setDbName("db_log");
        DbUtils db = DbUtils.create(config);
        db.createTableIfNotExist(SmsBean.class);
        int i=0;
        while (cursor.moveToNext()) {
            String smsnumber=cursor.getString(cursor.getColumnIndex("address"));
            String smsname=getContactNameByAddr(context.getApplicationContext(), smsnumber);
            int smstype=cursor.getInt(cursor.getColumnIndex("type"));
            String smsbody = cursor.getString(cursor.getColumnIndex("body"));
            String smsdate= cursor.getString(cursor.getColumnIndex("date"));
           SmsBean smsBean=new SmsBean(smsnumber,smstype,smsbody,smsdate,smsname);
            db.save(smsBean);
            i++;
        }
        System.out.println(i+"ssssssssssssssssssssssssssssssssssssssssssssssssssss");
            cursor.close();
    }

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
