package myview.zz.com.notificationex01;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    NotificationManager mNotificationManager;
    NotificationCompat.Builder mNotificationBuilder;
    Button btn_start,btn_cancel;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationBuilder=new NotificationCompat.Builder(context);
        Intent notificationIntent = new Intent(context,MainActivity.class);//
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,notificationIntent,0);
        mNotificationBuilder.setContentTitle("通知栏标题")
        /*.setContentText("通知栏内容：")

        .setDefaults(Notification.DEFAULT_VIBRATE)
        .setTicker("通知来了")
        .setWhen(System.currentTimeMillis())*/
                .setContentIntent(contentIntent)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setSmallIcon(R.mipmap.ic_launcher);
        btn_start = (Button)findViewById(R.id.startBtn);
        btn_cancel = (Button)findViewById(R.id.cancelBtn);
        btn_start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                mNotificationManager.notify(1,mNotificationBuilder.build());
            }
        });
        btn_cancel.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                mNotificationManager.cancel(1);
            }
        });
    }


}
