package myview.zz.com.rain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zz on 2017/9/16.
 */

public class MyView extends View{
    private Bitmap bitmap;

    public MyView(Context context, AttributeSet attrs) {
        super(context,attrs);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
    }

    public MyView(Context context){
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
    }
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setTextSize(30);
        paint.setColor(0xffff0000);//arde(透明度，红绿蓝)
        paint.setStyle(Paint.Style.STROKE);//空心
        canvas.drawText("this is a",0,30, paint);
        //绘制直线起始xy，终止xy
        canvas.drawLine(0,60,100,60,paint);
        //绘制矩形左上右下
        canvas.drawRect(0,90,100,190,paint);
        //圆角矩形。。rx，ry
        canvas.drawRoundRect(0,90,100,190,10,10,paint);
        //绘制圆形圆心坐标cxcy，半径
        canvas.drawCircle(50,270,50,paint);
        canvas.drawBitmap(bitmap,0,350,paint);

    }
}
