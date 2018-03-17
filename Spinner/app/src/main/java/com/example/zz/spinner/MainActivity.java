package com.example.zz.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Spinner spinner;
    //private List<String>list;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.texView);
        spinner = (Spinner)findViewById(R.id.spinner);
        textView.setText("您选择的城市是北京");
        //1.设置数据源
       /* list = new ArrayList<String>();
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");*/
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        //2.新建 ArrayAdapter(数组适配器)(上下文，布局样式，数据源)
       // adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter=new SimpleAdapter(this, dataList, R.layout.item, new String[]{"image","text"}, new int[]{R.id.image,R.id.text});
        //3.adapter设置一个下拉列表样式
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.item);
        //4.spinner加载适配器
        spinner.setAdapter(adapter);
        //5.spinner设置监听器
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?>parent, View view, int position, long id){
                //String cityName = adapter.getItem(position);
                textView.setText("您选择的城市是"+adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("NONE");
            }
        });
    }
    private void getData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", R.mipmap.ic_launcher);
        map.put("text", "北京");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("image", R.mipmap.ic_launcher);
        map2.put("text", "上海");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("image", R.mipmap.ic_launcher);
        map3.put("text", "广州");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("image", R.mipmap.ic_launcher);
        map4.put("text","深圳");
        dataList.add(map);
        dataList.add(map2);
        dataList.add(map3);
        dataList.add(map4);
    }
}
