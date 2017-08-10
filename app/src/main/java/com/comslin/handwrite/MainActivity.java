package com.comslin.handwrite;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    HandWrite handWrite;
    BgView bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handWrite = (HandWrite) findViewById(R.id.hw);
        bg = (BgView) findViewById(R.id.bg);
        handWrite.setUpListener(new UpListener() {
            @Override
            public void onDrawUp(Bitmap bitmap,Path path) {
                bg.setPath(path);
            }
        });
    }
}
