package com.example.juserzhang.gridview.Music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by West on 2015/11/10.
 */
public class MusicService extends Service {

    private String[] musicDir = new String[]{
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/再见只是陌生人.mp3",
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/以后的以后.mp3",
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/秦淮八艳.mp3",
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/订婚记.mp3"};
    private int musicIndex = 1;

    public final IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    public static MediaPlayer mp = new MediaPlayer();

    public MusicService() {
        try {
            mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/以后的以后.mp3");
            //mp.setDataSource(Environment.getDataDirectory().getAbsolutePath()+"/You.mp3");
            mp.prepare();
            musicIndex = 1;
        } catch (Exception e) {
            Log.d("hint", "can't get to the song");
            e.printStackTrace();
        }
    }

    public void playOrPause() {
        if (mp.isPlaying()) {
            mp.pause();
        } else {
            mp.start();
        }
    }

    public void stop() {
        if (mp != null) {
            mp.stop();
            try {
                mp.prepare();
                mp.seekTo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void nextMusic() {
        if (mp != null && musicIndex < 3) {
            mp.stop();
            try {
                mp.reset();
                mp.setDataSource(musicDir[musicIndex + 1]);
                musicIndex++;
                mp.prepare();
                mp.seekTo(0);
                mp.start();
            } catch (Exception e) {
                Log.d("hint", "can't jump next music");
                e.printStackTrace();
            }
        }
        else if(musicIndex==3)
        {
            Toast.makeText(MusicService.this,"没有了！",Toast.LENGTH_SHORT).show();
        }
    }

    public void preMusic() {
        if (mp != null && musicIndex > 0) {
            mp.stop();
            try {
                mp.reset();
                mp.setDataSource(musicDir[musicIndex - 1]);
                musicIndex--;
                mp.prepare();
                mp.seekTo(0);
                mp.start();
            } catch (Exception e) {
                Log.d("hint", "can't jump pre music");
                e.printStackTrace();
            }
        }
        else if(musicIndex==0)
        {
            Toast.makeText(MusicService.this,"已经是第一首了！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        mp.stop();
        mp.release();
        super.onDestroy();
    }

    /**
     * onBind 是 Service 的虚方法，因此我们不得不实现它。
     * 返回 null，表示客服端不能建立到此服务的连接。
     */
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
