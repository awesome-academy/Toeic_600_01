package com.framgia.toeic.screen.user;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Mark;
import com.framgia.toeic.data.repository.MarkRepository;
import com.framgia.toeic.data.source.local.DBHelper;
import com.framgia.toeic.data.source.local.MarkDatabaseHelper;
import com.framgia.toeic.data.source.local.MarkLocalDataSource;
import com.framgia.toeic.screen.base.BaseActivity;

import java.util.List;

public class UserActivity extends BaseActivity implements UserContract.View {
    private static final String PREFNAME = "data_user";
    private RecyclerView mRecyclerView;
    private UserContract.Presenter mPresenter;

    public static Intent getIntent(Context context) {
        return new Intent(context, UserActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_user;
    }

    @Override
    protected void initComponent() {
        mRecyclerView = findViewById(R.id.recycler_progress);
        mPresenter = new UserPresenter(MarkRepository.getInstance(
                new MarkLocalDataSource(new MarkDatabaseHelper(new DBHelper(this)))), this);
    }

    @Override
    protected void initData() {
        mPresenter.getMarks();
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(List<Integer> values, List<Mark> marks) {
        ProgressAdapter adapter = new ProgressAdapter(values, marks);
        mRecyclerView.setAdapter(adapter);
    }
}

