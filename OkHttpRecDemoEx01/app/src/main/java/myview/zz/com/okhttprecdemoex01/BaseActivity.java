package myview.zz.com.okhttprecdemoex01;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by zz on 2017/12/18.
 */

class ScreenUtil{
    public static int getStatusBarHeight(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        int x, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}



public abstract class BaseActivity extends AppCompatActivity {



    private ViewGroup mContentView;
    private View mStatusView, mLayoutView;
    private boolean mIsLight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindowParameter();
        setContentView(R.layout.activity_base);
        initContentView();
    }

    private void initContentView() {
        mContentView = findViewByID(R.id.content_view);
        mStatusView = findViewByID(R.id.status_bar);
        if (mLayoutView == null) {
            mLayoutView = getLayoutInflater().inflate(getLayoutId(), null);
        }
        mContentView.removeAllViews();
        mContentView.addView(mLayoutView);
        setStatusBarColor(R.color.colorPrimary);
    }

    public abstract int getLayoutId();

    public <T extends View> T findViewByID(int id) {
        return (T) super.findViewById(id);
    }

    /**
     * 隐藏Toolbar
     */
    public void hideStatusBar() {
        mStatusView.setVisibility(View.GONE);
    }

    /**
     * 设置是否为亮色主题
     *
     * @param isLight
     */
    public void setLightStatusBar(boolean isLight) {
        this.mIsLight = isLight;
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     */
    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.MATCH_PARENT, ScreenUtil.getStatusBarHeight(this));
            mStatusView.setBackgroundResource(color);
            mStatusView.setLayoutParams(lParams);
            mStatusView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置状态栏等与Window相关的参数
     */
    protected void initWindowParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0+
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (mIsLight) { //如果StatusBar为亮色主题的话，则文字颜色为深色
                option = option | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.argb(25,255,255,255));//设置背景颜色
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4~5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }
    }



}
