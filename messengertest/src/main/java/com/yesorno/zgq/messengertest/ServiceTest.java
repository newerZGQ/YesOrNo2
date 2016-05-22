package com.yesorno.zgq.messengertest;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 37902 on 2016/5/10.
 */
public class ServiceTest extends Service {
    private class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    Log.d("service",msg.getData().getString("hello"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private Messenger messenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
