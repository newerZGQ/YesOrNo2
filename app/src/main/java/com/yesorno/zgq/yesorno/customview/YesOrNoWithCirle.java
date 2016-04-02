package com.yesorno.zgq.yesorno.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.yesorno.zgq.yesorno.R;

/**
 * Created by 37902 on 2016/3/30.
 */
public class YesOrNoWithCirle extends View {
    private Paint paint;
    private int radius;
    private int maxRadius;

    private static int STATUSRUNNING = 1;
    private static int STATUSSTOP = 2;
    private int status;

    private static int CHANGEBIGGER = 1;
    private static int CHANGESMALLER = 2;
    private int transformStatus;

    private int stepSize;

    private float centerX;
    private float centerY;

    private Context context;
    public YesOrNoWithCirle(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public YesOrNoWithCirle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    private void init(){
        status = STATUSSTOP;
        transformStatus = CHANGESMALLER;
        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.colorWhite));
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        stepSize = getWidth()/20;
        radius = getHeight()/2;
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        maxRadius = getHeight()/2*4/5;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (status == STATUSRUNNING) {
            canvas.drawCircle(centerX, centerY, radius, paint);
            getNextStep();
        }
        if (status == STATUSSTOP){
            canvas.drawCircle(centerX, centerY, maxRadius, paint);
        }
    }
    private void getNextStep(){
        if (status == STATUSRUNNING){
            if (transformStatus == CHANGEBIGGER){
                radius += stepSize;
                if (radius>=maxRadius) {
                    radius -= stepSize;
                    transformStatus = CHANGESMALLER;
                }
            }
            if (transformStatus == CHANGESMALLER){
                radius -= stepSize;
                if (radius<=(maxRadius*4/5)) {
                    radius += stepSize;
                    transformStatus = CHANGEBIGGER;
                }
            }
            invalidate();
        }
    }

    public void start() {
        status = STATUSRUNNING;
        invalidate();
    }

    public void reset() {
        status = STATUSSTOP;
        invalidate();
    }
}
