package myview.zz.com.threadex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        labelView = (TextView)findViewById(R.id.label);
        startButton = (Button)findViewById(R.id.start);
        Button stopButton = (Button)findViewById(R.id.stop);
        final Intent serviceIntent = new Intent(this, RandomService.class);
        startButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick (View view){
                startService(serviceIntent);
            }
        });


        stopButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick (View view){
                stopService(serviceIntent);
            }
        });
    }
    private static Handler handler = new Handler();
    private static TextView labelView =null;
    private static int randomDouble;
    private static int randomDouble1;
    private static int randomDouble2;

    public static void UpdataGUI (int refreshDouble,int regreshDouble1,int regreshDouble2){
        randomDouble = refreshDouble;
        randomDouble1 = regreshDouble1;
        randomDouble2 = regreshDouble2;
        handler.post(RefreshLayout);
    }

    private static Runnable RefreshLayout = new Runnable() {
        @Override
        public void run() {
            labelView.setText(String.valueOf("201526204"+randomDouble+randomDouble1+randomDouble2));
        }
    };

}
