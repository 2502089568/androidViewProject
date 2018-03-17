package myview.zz.com.pagejumps;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    private MyCountDownTimer mc;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },3000);*/
        tv = (TextView) findViewById(R.id.textView1);
        mc = new MyCountDownTimer(4000, 1000);
        mc.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
    private Handler handler=new Handler();
    /**
     * 继承 CountDownTimer 防范
     *
     * 重写 父类的方法 onTick() 、 onFinish()
     */
    class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture
         * 表示以毫秒为单位 倒计时的总数
         *
         * 例如 millisInFuture=1000 表示1秒
         *
         * @param countDownInterval
         * 表示 间隔 多少微秒 调用一次 onTick 方法
         *
         * 例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        public void onFinish() {
            tv.setText("倒计时(1)");//正在跳转
        }
        public void onTick(long millisUntilFinished) {
            tv.setText("倒计时(" + (millisUntilFinished) / 1000 + ")");
        }
    }
    public void jumpOver(View v) {
        Intent intent=new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
