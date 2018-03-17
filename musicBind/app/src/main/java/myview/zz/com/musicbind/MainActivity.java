package myview.zz.com.musicbind;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,MusicService.class);
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    //绑定为了接收Ibinder P147
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService=((MusicService.MyBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService=null;
        }
    };


    void start(View v){
        Intent intent = new Intent(this,MusicService.class);
        try {
            musicService.playMusic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void stop(View v){
        Intent intent = new Intent(this,MusicService.class);
        musicService.stopMusic();

    }

    void pause(View v){
        Intent intent = new Intent(this,MusicService.class);
        musicService.pauseMusic();
    }

    void restart(View v){
        Intent intent = new Intent(this,MusicService.class);
        musicService.restartMusic();
    }

}
