package com.example.wilberchen.statusbartest;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class ActivityWrapper extends AppCompatActivity {

    //自定义toolbar
    @BindView(R.id.goback)
    ImageView goback;//toolBar中自定义的容器
    @BindView(R.id.title)
    TextView tv_title;
    @BindView(R.id.subTitle)
    TextView tv_subTitle;
    @BindView(R.id.option_text)
    TextView tv_option_text;
    @BindView(R.id.option_image)
    ImageView iv_option_image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initToolBar();
    }

    private void initToolBar() {
        if (useBaseActivityToolBar()) {
            ButterKnife.bind(this);
            goback.setImageDrawable(getResources().getDrawable(R.drawable.ic_send_location_online));
            goback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }


    protected void setToolBarStyle(boolean userOwnStyle, String title, String subTitle, String optionText, Drawable drawable) {
        if (useBaseActivityToolBar()) {
            if (userOwnStyle) {
                if (!TextUtils.isEmpty(title)) {
                    tv_title.setText(title);
                }
                if (!TextUtils.isEmpty(subTitle)) {
                    tv_subTitle.setText(subTitle);
                }
                if (!TextUtils.isEmpty(optionText)) {
                    tv_option_text.setText(optionText);
                }
                if (drawable != null) {
                    iv_option_image.setImageDrawable(drawable);
                }
            } else {
                //不同自己自定义的样式就用toolbar自带的
            }
        }

    }


    public Activity getSelfContext() {
        return this;
    }

    protected abstract boolean useBaseActivityToolBar();

    protected abstract int getLayoutId();

}
