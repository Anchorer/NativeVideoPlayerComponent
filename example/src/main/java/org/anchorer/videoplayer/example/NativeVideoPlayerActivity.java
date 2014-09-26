package org.anchorer.videoplayer.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.anchorer.videoplayer.BaseMediaControllerHolder;
import org.anchorer.videoplayer.BaseNativeVideoPlayerActivity;
import org.anchorer.videoplayer.NativeMediaController;

/**
 * 自定义UI视频播放器页面Demo。
 *
 * Created by Anchorer on 2014/9/23.
 */
public class NativeVideoPlayerActivity extends BaseNativeVideoPlayerActivity implements NativeMediaController.MediaControllerGenerator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 如果有通过Intent传入的其他数据，可以在该Activity的onCreate()方法中直接使用父类的Intent对象
        // ...
        // int videoId = mIntent.getIntExtra("videoId", 0);
        // ...

        // 如果有需要做一些监听的，可以使用父类中的MediaPlayer对象设置监听器
        // ...
        /*
        // 设置缓冲状态的监听器
        mPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        */
        // ...
    }

    @Override
    public BaseMediaControllerHolder generateMediaController() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.video_native_media_controler_custom, null);

        BaseMediaControllerHolder holder = new BaseMediaControllerHolder();
        holder.parentLayout = view;
        holder.pauseButton = (ImageButton) view.findViewById(R.id.video_native_media_controller_custom_btn_start);
        holder.currentTimeView = (TextView) view.findViewById(R.id.video_native_media_controller_custom_currenttime);
        holder.totalTimeView = (TextView) view.findViewById(R.id.video_native_media_controller_custom_totaltime);
        holder.seekbar = (SeekBar) view.findViewById(R.id.video_native_media_controller_custom_seekbar);
        holder.fullScreenButton = (ImageButton) view.findViewById(R.id.video_native_media_controller_custom_btn_unfullscreen);
        holder.pauseResId = R.drawable.selector_video_btn_pause;
        holder.startResId = R.drawable.selector_video_btn_start;
        holder.fullscreenResId = R.drawable.selector_video_btn_fullscreen;
        holder.unfullscreenResId = R.drawable.selector_video_btn_unfullscreen;

        return holder;
    }
}
