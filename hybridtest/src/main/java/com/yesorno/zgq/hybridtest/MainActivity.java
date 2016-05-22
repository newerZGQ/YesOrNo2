package com.yesorno.zgq.hybridtest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    final Handler myHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/www/index.html");
        webView.loadUrl("http://baidu.com");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        JavascriptInterface myJavaScriptInterface = new JavascriptInterface(this);
        webView.addJavascriptInterface(myJavaScriptInterface,"demo");
    }

    public class JavascriptInterface{
        Context context;
        public JavascriptInterface(Context mContext){
            context = mContext;
        }
        public void clickOnAndroid(){
            myHandler.post(new Runnable(){
                public void run(){
                    webView.loadUrl("javascript:wave()");
                }
            });
        }
    }
}
