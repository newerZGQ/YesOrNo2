package com.yesorno.zgq.yesorno.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import com.yesorno.zgq.yesorno.R;

/**
 * Created by 37902 on 2016/3/30.
 */
public class CircleButton extends TextView {
    private Paint mPaint;
    private Context context;
    public CircleButton(Context context) {
        super(context);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,mPaint);
        super.onDraw(canvas);
    }
}
