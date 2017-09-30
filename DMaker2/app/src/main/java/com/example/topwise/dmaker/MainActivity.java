package com.example.topwise.dmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.topwise.dmaker.TextMaker.TextViewErActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //进入到TextMaker的界面的按钮
    @BindView(R.id.btn_text)
    Button mBtn_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }



    //进入到TextMaker的界面的按钮的响应事件
    @OnClick({R.id.btn_text/*, R.id.btn_picture*/})
    public void ButtonToText(View view) {
        Log.d("zr","sgdhsgd");
        switch (view.getId()){
            case R.id.btn_text:
                startActivity(new Intent(MainActivity.this, TextViewErActivity.class));
                break;
        }
    }
}
