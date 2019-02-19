package com.framgia.toeic.screen.user;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Mark;
import com.framgia.toeic.data.model.User;
import com.framgia.toeic.data.repository.MarkRepository;
import com.framgia.toeic.data.repository.UserRepository;
import com.framgia.toeic.data.source.local.DBHelper;
import com.framgia.toeic.data.source.local.MarkDatabaseHelper;
import com.framgia.toeic.data.source.local.MarkLocalDataSource;
import com.framgia.toeic.data.source.local.UserLocalDataSource;
import com.framgia.toeic.screen.base.BaseActivity;

import java.util.List;

public class UserActivity extends BaseActivity implements View.OnClickListener, UserContract.View {
    private Dialog mDialog;
    private ImageView mImageUser;
    private TextView mTextName;
    private TextView mTextTarget;
    private EditText mEditName;
    private EditText mEditTarget;
    private Button mButtonCancel;
    private Button mButtonSave;
    private RecyclerView mRecyclerView;
    private UserContract.Presenter mPresenter;

    public static Intent getIntent(Context context) {
        return new Intent(context, UserActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListener();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_user;
    }

    @Override
    protected void initComponent() {
        mImageUser = findViewById(R.id.image_edit);
        mTextName = findViewById(R.id.text_name_user);
        mTextTarget = findViewById(R.id.text_target_user);
        mRecyclerView = findViewById(R.id.recycler_progress);
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_user);
        mButtonCancel = mDialog.findViewById(R.id.button_cancel);
        mButtonSave = mDialog.findViewById(R.id.button_save);
        mEditName = mDialog.findViewById(R.id.edit_name);
        mEditTarget = mDialog.findViewById(R.id.edit_target);
        MarkLocalDataSource markDataSource = new MarkLocalDataSource(
                new MarkDatabaseHelper(new DBHelper(this)));
        UserLocalDataSource userDataSource = new UserLocalDataSource(
                PreferenceManager.getDefaultSharedPreferences(this));
        mPresenter = new UserPresenter(UserRepository.getInstance(userDataSource),
                MarkRepository.getInstance(markDataSource), this);
    }

    public void addListener() {
        mButtonCancel.setOnClickListener(this);
        mButtonSave.setOnClickListener(this);
        mImageUser.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.getMarks();
        mPresenter.getUser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_edit:
                showDialogEditName(mTextName.getText().toString(), mTextTarget.getText().toString());
                break;
            case R.id.button_cancel:
                mDialog.cancel();
                break;
            case R.id.button_save:
                mTextName.setText(mEditName.getText());
                mTextTarget.setText(mEditTarget.getText());
                User user = new User(mEditName.getText().toString(), mEditTarget.getText().toString());
                mPresenter.updateUser(user);
                mDialog.cancel();
                break;
        }
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

    @Override
    public void showUser(User user) {
        mTextName.setText(user.getName());
        mTextTarget.setText(user.getTarget());
    }

    public void showDialogEditName(String name, String target) {
        mEditName.setText(name);
        mEditTarget.setText(target);
        mDialog.show();
    }
}

