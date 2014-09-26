package org.anchorer.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Base Activity，原生MediaPlayer视频播放器页面基类。
 * 如果要实现自定义UI的视频播放器页面，只需要继承该类，在此基础上实现一个{@link NativeMediaController.MediaControllerGenerator}接口即可。
 *
 * Created by Anchorer on 2014/9/23.
 */
public class BaseNativeVideoPlayerActivity extends Activity
        implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener,
        NativeMediaController.MediaPlayerControl, NativeMediaController.MediaControllerGenerator {

    private SurfaceView videoSurface;
    protected MediaPlayer mPlayer;
    protected NativeMediaController mController;
    protected Intent mIntent;
    private SurfaceHolder mVideoHolder;
    private int mCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.video_native_activity);
        mIntent = getIntent();

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt("videoPosition", 0);
        }

        videoSurface = (SurfaceView) findViewById(R.id.video_surface);
        mVideoHolder = videoSurface.getHolder();
        mVideoHolder.addCallback(this);

        mController = new NativeMediaController(this);
        mController.setUIGenerator(this);
    }


    @Override
    public BaseMediaControllerHolder generateMediaController() {
        return new BaseMediaControllerHolder();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mController.show();
        return false;
    }

    // Implement SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // 创建一个MediaPlayer对象
            mPlayer = new MediaPlayer();
            // 设置播放的视频数据源
            mPlayer.setDataSource(this, Uri.parse(mIntent.getStringExtra("path")));
            // 设置AudioStreamType
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // 将视频输出到SurfaceView
            mPlayer.setDisplay(mVideoHolder);
            // 播放准备，使用异步方式，配合OnPreparedListener
            mPlayer.prepareAsync();
            // 设置相关的监听器
            mPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}

    // Implement VideoMediaCController.MediaPlayerControl
    @Override
    public void start() {
        if (mPlayer != null)
            mPlayer.start();
    }

    @Override
    public void pause() {
        if(mPlayer != null)
        mPlayer.pause();
    }

    @Override
    public int getDuration() {
        if (mPlayer != null) {
            return mPlayer.getDuration();
        } else
            return 0;
    }

    @Override
    public int getCurrentPosition() {
        if (mPlayer != null) {
            return mPlayer.getCurrentPosition();
        } else
            return 0;
    }

    @Override
    public void seekTo(int pos) {
        if (mPlayer != null) {
            mPlayer.seekTo(pos);
        }
    }

    @Override
    public boolean isPlaying() {
        return mPlayer != null && mPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public boolean isFullScreen() {
        return false;
    }

    @Override
    public void toggleFullScreen() {
    }
    // End Implement VideoMediaCController.MediaPlayerControl

    // Implement MediaPlayer.OnPreparedListener
    @Override
    public void onPrepared(MediaPlayer mp) {
        mController.setMediaPlayer(this);
        mController.setAnchorView((FrameLayout) findViewById(R.id.video_surface_container));
        mController.show();
        mPlayer.start();
        this.seekTo(mCurrentPosition);
        mController.updatePausePlay();
    }
    // End MediaPlayer.OnPreparedListener

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentPosition = this.getCurrentPosition();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("videoPosition", mCurrentPosition);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
