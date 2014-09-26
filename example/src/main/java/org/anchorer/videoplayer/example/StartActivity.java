package org.anchorer.videoplayer.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 入口。
 *
 * Created by Anchorer on 2014/9/26.
 */
public class StartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, NativeVideoPlayerActivity.class);
        intent.putExtra("path", "http://dance-video.b0.upaiyun.com/video/3c/0a/0ee083a6263a4342bc9c903f2510_n.mp4");
        startActivity(intent);
    }
}
