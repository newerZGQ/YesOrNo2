package com.yesorno.zgq.yesorno.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 37902 on 2016/3/30.
 */
public class GradientCircle extends View {
    private Paint paint;
    private int alpha;
    public GradientCircle(Context context) {
        super(context);
        init();
    }

    public GradientCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        alpha = 255;
        paint = new Paint();
        paint.setAlpha(alpha);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,paint);

    }
}
