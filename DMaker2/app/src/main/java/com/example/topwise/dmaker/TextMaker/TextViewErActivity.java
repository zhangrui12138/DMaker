package com.example.topwise.dmaker.TextMaker;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;


import com.example.topwise.dmaker.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//本界面是文本界面
public class TextViewErActivity extends AppCompatActivity{

    //添加文本的按钮
    @BindView(R.id.addText)
    Button addText;
    //画图的面板
    @BindView(R.id.mianban)
    RelativeLayout mianban;


    //获取屏幕长宽
    private WindowManager windowManager;
    //屏幕宽度
    private int width;
    //屏幕高度
    private int height;

    //文本输入框的数量
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_er);
        ButterKnife.bind(this);
        init();
    }

    //此方法用于初始化View
    public void init() {
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
        height = windowManager.getDefaultDisplay().getHeight();
    }

    @OnClick(R.id.addText)
    public void onClick() {
      MakerText makerText=new MakerText(this);
        makerText.setHeight(250);
        makerText.setHeight(100);
        makerText.setX(0);
        makerText.setY(0);
        makerText.setHint("请输入待输入内容");
        mianban.addView(makerText);

    }



}
