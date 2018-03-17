package myview.zz.com.pagejumps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //映射button1按钮
        button1=(Button)findViewById(R.id.Button1);
        //映射button2按钮
        button2=(Button)findViewById(R.id.Button2);

        //增加单击事件监听
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,SubActivity1.class);
                //第二个参数用在后面的requestCode上
                startActivityForResult(intent1,1);

            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                //设置Action响应方式
                intent2.setAction(Intent.ACTION_VIEW);
                //Data内容：scheme：//host形式
                intent2.setData(Uri.parse("intentdemo://cn.edu.neusoft"));
                startActivity(intent2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK){
                Bundle random=data.getExtras();
                Toast.makeText(MainActivity.this,random.getInt("random")+"",Toast.LENGTH_LONG).show();
            }
        }
    }
}
