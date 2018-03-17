package myview.zz.com.pagejumps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class SubActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        Button btn=(Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //1.生成随机数
                Random rand = new Random();
                int r = rand.nextInt(100);
                //2.准备数Bundle对象传递Intent数据
                Bundle bundle = new Bundle();
                bundle.putInt("random",r);
                Intent intent3=new Intent();
                intent3.putExtras(bundle);
                //3.设置返回结果  intent3,并且其中包含传递参数
                setResult(RESULT_OK,intent3);
                finish();//关闭当前页面
            }
        });
    }
}
