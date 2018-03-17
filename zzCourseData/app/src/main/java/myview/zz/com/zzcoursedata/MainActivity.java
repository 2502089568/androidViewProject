package myview.zz.com.zzcoursedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private CourseData courseData;
    private String info="{\"id\":\"001\",\"name\":\"钢琴\",\"describe\":\"这是一门基础的钢琴课\",\"owned\":\"false\",\"clicks\":\"100\",\"buys\":\"1000\",\"rate\":\"4\",\"videos\":\"[\"第一课\",\"第二课\",\"第三课\"]\"}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
      //  Gson gson = new Gson();
        //courseData =gson.fromJson(info,CourseData.class);
    }



    public void jump(View v){
        Intent intent = new Intent(MainActivity.this,CourseActivity.class);
        startActivity(intent);
       /* intent.putExtra("data",courseData);
        intent.setClass(MainActivity.this,CourseActivity.class);
        startActivity(intent);*/
    }

}


