package myview.zz.com.zzcoursedata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity {

    private TextView coures_name,coures_describe,coures_clicks,coures_buys;
    private RatingBar coures_rate;
    private Button button_buy;
    private CourseData courseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        init();

      /*  Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("data")) {
                courseData = intent.getParcelableExtra("data");
            }
        }*/

        printData(courseData);
    }

    public void init(){
        coures_name=(TextView)findViewById(R.id.coures_name);
        coures_describe=(TextView)findViewById(R.id.coures_describe);
        coures_clicks=(TextView)findViewById(R.id.coures_clicks);
        coures_buys=(TextView)findViewById(R.id.coures_buys);
        coures_rate=(RatingBar)findViewById(R.id.coures_rate);
        button_buy=(Button)findViewById(R.id.button_buy);
    }

    public void printData(CourseData data){
        /*coures_name.setText(data.id);
        coures_describe.setText(data.describe);
        coures_clicks.setText(data.clicks);
        coures_buys.setText(data.buys);
        coures_rate.setRating(data.rate);*/
        coures_name.setText("001");
        coures_describe.setText("aaaaaaaaaaacccccccccccvvvvvvvvvvvvaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        coures_clicks.setText("100");
        coures_buys.setText("1000");
        coures_rate.setRating(4);

    }

}
