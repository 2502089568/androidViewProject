package com.example.zz.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//OnItemClickListener 处理视图中单个条目的点击事件
public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private int[] icon = {R.drawable.address_book, R.drawable.calendar, R.drawable.camera, R.drawable.clock, R.drawable.games_control,
            R.drawable.ic_launcher, R.drawable.messenger, R.drawable.ringtone, R.drawable.settings, R.drawable.speech_balloon,
            R.drawable.weather, R.drawable.world, R.drawable.youtube};
    private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声", "设置", "语音", "天气", "浏览器", "视频"};
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        //1.准备数据源
        //2.新建适配器（SimpleAdapter）
        //3.GridView加载适配器
        //4.GridView配置事件监听器（OnItemClickListener）
        dataList = new ArrayList<Map<String, Object>>();
        //getData();
        adapter = new SimpleAdapter(this, getData(), R.layout.item, new String[]{"image", "text"}, new int[]{R.id.image, R.id.text});
        gridView.setAdapter(adapter);//GridView加载适配器
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "我是" + iconName[position], Toast.LENGTH_SHORT).show();
            }
        });//加载监听器

    }
    private List<Map<String, Object>> getData() {

        for (int i = 0; i < 12; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }
}
