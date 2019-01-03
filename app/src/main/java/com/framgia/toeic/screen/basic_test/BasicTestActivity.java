package com.framgia.toeic.screen.basic_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.framgia.toeic.R;
import com.framgia.toeic.screen.base.BaseActivity;

public class BasicTestActivity extends BaseActivity {

    public static Intent getBasicTestIntent(Context context) {
        return new Intent(context, BasicTestActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_basic_test;
    }

    @Override
    protected void initComponent() {
        getSupportActionBar().setTitle(getResources().getString(R.string.action_basic_test));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
    }
}
