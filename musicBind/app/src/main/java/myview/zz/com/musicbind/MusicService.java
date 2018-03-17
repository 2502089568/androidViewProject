package myview.zz.com.musicbind;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class MusicService extends Service {
    private  MyBinder iBinder;
    private MediaPlayer musicPlayer;

    public class MyBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        iBinder = new MyBinder();
        Toast.makeText(this,"MusicService onBind",Toast.LENGTH_SHORT).show();
        return iBinder;
    }

    public void playMusic() throws IOException {//播放音乐
        Toast.makeText(this,"MusicService playMusic",Toast.LENGTH_SHORT).show();
        //初始化音乐播放器
        musicPlayer = MediaPlayer.create(getApplicationContext(),R.raw.whale);
        musicPlayer.setLooping(true);
        musicPlayer.start();
    }
    public void stopMusic(){
        if(musicPlayer==null)
            return;
        if(musicPlayer.isPlaying()){
            Toast.makeText(this,"MusicService stopMusic",Toast.LENGTH_SHORT).show();
            musicPlayer.stop();
        }
    }

    public void pauseMusic(){
        if(musicPlayer==null)
            return;
        if(musicPlayer.isPlaying()){
            Toast.makeText(this,"MusicService pauseMusic",Toast.LENGTH_SHORT).show();
            musicPlayer.pause();
        }
    }

    public void restartMusic(){
        if(musicPlayer==null)
            return;
        if(!musicPlayer.isPlaying()){
            Toast.makeText(this,"MusicService restartMusic",Toast.LENGTH_SHORT).show();
            musicPlayer.start();
        }
    }


}
