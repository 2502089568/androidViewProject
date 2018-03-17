package myview.zz.com.music;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    void start(View v){

        Intent intent = new Intent(MainActivity.this, MusicService.class);

        startService(intent);

    }
    void stop(View v){
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        stopService(intent);
    }

    //没用
    /*void pause(View v){
        mPlayer = new MediaPlayer();
        mPlayer.pause();;
    }

    void restart(View v){
        mPlayer = new MediaPlayer();
        mPlayer.reset();
    }*/
}
