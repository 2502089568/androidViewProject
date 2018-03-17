package myview.zz.com.timer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private Timer timer;
    private TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                timer = new Timer();
                timerTask=new TimerTask() {
                    int i=10;//倒计时数目
                    @Override
                    public void run() {
                       Message message = Message.obtain();//获取
                        message.what=i;
                        i--;
                        handler.sendMessage(message);
                    }
                };
                timer.schedule(timerTask,1000,1000);
            }
        });
    }

    private Handler handler = new Handler(){

      @Override
        public void handleMessage(Message msg){
          super.handleMessage(msg);
          if(msg.what>0){
              textView.setText(" "+msg.what);
          }
          else {
              //Handle中可更改UI组件
              textView.setText("开始点火");
              timer.cancel();//不能忘
          }
      }
    };


}
