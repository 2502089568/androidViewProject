package com.example.zz.http10;

import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zz on 2017/5/16.
 */

public class HttpThread extends Thread{
   private String url;
    private WebView webView;
    private Handler handler;

    public HttpThread(String url,WebView webView,Handler handlerH){
        this.url=url;
        this.webView=webView;
        this.handler=handler;
    }

    @Override
    public void run() {
        try {
            URL httpURL=new URL(url);
            try {
                HttpURLConnection conn = (HttpURLConnection)httpURL.openConnection();
                conn.setReadTimeout(5000);//请求超时等待时间
                conn.setRequestMethod("GET");//访问方式
                final StringBuffer sb=new StringBuffer();//得到返回信息
                BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));//拿到读入流开始转化读入
                String str;
                while ((str= reader.readLine())!=null){//填充
                    sb.append(str);
                }
              handler.post(new Runnable() {
                  @Override
                  public void run() {
                      webView.loadData(sb.toString(),"text/html;charset=utf-8"/*content*/,null);
                  }
              }) ;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
