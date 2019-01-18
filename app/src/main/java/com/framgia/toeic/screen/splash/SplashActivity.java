package com.framgia.toeic.screen.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.framgia.toeic.R;
import com.framgia.toeic.data.repository.FileRepository;
import com.framgia.toeic.data.source.remote.FileRemoteDatasource;
import com.framgia.toeic.screen.base.BaseActivity;
import com.framgia.toeic.screen.main.MainActivity;
import com.google.firebase.FirebaseApp;

import java.util.List;

public class SplashActivity extends BaseActivity implements View.OnClickListener,
        SplashContract.View, OnWriteData {
    private Button mButton;
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getLinkAudioFiles();
        mPresenter.getLinkImageFiles();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void initComponent() {
        mButton = findViewById(R.id.button_splash);
        mPresenter = new SplashPresenter(this, FileRepository.getInstance(
                new FileRepository(new FileRemoteDatasource())));
    }

    @Override
    protected void initData() {
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_splash:
                startActivity(MainActivity.getMainIntent(this));
                break;
            default:
        }
    }

    @Override
    public void downloadAudioFile(List<String> links) {
        startService(AudioService.getIntentAudioService(getApplicationContext(), links));
    }

    @Override
    public void downloadImageFile(List<String> links) {
        startService(ImageService.getIntentImageService(getApplicationContext(), links));
    }

    @Override
    public void writeFileError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void writeFileSuccess(String success) {

    }
}
