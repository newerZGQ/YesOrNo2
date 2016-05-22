package com.yesorno.zgq.progressbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0){
                    Log.d("--->>",""+msg.arg1);
                    progressBar.setProgress(msg.arg1);
                }
                if (msg.what == 1){
                    progressBar.setVisibility(View.GONE);
                }
            }
        };
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        progressBar.setVisibility(View.VISIBLE);

        new ProgressBarTest().start();

    }
    public class ProgressBarTest extends Thread{

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            long endTime = startTime;
            long x = endTime - startTime;
            int progress = 0;
            while(x <= 10000){
                progress = (int)(x/100);
                progressBar.setProgress(progress);
//                progressBar.postInvalidate();
//                Message message = Message.obtain();
//                message.what = 0;
//                message.arg1 = progress;
//                handler.sendMessage(message);
                endTime = System.currentTimeMillis();
                x = endTime - startTime;
                try {
                    sleep(100, 0);
                }catch (Exception e){

                }
            }
            Message message = Message.obtain();
            message.what = 1;
            handler.sendMessage(message);
        }
    }
}
