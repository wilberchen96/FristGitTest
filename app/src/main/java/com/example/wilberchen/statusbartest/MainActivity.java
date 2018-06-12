package com.example.wilberchen.statusbartest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;

public class MainActivity extends ActivityWrapper implements View.OnClickListener {
    @BindView(R.id.b4)
    TextView b4;

    int i= 55;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.b1).setOnClickListener(this);
        findViewById(R.id.b2).setOnClickListener(this);
        findViewById(R.id.b3).setOnClickListener(this);
        findViewById(R.id.b4).setOnClickListener(this);
       // StatusBarUtil.setTransparent(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.parseColor("#00285991"));
        }
    }

    @Override
    protected boolean useBaseActivityToolBar() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                //4.4--5.0的时候

                //FLAG_TRANSLUCENT_STATUS  等同于设置了两个属性 :SYSTEM_UI_FLAG_LAYOUT_STABLE和SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                //SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN 就是设置全屏，把内容顶上去(正常的内容都是在statusBar下面开始的)
                //SYSTEM_UI_FLAG_LAYOUT_STABLE 不知道干什么的 防止内容抖动
                //还有一点需要注意的是，FLAG_TRANSLUCENT_STATUS设置完成之后就会把状态栏变成透明
                //这个时候4.4-5.0的版本就加一个跟statusBar高度大小的view 在跟布局，实现沉浸式
              //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                //如果想要用图片置顶 实现沉浸式，那么就把手动加上去的view去掉，然后直接用图片

                Intent intent = new Intent(getSelfContext(), TestStatusBarUtilsActivity.class);
                startActivity(intent);
                break;
            case R.id.b2:
                //如果不需要图片沉浸式(图片置顶) 那么就清理FLAG_TRANSLUCENT_STATUS，不让内容全屏。再通过setStatusBarColor直接设置状态栏颜色
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    //设置状态栏颜色的时候 需要先设置FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS 这个属性，这个很重要
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                  //设置状态栏颜色
                    getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_red_dark));
                }
                break;

            case R.id.b3:
                //如果需要图片沉浸式(图片置顶) 一般做法就是先设置全屏 SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN 。
                // 然后再修改的view(titlebar,actionbar，toolbar)的属性，比如说修改pangding。
                //也可以再指定的view(titlebar,actionbar，toolbar)上加一个自己构建的假状态栏，然后对假状态栏设置
                //一般情况下，进来的时候就设置全屏 并且自己把状态栏设置为透明 对titlebar或者假状态栏的背景进行操作，这样状态栏就达到了颜色控制目的
                //所以activity的布局可以分为2层，第一次是顶部图片和下面内容，第二层就是titlebar或者toolbar
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                    getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
                }
                break;

            case R.id.b4:
                i+=10;
                 StatusBarUtil.setColor(this,Color.parseColor("#FFcc0000"),i);
               // StatusBarUtil.setColor(getSelfContext(), Color.parseColor("#121212"),i);
                break;

        }
    }


}
