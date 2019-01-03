package com.framgia.toeic.screen.exam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.framgia.toeic.R;
import com.framgia.toeic.screen.base.BaseActivity;

public class ExamActivity extends BaseActivity {

    public static Intent getExamIntent(Context context) {
        return new Intent(context, ExamActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_exam;
    }

    @Override
    protected void initComponent() {
        getSupportActionBar().setTitle(getResources().getString(R.string.action_exam));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void initData() {

    }
}
