package com.example.zz.zzproject1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA =0;
    int scoreTeamB =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //在创建选项菜单
        // Inflate the menu; this adds items to the action bar if it is present.
        //膨胀的菜单;如果它存在的话，它将添加到动作栏。
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //在选择项选择
        // Handle action bar item clicks here. The action bar will
        //处理动作栏项目点击这里。操作栏将
        // automatically handle clicks on the Home/Up button, so long
        //自动处理Home / Up按钮上的点击，时间太长
        // as you specify a parent activity in AndroidManifest.xml.
        //在androidmanifest . xml中指定父活动。
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Increase the score for Team A by 1 point.
     */
    public void addOneForTeamA(View v) {
        scoreTeamA =scoreTeamA+1;
        displayForTeamA(scoreTeamA);
    }
    public void addOneForTeamB(View v) {
        scoreTeamB =scoreTeamB+1;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score for Team A by 2 points.
     */
    public void addTwoForTeamA(View v) {
        scoreTeamA =scoreTeamA+2;
        displayForTeamA(scoreTeamA);
    }
    public void addTwoForTeamB(View v) {
        scoreTeamB =scoreTeamB+2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void addThreeForTeamA(View v) {
        scoreTeamA =scoreTeamA+3;
        displayForTeamA(scoreTeamA);
    }
    public void addThreeForTeamB(View v) {
        scoreTeamB =scoreTeamB+3;
        displayForTeamB(scoreTeamB);
    }
    public void restScore(View v){
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}
