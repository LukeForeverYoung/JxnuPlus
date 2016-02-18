package com.jxnu.zk.jxnuplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mPSW;
    private Button mRegist;
    private Button mQS;
    private Button mLogin;
    private TextView mUsername;
    private TextView mpsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsername = (TextView)findViewById(R.id.title1);
        mpsw = (TextView)findViewById(R.id.title2);
        mUserName = (EditText)findViewById(R.id.userName);
        mPSW = (EditText)findViewById(R.id.passWord);
        mRegist = (Button)findViewById(R.id.registButton);
        mQS = (Button)findViewById(R.id.qsButton);
        mLogin = (Button)findViewById(R.id.loginButton);
    }
}
