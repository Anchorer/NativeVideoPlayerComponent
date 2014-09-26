package org.anchorer.videoplayer;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * VideoView的控制操作栏控件集合，可以根据自定义的UI布局对该对象进行赋值。
 * 该控件集合继承了如下内容：
 * {@link #pauseButton} 开始/暂停按钮
 * {@link #startResId}  开始ICON资源ID
 * {@link #pauseButton} 暂停ICON资源ID
 * {@link #stopButton}  停止播放按钮
 * {@link #totalTimeView}   视频总时间
 * {@link #currentTimeView} 当前播放时间
 * {@link #seekbar}     进度条
 * {@link #titleView}   视频标题
 * {@link #fullScreenButton}    全屏按钮
 * {@link #fullscreenResId}     全屏ICON资源ID
 * {@link #unfullscreenResId}   取消全屏ICON资源ID
 * {@link #nextButton}  下一个按钮
 * {@link #preButton}   上一个按钮
 *
 * Created by Anchorer on 2014/8/12.
 */
public class BaseMediaControllerHolder {
    public View parentLayout;          //父控件
    public ImageButton pauseButton;     //开始/暂停按钮
    public ImageButton stopButton;      //停止按钮
    public TextView totalTimeView;      //视频总长度
    public TextView currentTimeView;    //当前播放时间
    public SeekBar seekbar;             //进度条
    public TextView titleView;          //视频标题
    public ImageButton fullScreenButton;    //全屏按钮
    public ImageButton nextButton;      //下一个按钮
    public ImageButton preButton;       //上一个按钮
    public ImageButton forwardButton;   //快进按钮
    public ImageButton backwardButton;  //后退按钮
    public ImageButton likeButton ; // 收藏
    public ImageView imageViewBack ; //反悔按钮
    public ImageView imageViewShare ;// 分享

    public int startResId;          //开始按钮图片资源ID
    public int pauseResId;          //暂停按钮图片资源ID
    public int fullscreenResId;     //全屏按钮图片资源ID
    public int unfullscreenResId;   //取消全屏按钮图片资源ID

    private boolean hasStopped;     //视频是否已停止，可能是播放结束，也可能是手动停止

    private List<View> views;       //支持一系列自定义的视图，该列表视图实现显示与隐藏

    public BaseMediaControllerHolder() {}

    /**
     * 设置控件的显示状态
     * @param visibility    控件是否显示
     */
    public void setVisibility(int visibility) {
        if(parentLayout != null)
            parentLayout.setVisibility(visibility);
        if(pauseButton != null)
            pauseButton.setVisibility(visibility);
        if(totalTimeView != null)
            totalTimeView.setVisibility(visibility);
        if(currentTimeView != null)
            currentTimeView.setVisibility(visibility);
        if(seekbar != null)
            seekbar.setVisibility(visibility);
        if(titleView != null)
            titleView.setVisibility(visibility);
        if(fullScreenButton != null)
            fullScreenButton.setVisibility(visibility);
        if(stopButton != null)
            stopButton.setVisibility(visibility);
        if(nextButton != null)
            nextButton.setVisibility(visibility);
        if(preButton != null)
            preButton.setVisibility(visibility);
        if(views != null) {
            for (View view : views) {
                if (view != null)
                    view.setVisibility(visibility);
            }
        }
    }

    public void setPauseButton(ImageButton pauseButton) {
        this.pauseButton = pauseButton;
    }

    public void setTotalTimeView(TextView totalTimeView) {
        this.totalTimeView = totalTimeView;
    }

    public void setCurrentTimeView(TextView currentTimeView) {
        this.currentTimeView = currentTimeView;
    }

    public void setProgress(SeekBar seekbar) {
        this.seekbar = seekbar;
    }

    public void setTitleView(TextView titleView) {
        this.titleView = titleView;
    }

    public void setFullScreenButton(ImageButton fullScreenButton) {
        this.fullScreenButton = fullScreenButton;
    }

    public void setParentLayout(View parentLayout) {
        this.parentLayout = parentLayout;
    }

    public void setStopButton(ImageButton stopButton) {
        this.stopButton = stopButton;
    }

    public void setNextButton(ImageButton nextButton) {
        this.nextButton = nextButton;
    }

    public void setPreButton(ImageButton preButton) {
        this.preButton = preButton;
    }

    public void setStartResId(int startResId) {
        this.startResId = startResId;
    }

    public void setSeekbar(SeekBar seekbar) {
        this.seekbar = seekbar;
    }

    public void setPauseResId(int pauseResId) {
        this.pauseResId = pauseResId;
    }

    public void setHasStopped(boolean hasStopped) {
        this.hasStopped = hasStopped;
    }

    public boolean isHasStopped() {
        return hasStopped;
    }

    public void addCustomView(View view) {
        if(views == null)
            views = new ArrayList<View>();
        views.add(view);
    }

    public void setFileNameText(String title) {
        if(titleView != null)
            titleView.setText(title);
    }

    public void setEndTimeText(String totalTime) {
        if (totalTimeView != null)
            totalTimeView.setText(totalTime);
    }

    public void setCurrentTimeText(String currentTime) {
        if(currentTimeView != null)
            currentTimeView.setText(currentTime);
    }

    public void pauseButtonRequestFocus() {
        if (pauseButton != null)
            pauseButton.requestFocus();
    }

    public void setProgress(int progress) {
        if (seekbar != null)
            seekbar.setProgress(progress);
    }

    public void setSecondaryProgress(int secondaryProgress) {
        if (seekbar != null)
            seekbar.setSecondaryProgress(secondaryProgress);
    }

    /**
     * 为开始/暂停按钮设置ICON显示
     * @param isPlaying 是否正在播放
     */
    public void setPauseButtonImage(boolean isPlaying) {
        if(pauseButton != null) {
            if(isPlaying)
                pauseButton.setImageResource(pauseResId);
            else
                pauseButton.setImageResource(startResId);
        }
    }

    public void setPauseButtonEnabled(boolean enabled) {
        if(pauseButton != null)
            pauseButton.setEnabled(enabled);
    }

    public void setSeekbarEnabled(boolean enabled) {
        if(seekbar != null)
            seekbar.setEnabled(enabled);
    }

    public void setOnFullScreenListener(View.OnClickListener l) {
        if(fullScreenButton != null)
            fullScreenButton.setOnClickListener(l);
    }

    public void setOnPauseListener(View.OnClickListener l) {
        if(pauseButton != null)
            pauseButton.setOnClickListener(l);
    }

    public void setOnStopListener(View.OnClickListener l) {
        if(stopButton != null)
            stopButton.setOnClickListener(l);
    }

    public void setOnPreListener(View.OnClickListener l) {
        if(preButton != null)
            preButton.setOnClickListener(l);
    }

    public void setOnNextListener(View.OnClickListener l) {
        if(nextButton != null)
            nextButton.setOnClickListener(l);
    }

}
