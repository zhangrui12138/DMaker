package com.example.topwise.dmaker.TextMaker;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by topwise on 17-9-23.
 */

public class MakerText extends EditText {

    private int lastX;
    private int lastY;
    private Context mContext;
    private static  boolean ismoved=true;
    private Paint mpaint;//画圆点的画笔
    private Paint mRotatePaint;//画圆点的画笔
    private InputMethodManager imm;//软键盘
    private boolean showPoint;//显示红点
    public MakerText(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public MakerText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    //视图初始化
    public void init(){
        mpaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mRotatePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mpaint.setColor(Color.RED);
        mRotatePaint.setColor(Color.BLUE);
        setTextSize(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(showPoint && isFocused()){
            int viewWidth = getWidth();
            int viewHeight = getHeight();
            canvas.drawCircle(viewWidth - 16, 16, 16, mpaint);
            canvas.drawCircle(viewWidth - 16, viewHeight-16, 16, mRotatePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取当前触摸的坐标
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 上一次离开时的坐标
                ismoved=false;
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                // 两次的偏移量
                ismoved=true;
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                moveView(offsetX, offsetY);
                // 不断修改上次移动完成后坐标
                lastX = rawX;
                lastY = rawY;
                Log.d("zhangrui",event.getY()+"///////"+getHeight()+"////"+(int) Math.abs(event.getY()-getHeight()));
                if((int) Math.abs(event.getX()-getWidth())<getWidth()/4 && (int) Math.abs(event.getY()-getHeight())<getHeight()/4){
                    Toast.makeText(mContext,"rotate is running!", Toast.LENGTH_SHORT).show();
                    rotateMySelf();
                    lostFoucus();
                }
                break;

            case MotionEvent.ACTION_UP:
                if(ismoved){
                    lostFoucus();
                }else{
                    getFoucus();
                    if((int) Math.abs(event.getX()-getWidth())<getWidth()/4 && event.getY() <getHeight()/4){removeView();lostFoucus();}
                }
                break;

            default:
                break;
        }
        return true;
    }

    private void moveView(int offsetX, int offsetY) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)getLayoutParams();
        layoutParams.leftMargin = getLeft() + offsetX;
        layoutParams.topMargin = getTop() + offsetY;
        setLayoutParams(layoutParams);
    }

    //获取焦点处理方法
    public void getFoucus(){
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        imm=getInputMethodManager();
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        showPoint=true;
        invalidate();
    }

    //失去焦点处理方法
    public void lostFoucus(){
        clearFocus();
        setFocusable(false);
        setFocusableInTouchMode(false);
        imm=getInputMethodManager();
        imm.hideSoftInputFromWindow(getWindowToken(), 0);
        showPoint=false;
        invalidate();
    }

    public InputMethodManager getInputMethodManager(){
        if (imm == null){
            imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        return imm;
    }

    public void removeView(){
      setVisibility(View.GONE);
    }

    public void rotateMySelf(){
        ObjectAnimator mAnimatorRotate = ObjectAnimator.ofFloat(this, "rotation", 0.0f,360.0f);
        mAnimatorRotate.setDuration(1000);
        mAnimatorRotate.start();
    }
}
