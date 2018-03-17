package myview.zz.com.okhttprecdemoex01;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/*此为TCP建立连接的方法（模拟温度传感器传输数据）
*               //os.close();
                //is.close();
                //br1.close();
                //br.close();
                //以上关了会直接断开连接???????
*/

/**/

public class Simulation extends AppCompatActivity {

    private TextView textView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Handler handler;
    private Handler handler1;
    private Socket socket=null;
    private TextView textView1;
    private EditText editText;
    private Thread1 thread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        textView = (TextView) findViewById(R.id.tv);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        textView1 = (TextView) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText2);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                textView.setText(msg.obj.toString());
            }
        };
        handler1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                textView1.setText(msg.obj.toString());
            }
        };

    }

    Thread thread = new Thread() {
        @Override
        public void run() {

            try {
                Log.i("miao", "#########################################" + "clicked");
                socket = new Socket("121.42.180.30", 8282);
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String data = br.readLine();
                Message msg = new Message();
                msg.obj = data;
                handler.sendMessage(msg);

                Log.i("miao", "#########################################" + "Connected");
                OutputStream os = socket.getOutputStream();
                os.write("{\"M\":\"checkin\",\"ID\":\"995\",\"K\":\"c418d3f28\"}\n".getBytes());
                is = socket.getInputStream();
                BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
                String data1 = "  ";
                data1 = br1.readLine();
                Log.i("miao", "#########################################" + data);

                //os.close();
                //is.close();
                //br1.close();
                //br.close();
                //以上关了会直接断开连接???????


                Message msg2 = new Message();
                msg2.obj = data1;
                handler.sendMessage(msg2);

                //this.sleep(1000);
                Message msg1 = new Message();
                if (socket.isClosed()) {
                    Log.i("miao", "#########################################" + "连接断开");
                    msg1.obj= "连接失败";
                    handler1.sendMessage(msg1);
                }
                else {
                    Log.i("miao", "#########################################" + "连接打开");
                    msg1.obj= "连接打开";
                    handler1.sendMessage(msg1);
                }
            } catch (Exception e) {
                Log.i("miao", "#########################################" + "Exception");
                e.printStackTrace();
            }
        }
    };

    class  Thread1 extends Thread {
        private int a;
        public Thread1(int a){
            this.a=a;
        }
        public Thread1(){
        }
        @Override
        public void run() {

            try {  Message msg1 = new Message();
                if (socket.isClosed()) {
                    Log.i("miao", "#########################################" + "连接断开");
                    msg1.obj= "连接断开";
                    handler1.sendMessage(msg1);
                }
                else {
                    Log.i("miao", "#########################################" + "连接打开");
                    msg1.obj= "发送成功";
                    handler1.sendMessage(msg1);
                }
                Log.i("miao", "#########################################" + "Connected");
                OutputStream os1 = socket.getOutputStream();
                String postData = "{\"M\":\"update\",\"ID\":\"995\",\"V\":{\"861\":\""+a+"\"}}\n";
                os1.write(postData.getBytes());
                Log.i("miao", "5555555555555555555555555555555555555555" + postData);
            } catch (Exception e) {
                Log.i("miao", "#########################################" + "Exception");
                Message msg3 = new Message();
                e.printStackTrace();
                msg3.obj= "连接断开,本次数据未发送，请重新发送";
                handler1.sendMessage(msg3);
                thread.start();
            }
        }

        public void End(View v){
            finish();
        }
    }


    public void tcpClientStart(View view) {
        thread.start();
    }

    public void tcpClientTran(View view) {
        Thread1 thread1=new Thread1(Integer.parseInt(editText.getText().toString()));
        thread1.start();
    }

    public void tcpClientEnd(View view) {
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            textView1.setText("连接未建立，请先建立连接");
        }
        if (socket.isClosed()) {
            Log.i("miao", "#########################################" + "连接断开");
            textView1.setText("连接断开");

        } else {
            Log.i("miao", "#########################################" + "连接打开");
            textView1.setText("连接打开");
        }

        textView.setText("连接断开");
    }

}



