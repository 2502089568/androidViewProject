package com.example.zz.adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//OnItemClickListener 处理视图中单个条目的点击事件
//OnScrollListener 处理视图在滚动中加载视图
//引入实现接口
public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayAdapter<String> arr_adapter;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> dataList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        //1.新建适配器
        //ArrayAdapter<String>(上下文，ListView加载的每个列表项所对应的的布局文件,数据源）
        //SimpAdapter(上下文，数据源一个Map所组成的List集合（每个Map对应ListView一行，每个Map中的键必须包含所有from中所指定的键），列表项的布局文件名，Map的键名，绑定数据视图中的ID与from成对应关系）
        //2.适配器加载数据源
        String[] arr_data = {"zz1", "zz2", "zz3", "zz4"};
        dataList = new ArrayList<Map<String, Object>>();
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
        simp_adapter = new SimpleAdapter(this, getData(), R.layout.item, new String[]{"pic", "text"}, new int[]{R.id.pic, R.id.text});
        //3.视图加载适配器
        // listView.setAdapter(arr_adapter);
        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);//加载监听器点击
        listView.setOnScrollListener(this);//滚动
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("text", "zz" + i);
            dataList.add(map);
        }
        return dataList;
    }


    /*实现监听器方法*/
    @Override
    /*下拉刷新（滚动状态）*/
    public void onScrollStateChanged(AbsListView view, int scrollState/* 不同滚动状态*/) {
        switch(scrollState)
        {
            case SCROLL_STATE_FLING:
                Log.i("Main","用户在手指离开屏幕前，由于用力划了一下，视图仍由于惯性滑动");
                Map<String,Object>map=new HashMap<String, Object>(); //key可以理解成房子  value 可以理解成 住户名，一个对应一个
                //Object是所有类的父类，它有很多类对象会用到的方法，例如比较常用的toString 、equals，当你新建xx类时，你可以重写Object已经定义的方法，也可以直接调用Object中的方法，如果你写一个封装的方法，不确定传进来的是什么类型的值，就可以使用Object作为一个笼统类
                map.put("pic",R.mipmap.ic_launcher);
                map.put("text", "增加项1");
                dataList.add(map);
                simp_adapter.notifyDataSetChanged();//通知ui线程刷新界面，动态更新视图中的数据
                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main","视图已经停止滑动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Main","手指没离开屏幕视图正在移动");
                Map<String,Object>map1=new HashMap<String, Object>(); //key可以理解成房子  value 可以理解成 住户名，一个对应一个
                //Object是所有类的父类，它有很多类对象会用到的方法，例如比较常用的toString 、equals，当你新建xx类时，你可以重写Object已经定义的方法，也可以直接调用Object中的方法，如果你写一个封装的方法，不确定传进来的是什么类型的值，就可以使用Object作为一个笼统类
                map1.put("pic",R.mipmap.ic_launcher);
                map1.put("text", "增加项2");
                dataList.add(map1);
                simp_adapter.notifyDataSetChanged();//通知ui线程刷新界面，动态更新视图中的数据
                break;
        }
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int tatalItemCount){

    }

    //点击的方法
    public void onItemClick(AdapterView<?> parent,View view ,int position/*点击的位置信息*/,long id){
       String text= listView.getItemAtPosition(position)+"";
        Toast.makeText(this,"position="+position+" text="+text,Toast.LENGTH_SHORT).show();
    }
}
