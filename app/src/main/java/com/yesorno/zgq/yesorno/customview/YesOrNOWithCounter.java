package com.yesorno.zgq.yesorno.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.github.glomadrian.dashedcircularprogress.DashedCircularProgress;
import com.yesorno.zgq.yesorno.R;

/**
 * Created by 37902 on 2016/3/28.
 */
public class YesOrNOWithCounter extends LinearLayout {
    private Context context;
    private YesOrNoView yesOrNoView;
    private DashedCircularProgress circularProgress;
    private int resetIcon;
    private int runningIcon;
    public YesOrNOWithCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.resetIcon = R.drawable.myview_counter_top_icon_reset;
        this.runningIcon = R.drawable.myview_counter_top_icon_running;
        initView();
    }

    public YesOrNOWithCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initView(){
        View.inflate(context, R.layout.custom_view_yesorno_with_counter,this);
        yesOrNoView = (YesOrNoView)findViewById(R.id.yes_or_no);
        circularProgress = (DashedCircularProgress) findViewById(R.id.circle_progress);
        circularProgress.setIcon(resetIcon);
        circularProgress.setOnValueChangeListener(new DashedCircularProgress.OnValueChangeListener() {
            @Override
            public void onValueChange(float v) {
                if (v == circularProgress.getMax()) {
                    yesOrNoView.stop();
//                    circularProgress.setIcon(R.drawable.myview_counter_top_icon_stop);
                }
            }
        });
    }
    public void setTargetTitle(int i){
        yesOrNoView.setTargetTitle(i);
    }
    private void getTargetTitle(){

    }
    public void start(){
        yesOrNoView.start();
        circularProgress.setDuration(3000);
        circularProgress.setIcon(R.drawable.myview_counter_top_icon_running);
        circularProgress.setValue(circularProgress.getMax());
    }
    public void stop(){
        yesOrNoView.stop();
    }
    public void reset(){
//        circularProgress.reset();
        circularProgress.setIcon(R.drawable.myview_counter_top_icon_reset);
        circularProgress.setDuration(500);
        circularProgress.setValue(0);
        circularProgress.reset();
        yesOrNoView.reset();
    }

}
