package myview.zz.com.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText messageText,numberText;
    private Button button;
       public static TextView textView;//被其他类引用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageText = (EditText)findViewById(R.id.edit_message);
        numberText = (EditText)findViewById(R.id.edit_number);
        button = (Button) findViewById(R.id.button_send);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String number = numberText.getText().toString();
                String message = messageText.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();//获得默认的短信管理器
                //实例化一个pendingIntent的对象，该对象的功能为广播
                PendingIntent mpi = PendingIntent.getBroadcast(MainActivity.this,0,new Intent(),0);
                smsManager.sendTextMessage(number,null,message,mpi,null);//会导致强退
            }
        });

    }
}
