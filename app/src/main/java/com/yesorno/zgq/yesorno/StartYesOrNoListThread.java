package com.yesorno.zgq.yesorno;

import android.os.Handler;

import com.yesorno.zgq.yesorno.customview.YesOrNoView;

import java.util.List;

public class StartYesOrNoListThread extends Thread{
    private List<YesOrNoView> list;
    private int dalayTime;
    private Handler handler;
    public StartYesOrNoListThread(List<YesOrNoView> list, Handler handler,int dalayTime){
        this.list = list;
        this.dalayTime = dalayTime;
        this.handler = handler;
    }
    @Override
    public void run() {
        for (int i = 0;i<list.size();i++){
            handler.sendEmptyMessage(i);
            try {
                sleep(dalayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
