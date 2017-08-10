package com.comslin.handwrite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by linChao on 2017-03-08.
 */

public class HandWrite extends View {
    public HandWrite(Context context) {
        super(context);
        init();

    }

    public HandWrite(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public HandWrite(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public HandWrite(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5f);
    }
    private Paint paint = new Paint();
    private Path path = new Path();
    private Canvas drawingCanvas;

    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
        drawingCanvas = canvas;
    }

    public Canvas getDrawingCanvas() {
        return drawingCanvas;
    }

    UpListener upListener;

    public void setUpListener(UpListener upListener) {
        this.upListener = upListener;
    }

    String TAG = "TAG";
    private final RectF dirtyRect = new RectF();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float eventX = event.getX();
        final float eventY = event.getY();
        Log.d(TAG, "onTouchEvent: " + eventX + "Y:" + eventY);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                int historySize = event.getHistorySize();
                for (int i = 0; i < historySize; i++) {
                    float historicalX = event.getHistoricalX(i);
                    float historicalY = event.getHistoricalY(i);
                    path.lineTo(historicalX, historicalY);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (upListener != null)
                    upListener.onDrawUp(getDrawingCache(),new Path(path));
                path.reset();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postInvalidate((int) eventX - 1, (int) eventY - 1, (int) eventX + 1, (int) eventY + 1);
                    }
                }).start();
                break;
        }
        return true;

    }
}

