package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.27 17:16
 * @Description：YangXinYu
 */
public class MyView extends View{

    private float x ;
    private float y ;
    Paint paint = new Paint();

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取x y坐标
        x = getMeasuredWidth()/2;
        y = getMeasuredHeight()/2;
        Log.e("tag",x+"");
        Log.e("tag",y+"");
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置颜色为红色
        paint.setColor(Color.RED);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawLine(x,0,x,getHeight()*2,paint);//y轴
        canvas.drawLine(0,y,getWidth()*2,y,paint);//x轴
        canvas.drawCircle(x,y,100,paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取当前点击的下x，y
        x = event.getX();
        y = event.getY();
        invalidate();//更新UI 重新调用onDraw方法
        return true;//这里面必须返回true,否则小球移动不了。
    }


}
