package com.comslin.handwrite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linChao on 2017-03-09.
 */

public class BgView extends View {
    public BgView(Context context) {
        super(context);
        init();
    }

    public BgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);        init();

    }

    public BgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);        init();

    }

    public BgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);        init();

    }

    Path p;
    private void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5f);
    }

    public void setPath(Path path) {
        if (p == null) {
            p = path;
        } else {
            p.addPath(path);
        }
        invalidate();
    }

    Paint paint =new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (p != null)
            canvas.drawPath(p, paint);
    }
}
