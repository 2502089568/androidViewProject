package myview.zz.com.okhttprecdemoex01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Operate extends AppCompatActivity {

    //String erroe = "{\"error\":\"invalid_client\",\"error_description\":\"The client credentials are invalid\"}";

    private TextView textView1,textView2;
    private Button button1,button2;
    private EditText editText1,editText2;

    private OkHttpClient getDeviceDate,getHistoriclData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);
        Intent intent = getIntent();
        final String access_token = intent.getStringExtra("data");
        init();
        textView1.setText(access_token);
        button1.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

                GetDeviceDate(editText1.getText().toString(),access_token);
            }
        });

        button2.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                GetHistoriclData(editText2.getText().toString(),access_token);
            }
        });


    }

    public void init(){
        textView1=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.t1);
        button1=(Button)findViewById(R.id.b1);
        button2=(Button)findViewById(R.id.b2);
        editText1=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
    }

    public void GotoSimulation(View v){
        Intent intent = new Intent(Operate.this,Simulation.class);
        startActivity(intent);
    }

    public void GetDeviceDate(String id,String access_token){
            getDeviceDate = new OkHttpClient();
            String url="https://www.bigiot.net/oauth/dev?access_token="+access_token+"&id="+id;
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Call call = getDeviceDate.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    textView2.setText("sorry,failure");
                }

                @Override
                public void onResponse(Call call,Response response) throws IOException {
                    final String str = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setText(str);
                            Log.i("aaa",str);

                        }
                    });
                };

                });

    }

    public void GetHistoriclData(String id,String access_token){
        getHistoriclData=new OkHttpClient();
//        RequestBody formBody = new FormBody.Builder()
//                .add("access_token:",access_token)
//                .add("id",id)
//                .build();
        String url="https://www.bigiot.net/oauth/historydata?access_token="+access_token+"&id="+id;
        Log.e("HistoriclDataURL",url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = getHistoriclData.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                textView2.setText("sorry,failure");
            }

            @Override
            public void onResponse(Call call,Response response) throws IOException {
                final String str = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText(str);
                        Log.i("aaa",str);

                    }
                });

            }
        });
    }




}
