package com.example.zz.webview;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String url="http://www.baidu.com/";
    private WebView webView;
    private ProgressDialog dialog;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = (ProgressBar) findViewById(R.id.progressBar);
 //      调用本地浏览器打开
//        Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
  //      startActivity(intent);
        init();
    }

    private void init() {
        webView=(WebView)findViewById(R.id.webView);
        //加载本地资源
        //在使用android studio 开发的过程中，项目目录结构中并没有assets文件夹，需要手动在window模式下，去找到app/src/main/然后在main下边手动新建assets文件夹，回到android studio中，会出现这个目录结构。
        //webView.loadUrl("file:///android_assets/example.html");
        webView.loadUrl("http://www.baidu.com");
        //覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使网页可以在WebView中打开
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                //返回值是true的时候在WebView中去打开，如果为False调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return  true;
            }
            //WebViewClient帮助WebView去处理一些页面控制和请求通知

        });
        //启用JavaScript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //WebView加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //辅助类，包含检测网站加载进度的变化的方法
        webView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view,int newProgress/*1-100间的整数可用作加载进度*/){
                if(newProgress==100)
                {
                    //网页加载完毕关闭ProgressDialog
                    closeDialog();
                }
                else
                {
                    //网页正在加载,打开ProgressDialog
                    openDialog(newProgress);

                }
            }
            private void closeDialog(){
                  /*      if(dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                    dialog=null;
                }*/
                progress.setProgress(100);

            }
            private void openDialog(int newProgress){
                /*  //弹出框的显示，速度上不友好
                  if(dialog==null){
                      dialog=new ProgressDialog(MainActivity.this);
                      dialog.setTitle("正在加载");
                      dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//样式
                      dialog.setProgress(newProgress);//显示进度
                      dialog.show();
                  }
                else
                  {
                      dialog.setProgress(newProgress);
                  }
                  */
                progress.setProgress(newProgress);

            }
        }

        );
    }

    //改写物理按键--返回的逻辑，使点击返回不会直接退出
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            Toast.makeText(this,webView.getUrl(),Toast.LENGTH_SHORT);
            if(webView.canGoBack()){
                webView.goBack();//返回上一页面
                return true;
            }
         else
            {
                System.exit(0);//退出程序
            }
        }
        return  super.onKeyDown(keyCode,event);
    }
}
