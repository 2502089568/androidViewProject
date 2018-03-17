package myview.zz.com.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service{
    private MediaPlayer mPlayer;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //启动方式启动
    public void  onCreate() {
        mPlayer = new MediaPlayer();
        Toast.makeText(this,"MusicSevice onCreate()",Toast.LENGTH_SHORT).show();
        mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.whale);
        if(mPlayer==null)
            Log.e("MusicService","-----null");
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"MusicSevice onStart()",Toast.LENGTH_SHORT).show();
        mPlayer.start();
        mPlayer.setLooping(true);//重复播放
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy(){
        Toast.makeText(this,"MusicSevice onDestroy()",Toast.LENGTH_SHORT).show();
        mPlayer.stop();
        super.onDestroy();
    }

}
