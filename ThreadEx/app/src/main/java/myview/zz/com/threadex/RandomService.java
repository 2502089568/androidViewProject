package myview.zz.com.threadex;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class RandomService extends Service {
    private Thread luckThread;

    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this,"幸运大抽奖开始",Toast.LENGTH_SHORT).show();
        luckThread = new Thread(null,backgroundWork,"luckThread");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"抽奖进行中",Toast.LENGTH_SHORT).show();
        if(!luckThread.isAlive()){
            luckThread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"恭喜你中奖了",Toast.LENGTH_SHORT).show();
        luckThread.interrupt();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Runnable backgroundWork = new Runnable() {
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    int ranndomDouble = (int) Math.round(Math.random() * 2 + 1);
                    int ranndomDouble1 = (int) Math.round(Math.random() * 2);
                    int ranndomDouble2 = (int) Math.round(Math.random() * 9);
                    MainActivity.UpdataGUI(ranndomDouble, ranndomDouble1, ranndomDouble2);
                    Thread.sleep(100);
                    if (ranndomDouble == ranndomDouble2 && ranndomDouble1 == 0) {
                        luckThread.interrupt();
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    };

}
