package com.example.juserzhang.gridview.Video;


import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.juserzhang.gridview.R;

import java.text.SimpleDateFormat;


public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private TextView mTextView01;
    private static final String TAG = "HIPPO_MediaPlayer"; //打印日志的标志
    private String VideoDir = new String (Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Video/装糊涂.mp4");

    private MediaPlayer mMediaPlayer01;

    private SurfaceView mSurfaceView01;

    private SurfaceHolder mSurfaceHolder01;

    private TextView VideoTime;
    private SeekBar seekBar;
    private Button mQuit;
    private ImageButton mPlayorPause;
    private ImageButton mReset;
    private ImageButton mStop;

    private SimpleDateFormat time = new SimpleDateFormat("m:ss");

    public Handler handler = new Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(mMediaPlayer01 !=null){
                if(mMediaPlayer01.isPlaying()) {
                    mPlayorPause.setImageResource(R.drawable.pause_64);
                } else {
                    mPlayorPause.setImageResource(R.drawable.play_64);
                }
                VideoTime.setText(time.format(mMediaPlayer01.getCurrentPosition()) + "/"
                        + time.format(mMediaPlayer01.getDuration()));
                seekBar.setProgress(mMediaPlayer01.getCurrentPosition());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mMediaPlayer01.seekTo(seekBar.getProgress());
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                handler.postDelayed(runnable, 100);
            }

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_main);


        mTextView01 = (TextView)findViewById(R.id.myTextView1);
        mQuit=(Button)findViewById(R.id.BtnQuit2);
        getWindow().setFormat(PixelFormat.UNKNOWN);


        mSurfaceView01 = (SurfaceView) findViewById(R.id.mSurfaceView1); //显示动画用的容器


        mSurfaceHolder01 = mSurfaceView01.getHolder();
        mSurfaceHolder01.addCallback(this);
        mSurfaceHolder01.setFixedSize(176,144);
      //  mSurfaceHolder01.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        mPlayorPause = (ImageButton) findViewById(R.id.playorpause);
        mReset = (ImageButton) findViewById(R.id.reset);
        mStop = (ImageButton) findViewById(R.id.stop);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        VideoTime=(TextView) findViewById(R.id.VideoTime);


        mPlayorPause.setOnClickListener(new ImageButton.OnClickListener()
        {
            public void onClick(View view)
            {

                if(mMediaPlayer01 != null){
                    if(mMediaPlayer01.isPlaying()){
                        mMediaPlayer01.pause();
                        mTextView01.setText("pause");
                        mPlayorPause.setImageResource(R.drawable.play_64);
                    } else{
                        mMediaPlayer01.start();
                        mTextView01.setText("play");
                        mPlayorPause.setImageResource(R.drawable.pause_64);
                    }
                }  else{
                    playVideo(VideoDir);
                    mPlayorPause.setImageResource(R.drawable.pause_64);

                }

                seekBar.setProgress(mMediaPlayer01.getCurrentPosition());
                seekBar.setMax(mMediaPlayer01.getDuration());
                handler.post(runnable);
            }
        });


        mReset.setOnClickListener(new ImageButton.OnClickListener()
        {
            public void onClick(View view)
            {
                if(mMediaPlayer01 != null)
                {
                    mMediaPlayer01.pause();
                    mMediaPlayer01.seekTo(0);
                    seekBar.setProgress(0);
                    mMediaPlayer01.start();
                }
            }
        });


        mStop.setOnClickListener(new ImageButton.OnClickListener()
        {
            public void onClick(View view)
            {
                if (mMediaPlayer01 != null)
                {
                    mMediaPlayer01.stop();
                    mTextView01.setText("stop");
                    mPlayorPause.setImageResource(R.drawable.play_64);
                    try {
                        mMediaPlayer01.prepare();
                        mMediaPlayer01.seekTo(0);
                        seekBar.setProgress(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                try {
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private void playVideo(String strPath)
    {
        mMediaPlayer01 = new MediaPlayer();
        mMediaPlayer01.setAudioStreamType(AudioManager.STREAM_MUSIC);


        mMediaPlayer01.setDisplay(mSurfaceHolder01);

        try
        {
            mMediaPlayer01.setDataSource(strPath);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            mTextView01.setText("setDataSource Exceeption:"+e.toString());
        }

        try
        {
            mMediaPlayer01.prepare();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            mTextView01.setText("prepare Exceeption:"+e.toString());
        }
        mMediaPlayer01.start();
        mTextView01.setText("play");
        mMediaPlayer01.setOnCompletionListener
                (new MediaPlayer.OnCompletionListener()
                {
                    @Override
                    public void onCompletion(MediaPlayer arg0)
                    {
                        // TODO Auto-generated method stub
                        mTextView01.setText("stop");
                    }
                });
    }


    @Override
    public void surfaceChanged
            (SurfaceHolder surfaceholder, int format, int w, int h)
    {
        // TODO Auto-generated method stub
        Log.i(TAG, "Surface Changed");
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        // TODO Auto-generated method stub
        Log.i(TAG, "Surface Changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        // TODO Auto-generated method stub
        Log.i(TAG, "Surface Destroyed");
    }
}
