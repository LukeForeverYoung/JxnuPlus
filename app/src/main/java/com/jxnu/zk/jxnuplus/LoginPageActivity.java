package com.jxnu.zk.jxnuplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginPageActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mPSW;
    private Button mRegist;
    private Button mQS;
    private Button mLogin;
    private TextView mUsername;
    private TextView mpsw;
    private RadioGroup mRadioGroup;
    private RadioButton mStudentR;
    private RadioButton mTeacherR;
    private String mNum;
    private String mPsw;
    private String mType;
    private int typeflag = 0;
    private String baseUrl = "http://jwc.jxnu.edu.cn/Default_Login.aspx";
    private HttpURLConnection urlConn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mUsername = (TextView)findViewById(R.id.title1);
        mpsw = (TextView)findViewById(R.id.title2);
        mUserName = (EditText)findViewById(R.id.userName);
        mPSW = (EditText)findViewById(R.id.passWord);
        mRegist = (Button)findViewById(R.id.registButton);
        mQS = (Button)findViewById(R.id.qsButton);
        mLogin = (Button)findViewById(R.id.loginButton);
        mRadioGroup = (RadioGroup)findViewById(R.id.Radio);
        mStudentR = (RadioButton)findViewById(R.id.studentR);
        mTeacherR = (RadioButton)findViewById(R.id.teacherR);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mStudentR.getId() == checkedId) {
                    typeflag = 0;
                    mUsername.setText("学号:");
                    mUserName.setHint("请输入学号");
                    mType = "Student";
                } else if (mTeacherR.getId() == checkedId) {
                    typeflag = 1;
                    mUsername.setText("工号:");
                    mUserName.setHint("请输入工号");
                    mType = "Teacher";
                }
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Map<String, String> post = new HashMap<String, String>();
                post.put("__VIEWSTATE", "/wEPDwUJNjk1MjA1MTY0D2QWAgIBD2QWBAIBD2QWBGYPEGRkFgFmZ" +
                        "AIBDxAPFgYeDURhdGFUZXh0RmllbGQFDOWNleS9jeWQjeensB4ORGF0YVZhbHVlRmllbG" +
                        "QFCeWNleS9jeWPtx4LXyFEYXRhQm91bmRnZBAVQAnkv53ljavlpIQJ6LSi5Yqh5aSEEui" +
                        "0ouaUv+mHkeiejeWtpumZohLmiJDkurrmlZnogrLlrabpmaIS5Z+O5biC5bu66K6+5a2m" +
                        "6ZmiEuWIneetieaVmeiCsuWtpumZogzkvKDmkq3lrabpmaIh5b2T5Luj5b2i5oCB5paH6" +
                        "Im65a2m56CU56m25Lit5b+DD+WFmuWKnuOAgeagoeWKngnmoaPmoYjppoYV5Zyw55CG5L" +
                        "iO546v5aKD5a2m6ZmiG+WvueWkluiBlOe7nOS4juaOpeW+heS4reW/gxjpq5jnrYnmlZn" +
                        "ogrLnoJTnqbbkuK3lv4MY5Zu96ZmF5ZCI5L2c5LiO5Lqk5rWB5aSEEuWbvemZheaVmeiC" +
                        "suWtpumZog/lkI7li6Tkv53pmpzlpIQY5YyW5bel5byA5Y+R56CU56m25Lit5b+DEuWMl" +
                        "uWtpuWMluW3peWtpumZognln7rlu7rlpIQb6K6h566X5py65L+h5oGv5bel56iL5a2m6Z" +
                        "miKuaxn+ilv+ecgeWFieeUteWtkOS4jumAmuS/oemHjeeCueWunumqjOWupA/mlZnluIj" +
                        "mlZnogrLlpIQJ5pWZ5Yqh5aSEDOaVmeiCsuWtpumZog/lhpvkuovmlZnnoJTlrqQS56eR" +
                        "5oqA44CB56S+56eR5aSEEuenkeWtpuaKgOacr+WtpumZohjor77nqIvkuI7mlZnlrabno" +
                        "JTnqbbmiYAY56a76YCA5LyR5bel5L2c5Yqe5YWs5a6kEueQhuWMlua1i+ivleS4reW/gx" +
                        "vljoblj7LmlofljJbkuI7ml4XmuLjlrabpmaIV6ams5YWL5oCd5Li75LmJ5a2m6ZmiDOe" +
                        "+juacr+WtpumZohLlhY3otLnluIjojIPnlJ/pmaIS5Lq65omN5Lqk5rWB5Lit5b+DCeS6" +
                        "uuS6i+WkhAzova/ku7blrabpmaIJ5ZWG5a2m6ZmiG+iuvuWkh+S4juWunumqjOWupOeuo" +
                        "eeQhuWkhBLnlJ/lkb3np5HlrablrabpmaIS5biI6LWE5Z+56K6t5Lit5b+DG+aVsOWtpu" +
                        "S4juS/oeaBr+enkeWtpuWtpumZohLntKDotKjmlZnogrLkuK3lv4MM5L2T6IKy5a2m6Zm" +
                        "iCeWbvuS5pummhg/lpJblm73or63lrabpmaIe5aSW57GN5LiT5a62566h55CG5pyN5Yqh" +
                        "5Lit5b+DEuWkluivreiAg+ivleS4reW/gw/mlofljJbnoJTnqbbpmaIJ5paH5a2m6ZmiG" +
                        "+eJqeeQhuS4jumAmuS/oeeUteWtkOWtpumZoh7njrDku6PmlZnogrLmioDmnK/lupTnlK" +
                        "jkuK3lv4MV5qCh5Y+L5bel5L2c5Yqe5YWs5a6kFeagoeWbree9keeuoeeQhuS4reW/gwz" +
                        "lv4PnkIblrabpmaIS5paw6Ze75L+h5oGv5Lit5b+DD+WtpuaKpeadguW/l+ekvg/lrabn" +
                        "p5Hlu7rorr7lpIQJ5a2m55Sf5aSEDOeglOeptueUn+mZohLoibrmnK/noJTnqbbkuK3lv" +
                        "4MM6Z+z5LmQ5a2m6ZmiD+aLm+eUn+WwseS4muWkhAzmlL/ms5XlrabpmaIVQAgxODAgIC" +
                        "AgIAgxNzAgICAgIAg2ODAwMCAgIAg0NTAgICAgIAg2MzAwMCAgIAg4MjAwMCAgIAg2NDA" +
                        "wMCAgIAgzODIgICAgIAgxMzAgICAgIAgxMDkgICAgIAg0ODAwMCAgIAgxMzIgICAgIAgz" +
                        "OTAgICAgIAgxNjAgICAgIAg2OTAwMCAgIAg4NzAwMCAgIAgzNjUgICAgIAg2MTAwMCAgI" +
                        "AgxNDQgICAgIAg2MjAwMCAgIAgzODEgICAgIAgyNTAgICAgIAgyNDAwMCAgIAg1MDAwMC" +
                        "AgIAgzNzAwMCAgIAgxNDAgICAgIAg4MTAwMCAgIAgzMjQgICAgIAgxMDQgICAgIAgzMjA" +
                        "gICAgIAg1ODAwMCAgIAg0NjAwMCAgIAg2NTAwMCAgIAg1NzAwMCAgIAgzMzAgICAgIAgx" +
                        "NTAgICAgIAg2NzAwMCAgIAg1NDAwMCAgIAgzNjAgICAgIAg2NjAwMCAgIAgzMTAgICAgI" +
                        "Ag1NTAwMCAgIAgzODAwMCAgIAg1NjAwMCAgIAgyOTAgICAgIAg1MjAwMCAgIAg4OTAwMC" +
                        "AgIAgzMDAgICAgIAgzNTAgICAgIAg1MTAwMCAgIAg2MDAwMCAgIAgzNjEgICAgIAgxODk" +
                        "gICAgIAgzMDQgICAgIAg0OTAwMCAgIAgxMDYgICAgIAg0MjAgICAgIAgxMzYgICAgIAgx" +
                        "MTAgICAgIAgxOTAgICAgIAgxNDYgICAgIAg1MzAwMCAgIAg0NDAgICAgIAg1OTAwMCAgI" +
                        "BQrA0BnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2" +
                        "dnZ2dnZ2dnZ2dnZ2dnZ2dnZGQCAw8PFgIeB1Zpc2libGVoZGQYAQUeX19Db250cm9sc1J" +
                        "lcXVpcmVQb3N0QmFja0tleV9fFgEFClJlbWVuYmVyTWXqOH7cZh1zUpeXlhAFbi+xgs47" +
                        "IGRtfMhAmYwxYUraZA==");
                post.put("rblUserType", mType);
                if(typeflag == 1){
                    post.put("StuNum", mNum);
                    post.put("TeaNum","");
                }else
                {
                    post.put("StuNum","");
                    post.put("TeaNum", mNum);
                }
                post.put("Password", mPsw);
                post.put("login","登录");*/
                URL url = null;
                try {
                    //通过openConnection 连接
                    url = new URL(baseUrl);
                    urlConn=(HttpURLConnection)url.openConnection();
                    //设置输入和输出流
                    urlConn.setDoOutput(true);
                    urlConn.setDoInput(true);
                    //设置网页提交方式
                    urlConn.setRequestMethod("POST");
                    urlConn.setConnectTimeout(10000);
                    urlConn.setUseCaches(false);
                    // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                    urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
                    // 要注意的是connection.getOutputStream会隐含的进行connect。
                    urlConn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.82 Safari/537.36");
                    urlConn.setRequestProperty("Accept-Language","zh-CN");
                    urlConn.setRequestProperty("Accept-Encoding","gzip, deflate");
                    urlConn.setRequestProperty("Connection","keep-alive");
                    urlConn.setRequestProperty("Content-Length",String.valueOf(4105));
                    urlConn.connect();
                    //DataOutputStream流
                    DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());
                    //要上传的参数
                    String param = new String();
                    if(typeflag == 0){
                        param = "rblUserType" + mType +
                                "&StuNum" +mNum +
                                "&TeaNum" + " " +
                                "&Password" + mPsw +
                                "&login" + "登录";
                    }else if(typeflag == 1){
                        param = "rblUserType" + mType +
                                "&StuNum" +" " +
                                "&TeaNum" + mNum +
                                "&Password" + mPsw +
                                "&login" + "登录";
                    }
                    out.writeBytes(param);
                    //刷新、关闭
                    out.flush();
                    // out.close();
//                    if(urlConn.getResponseCode() == 200)
                        Log.d("反馈",String.valueOf(urlConn.getResponseCode()));

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
