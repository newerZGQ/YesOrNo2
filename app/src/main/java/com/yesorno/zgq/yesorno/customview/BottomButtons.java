package com.yesorno.zgq.yesorno.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yesorno.zgq.yesorno.R;

/**
 * Created by 37902 on 2016/3/30.
 */
public class BottomButtons extends LinearLayout {
    private Context context;
    public TextView button1;
    public TextView button2;
    public TextView button3;
    public BottomButtons(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public BottomButtons(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }
    private void initView(){
        View.inflate(context, R.layout.fragment_one_items_bottom_buttons,this);
        button1 = (TextView) findViewById(R.id.bottom_buttons_1);
        button2 = (TextView) findViewById(R.id.bottom_buttons_2);
        button3 = (TextView) findViewById(R.id.bottom_buttons_3);
        button1.setBackground(context.getResources().getDrawable(R.drawable.fragment_one_items_btom_btn_bg_setting_unpressed));
        button2.setBackground(context.getResources().getDrawable(R.drawable.fragment_one_items_btom_btn_bg_setting_unpressed));
        button3.setBackground(context.getResources().getDrawable(R.drawable.fragment_one_items_btom_btn_bg_setting_unpressed));
//        button1.setImageResource(R.drawable.myview_bottom_buttons_icon_setting);
    }
}
