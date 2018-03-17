package com.example.zz.time;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private DatePicker datePicker;
    private Calendar cal;//在开始可显示当前日期与时间的情况好用的类
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取日历的一个对象
        cal=Calendar.getInstance();
        //获取年月日时分秒的信息
        year=cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MARCH)+1;
        day=cal.get(Calendar.DAY_OF_MONTH);
        hour=cal.get(Calendar.HOUR_OF_DAY);
        minute=cal.get(Calendar.MINUTE);
        setTitle(year+"-"+month+"-"+day+"   "+hour+":"+minute);//在顶部显示
        datePicker=(DatePicker)findViewById(R.id.datePicker);
        timePicker=(TimePicker)findViewById(R.id.timePicker);

        //datePicker初始化
        /*贴合在屏幕上的显示方法*/
        datePicker.init(year, cal.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() //这个参数用来设置监听器
        {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);//在顶部显示
            }
        });
        //设置时间监听器
        /*贴合在屏幕上的显示方法*/
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay+":"+minute);
            }
        });
        /*弹出对话框显示*/
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);//在顶部显示
            }
        },year,cal.get(Calendar.MONTH),day).show();
        /*弹出对话框显示*/
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay+":"+minute);//在顶部显示
            }
        }, hour, minute,true).show();
    }
}
