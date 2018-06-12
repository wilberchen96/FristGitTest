package com.example.wilberchen.statusbartest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestStatusBarUtilsActivity extends ActivityWrapper {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restToolBar();
    }


    private void restToolBar(){
        setToolBarStyle(true ,"111","222","333",null);
    }

    @Override
    protected boolean useBaseActivityToolBar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.testactivity;
    }
}
