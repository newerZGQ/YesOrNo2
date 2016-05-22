package com.yesorno.zgq.smoothscroll;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button tv = (Button) findViewById(R.id.test);
        final int startx = 0;
        final int deltax = -300;
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ValueAnimator animator = ValueAnimator.ofInt(0,1).setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float v = animator.getAnimatedFraction();
                        tv.scrollTo(startx + (int)(deltax*v),0);
                    }
                });
                animator.start();
            }
        });

    }
}
