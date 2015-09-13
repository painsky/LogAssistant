package com.ftf.logassistant.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ftf.logassistant.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.radio_top)
    private RadioGroup radioGroup;
    @ViewInject(R.id.radio_button_talk)
    private RadioButton radioButtonTalk;
    @ViewInject(R.id.radio_button_sms)
    private RadioButton radioButtonSms;
    @ViewInject(R.id.radio_button_synchronou)
    private RadioButton radioButtonSynchronou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_top);
        ViewUtils.inject(this);
    }


}
