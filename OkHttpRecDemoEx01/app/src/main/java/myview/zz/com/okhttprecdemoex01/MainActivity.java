package myview.zz.com.okhttprecdemoex01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static myview.zz.com.okhttprecdemoex01.R.id.tv;

public class MainActivity extends BaseActivity{

    private char flage;//用于判断是否要保留密码
    private TextView textView,textView2;
    private OkHttpClient mOkHttpClient;
    private EditText et_user,et_pass,et_apipass,et_iduser;
    private CheckBox cb_remember;
    private Button btn_login,btn_reg;
    private String username;
    private String userpass;
    private String apipass;
    private String iduser;
    private Handler mHandler;
    private final static String SP_INFOS="login";
    private final static String USERNAME="uname";
    private final static String USERPASS="upass";
    private final static String IDUSER="usid";
    private final static String APIPASS="apass";
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewByID(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));

        mToolbar.setVisibility(View.GONE);
        hideStatusBar();

        init();
        username=et_user.getText().toString();
        userpass=et_pass.getText().toString();
        iduser=et_iduser.getText().toString();
        apipass=et_apipass.getText().toString();
        if(username!=null&&userpass!=null&iduser!=null&&apipass!=null){cb_remember.setChecked(true);//解决
        }
        btn_login.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
               username=et_user.getText().toString();
                userpass=et_pass.getText().toString();
                iduser=et_iduser.getText().toString();
                apipass=et_apipass.getText().toString();

                postAsynHttpGetAccess_token(username,userpass,iduser,apipass);

                mHandler=new Handler(){
                    @Override
                    public void handleMessage(Message mse){
                        super.handleMessage(mse);
                        Toast.makeText(MainActivity.this,mse.obj.toString(),Toast.LENGTH_LONG).show();
                    }
                };
            }
        });
        btn_reg.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"程序员还在加班加点赶进度中，请先自行去贝壳物联注册吧~",Toast.LENGTH_LONG).show();
            }});
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    private void init(){
        textView = (TextView)findViewById(tv);
        textView2 = (TextView)findViewById(R.id.tv1);
        et_user=(EditText)findViewById(R.id.et_user);
        et_pass=(EditText)findViewById(R.id.et_pass);
        cb_remember=(CheckBox)findViewById(R.id.cb_remember);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_reg=(Button)findViewById(R.id.btn_reg);
        et_apipass=(EditText)findViewById(R.id.et_apipass);
        et_iduser=(EditText)findViewById(R.id.et_iduser);
        flage='a';
    }



    public void rememberMe(String uname,String upass,String usid,String apass){
        SharedPreferences sp =getSharedPreferences(SP_INFOS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(USERNAME,uname);
        editor.putString(USERPASS,upass);
        editor.putString(IDUSER,usid);
        editor.putString(APIPASS,apass);
        editor.commit();//提交
    }

    @Override
    protected void onStop(){
        super.onStop();

        String username1 = et_user.getText().toString();
        String userpass1=et_pass.getText().toString();
        String iduser1=et_iduser.getText().toString();
        String apipass1=et_apipass.getText().toString();


        if(cb_remember.isChecked()&&flage=='a'||flage=='b'){
            if(flage=='a'&&username1!=username||userpass1!=userpass||iduser1!=iduser||apipass1!=apipass){
                this.rememberMe(username,userpass,iduser,apipass);
            }else
            this.rememberMe(et_user.getText().toString(),et_pass.getText().toString(),et_iduser.getText().toString(),et_apipass.getText().toString());
        }  else  this.rememberMe(et_user.getText().toString(),null,et_iduser.getText().toString(),null);
    }

    @Override
    protected void onStart(){
        super.onStart();
        checkIfRemember();
    }



    public void checkIfRemember(){
        SharedPreferences sp =getSharedPreferences(SP_INFOS,MODE_PRIVATE);
        username=sp.getString(USERNAME,null);
        userpass=sp.getString(USERPASS,null);
        iduser=sp.getString(IDUSER,null);
        apipass=sp.getString(APIPASS,null);
        if(username!=null&&userpass!=null&iduser!=null&&apipass!=null){
            et_user.setText(username);
            et_pass.setText(userpass);
            et_iduser.setText(iduser);
            et_apipass.setText(apipass);
            //cb_remember.setChecked(true);//bug当进入第二个页面后在退回，勾选会莫名被加上
        }else {
            et_user.setText(username);
            et_iduser.setText(iduser);
            cb_remember.setChecked(false);
        }

    }


   private void postAsynHttpGetAccess_token(String username,String userpass,String iduser,String apipass) {

        mOkHttpClient=new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                //.add("client_id","97")
                //.add("username",909)
                .add("client_id",username)
                .add("client_secret",userpass)
                //.add("client_secret","d779402bdc")
                .add("username",iduser)
                 .add("password",apipass)
                //.add("password", "5326dd5bc0")
                 .add("grant_type","password")
                .build();
        Request request = new Request.Builder()
                .url("https://www.bigiot.net/oauth/token")
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String erroe="错误，可能您已到达没有网络的异次元~\n"+"错误信息："+e.toString();
                Message mse = new Message();
                mse.obj=erroe;
                mHandler.sendMessage(mse);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 final String access_token;
                final String str = response.body().string();
                Log.i("wangshu", str);
                String erroReg="^.*r\":\"(.*)\",\"er";//判断是否登录失败
                Pattern erroPat= Pattern.compile(erroReg);
                Matcher erroMat= erroPat.matcher(str);
                String JudgeLogin;
                if(erroMat.find()){
                    if(erroMat.group(1).equals("invalid_client")){
                        JudgeLogin="应用id或应用密码错误";
                        Message mse = new Message();
                        mse.obj=JudgeLogin;
                        mHandler.sendMessage(mse);
                        flage='c';
                    }
                    if(erroMat.group(1).equals("invalid_grant")){
                        JudgeLogin="用户id或用户apikey错误";
                        Message mse = new Message();
                        mse.obj=JudgeLogin;
                        mHandler.sendMessage(mse);
                        flage='c';
                    }
                    else{
                        JudgeLogin="未知错误,可能是应用id或应用密码错误";
                        Message mse = new Message();
                        mse.obj=JudgeLogin;
                        mHandler.sendMessage(mse);
                        flage='c';
                    }
                }else{
                    //登录成功
                    final Matcher matcher;
                    //字符串截取
                    String regex = "^.*en\":\"(.*)\",\"ex";
                    Pattern pattern = Pattern.compile(regex);
                    matcher = pattern.matcher(str);
                    if (matcher.find()) {
                        access_token=matcher.group(1);

                    }else access_token="erroe";
                    Log.i("wangshu", str);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Toast.makeText(MainActivity.this,"成功得到数据",Toast.LENGTH_LONG).show();
                            textView.setText("access_token为："+access_token);
                            //textView2.setText(access_token);
                            if(access_token.equals("erroe")){
                                Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                            }else {Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();}
                        }
                    });
                    if(access_token.equals("erroe")){

                    }else {flage='b';}
                    // Bundle bundle = new Bundle();
                    // bundle.putString("data",access_token);
                    if(flage=='b'){
                        Intent intent = new Intent(MainActivity.this,Operate.class);
                        intent.putExtra("data",access_token);
                        startActivity(intent);}
                }
            }

        });
    }
}

