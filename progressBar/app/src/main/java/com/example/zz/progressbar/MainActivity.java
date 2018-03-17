package com.example.zz.progressbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

/*关键方法：
（1）setProgress(int) 设置第一进度
（2）setSecondaryProgress(int) 设置第二进度
（3）getProgress() 获取第一进度
（4）getSecondaryProgress() 获取第二进度
（5）incrementProgress(int) 增加或减少第一进度
（6）incrementSecondaryProgress(int) 增加或减少第二进度
（7）getMax() 获取最大进度*/
public class MainActivity extends Activity {

    private ProgressBar progress;
    private TextView text;
    private ProgressDialog prodialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //启用窗口特征，启用带进度和不带进度的进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        //显示两种进度条
        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
        //Max=10000
        setProgress(9999);
        progress = (ProgressBar) findViewById(R.id.horiz);
        text = (TextView) findViewById(R.id.text);
        init();
    }

    private void init() {
        //getPrgress()  获取第一进度条的进度
        int first = progress.getProgress();
        //获取第2进度条的进度
        int second = progress.getSecondaryProgress();
        //获取进度条的max进度
        int max = progress.getMax();
        text.setText("第一进度条百分比:" + (int) ((first / (float) max) * 100) + "%" + "第二进度条百分比" + (int) ((second / (float) max) * 100) + "%");

    }

    public void add(View view) {
        //加第一和第二进度10个刻度
        progress.incrementProgressBy(10);
        progress.incrementSecondaryProgressBy(10);
        text.setText("第一进度条百分比:" + (int) ((progress.getProgress() / (float) progress.getMax()) * 100) + "%" + "第二进度条百分比" + (int) ((progress.getSecondaryProgress() / (float) progress.getMax()) * 100) + "%");
    }

    public void reduce(View view) {
        progress.incrementProgressBy(-10);
        progress.incrementSecondaryProgressBy(-10);
        text.setText("第一进度条百分比:" + (int) ((progress.getProgress() / (float) progress.getMax()) * 100) + "%" + "第二进度条百分比" + (int) ((progress.getSecondaryProgress() / (float) progress.getMax()) * 100) + "%");
    }

    public void reset(View view) {
        progress.setProgress(50);
        progress.setSecondaryProgress(80);
        text.setText("第一进度条百分比:" + (int) ((progress.getProgress() / (float) progress.getMax()) * 100) + "%" + "第二进度条百分比" + (int) ((progress.getSecondaryProgress() / (float) progress.getMax()) * 100) + "%");
    }
    public void show(View view) {
        /*页面显示风格*/
        //新建ProgressDialog对象
        prodialog =new ProgressDialog(MainActivity.this);
        //设置显示风格
        prodialog.setTitle("ZZ");
        //设置对话框的文字信息
        prodialog.setMessage("是个大帅哥");
        //设置图标
        prodialog.setIcon(R.mipmap.ic_launcher);

        /*
        * 设定关于ProgressBar进度条的一些属性
        * */
        prodialog.setMax(100);
        //设定初始已经增长到的进度
        prodialog.incrementProgressBy(50);
        //进度条是明确显示进度的
        prodialog.setIndeterminate(false);
        /**
         * 设定一个确定按钮
         * */
    /*(    prodialog.setButton(DialogInterface.BUTTON_NEGATIVE,"确定", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog,int which) {
                Toast.makeText(MainActivity.this,"123456",Toast.LENGTH_SHORT).show();
            }
        });*/
        //是否可以通过返回按钮退出对话框
        prodialog.setCancelable(true);

        //显示ProgressDialog
        prodialog.show();
    }
}


