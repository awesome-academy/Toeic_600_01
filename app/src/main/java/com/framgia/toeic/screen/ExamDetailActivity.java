package com.framgia.toeic.screen;

import android.content.Context;
import android.content.Intent;

import com.framgia.toeic.R;
import com.framgia.toeic.screen.base.BaseActivity;

public class ExamDetailActivity extends BaseActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, ExamDetailActivity.class);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_exam_detail;
    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected void initData() {

    }
}
