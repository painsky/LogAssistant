package com.ftf.logassistant.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ftf.logassistant.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    @ViewInject(R.id.radio_top)
    private RadioGroup radioGroup;
    @ViewInject(R.id.radio_button_talk)
    private RadioButton radioButtonTalk;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //沉浸式布局需要APT19
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewUtils.inject(this);
        fragmentManager=getSupportFragmentManager();
        radioButtonTalk.setChecked(true);
        radioGroup.setOnCheckedChangeListener(this);
        changeFragment(new TalkFragment(),false);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radio_button_talk:
                changeFragment(new TalkFragment(),true);
                break;
            case R.id.radio_button_sms:
                changeFragment(new SmsFragment(),true);
                break;
            case R.id.radio_button_synchronou:
                changeFragment(new SynchronouFragment(),true);
                break;
        }
    }
    public void changeFragment(Fragment fragment,boolean isFirst){
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment,fragment);
        if (!isFirst){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

    }
}
