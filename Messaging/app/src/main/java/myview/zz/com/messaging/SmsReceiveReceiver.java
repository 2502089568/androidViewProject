package myview.zz.com.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;

import java.util.Date;

public class SmsReceiveReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        /*throw new UnsupportedOperationException("Not yet implemented");*/
        String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
        SimpleDateFormat dateFormat =new SimpleDateFormat ("hh:mm,MM月dd日");
        if(intent.getAction().equals(SMS_ACTION)){
            Bundle bundle =intent.getExtras();
            if(bundle!=null){
                //由短信内容组成数组对象
                Object[] objects =(Object[])bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[objects.length];
                for(int i=0;i<objects.length;i++){
                    //为原始的PDU创建一个SmsMessage对象
                    messages[i]=SmsMessage.createFromPdu((byte[])objects[i]);
                }
                String smsBody = messages[0].getMessageBody();
                String smsSender = messages[0].getDisplayOriginatingAddress();
                //获取当前系统时间
                String smsReceiveTime = dateFormat.format(new Date());
                MainActivity.textView.setText("内容:"+smsBody+"\n"+"发送者："+smsSender+"\n"+"接收时间："+smsReceiveTime);
            }
        }
    }
}
