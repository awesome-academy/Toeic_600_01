package com.framgia.toeic.screen.basic_test_detail;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.BasicTestLesson;
import com.framgia.toeic.data.repository.BasicTestRepository;
import com.framgia.toeic.data.source.local.BasicTestDatabaseHelper;
import com.framgia.toeic.data.source.local.BasicTestLocalDatasource;
import com.framgia.toeic.data.source.local.DBHelper;
import com.framgia.toeic.screen.base.DisplayAnswerListener;
import com.framgia.toeic.screen.base.MediaPlayerManager;
import com.framgia.toeic.screen.base.ResultTest;
import com.framgia.toeic.screen.base.ShowAnswerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicTestDetailActivity extends ResultTest
        implements BasicTestDetailContract.View, ShowAnswerListener {
    private static final String EXTRA_BASIC_TEST_LESSON = "EXTRA_BASIC_TEST_LESSON";
    private static final String EXTENSION_MEDIA = ".mp3";
    private static final int TIMELINE = 15;
    private TextView mTextViewTime;
    private TextView mTextViewSubmit;
    private ImageView mImagePlayPause;
    private BasicTestLesson mLesson;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private BasicTestDetailContract.Presenter mPresenter;

    public static Intent getIntent(Context context, BasicTestLesson basicTestLesson) {
        Intent intent = new Intent(context, BasicTestDetailActivity.class);
        intent.putExtra(EXTRA_BASIC_TEST_LESSON, basicTestLesson);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_basic_test_detail;
    }

    @Override
    protected void initComponent() {
        super.initComponent();
        mTextViewSubmit = findViewById(R.id.text_submit);
        mTextViewTime = findViewById(R.id.text_timer);
        mViewPager = findViewById(R.id.view_pager);
        mFragments = new ArrayList<>();
        mImagePlayPause = findViewById(R.id.image_play_pause);
        mPresenter = new BasicTestDetailPresenter(this, BasicTestRepository
                .getInstance(new BasicTestLocalDatasource(new BasicTestDatabaseHelper(
                        new DBHelper(this)))));
    }

    @Override
    public void showData() {
        super.showData();
        mCountDownTimer = new CountDownTimer(
                TIMELINE * TRANFER_MINIUTE_TO_SECOND * TRANFER_SECOND_TO_MILISECOND,
                TRANFER_SECOND_TO_MILISECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTextViewTime.setText(getStringDatefromlong(millisUntilFinished) + "");
            }

            @Override
            public void onFinish() {
                submitAnswer();
            }
        };
        mCountDownTimer.start();
    }

    @Override
    protected void initData() {
        mLesson = getIntent().getParcelableExtra(EXTRA_BASIC_TEST_LESSON);
        ViewPagerAdapter adapter =
                new ViewPagerAdapter(getSupportFragmentManager(), mLesson.getBasicTests(), mFragments);
        mViewPager.setAdapter(adapter);
        mTextViewSubmit.setOnClickListener(this);
        mImagePlayPause.setOnClickListener(this);
        try {
            playMedia(mLesson.getBasicTests().get(0).getIdImage(), EXTENSION_MEDIA);
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        MediaPlayerManager.getInstance(new MediaPlayer()).setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mImagePlayPause.setImageResource(R.drawable.ic_play_button);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mPresenter.changeMediaFile(++i, mLesson.getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text_submit:
                submitAnswer();
                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {
                        notifyFragments();
                    }

                    @Override
                    public void onPageSelected(int i) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
                break;
            case R.id.image_play_pause:
                mPresenter.checkListening();
        }
    }

    @Override
    public void listenMedia() {
        mImagePlayPause.setImageResource(R.drawable.ic_pause_button);
        MediaPlayerManager.getInstance(new MediaPlayer()).startMedia();
    }

    @Override
    public void pauseMedia() {
        mImagePlayPause.setImageResource(R.drawable.ic_play_button);
        MediaPlayerManager.getInstance(new MediaPlayer()).pause();
    }

    @Override
    public void hideSeekBar() {
        MediaPlayerManager.getInstance(new MediaPlayer()).stop();
        mSeekBar.setVisibility(View.GONE);
        mImagePlayPause.setVisibility(View.GONE);
    }

    @Override
    public void changeMedia(int id) {
        mSeekBar.setVisibility(View.VISIBLE);
        mSeekBar.setProgress(0);
        mImagePlayPause.setVisibility(View.VISIBLE);
        mImagePlayPause.setImageResource(R.drawable.ic_play_button);
        try {
            playMedia(id, EXTENSION_MEDIA);
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void playMedia(int id, String extension) throws IOException {
        super.playMedia(id, extension);
        mSeekBar.setMax(MediaPlayerManager.getInstance(new MediaPlayer()).getDurationMedia());
    }

    @Override
    public void showDialogResult(int mark, int rating) {
        super.showDialogResult(mark, rating);
        mTextViewFalse.setText(mLesson.getBasicTests().size() - mark + "");
        mPresenter.updateLesson(mLesson, mark);
        mImagePlayPause.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerManager.getInstance(new MediaPlayer()).stop();
        mCountDownTimer.cancel();
    }

    @Override
    public void notifyFragments() {
        for (Fragment fragment : mFragments) {
            DisplayAnswerListener displayAnswerListener = (DisplayAnswerListener) fragment;
            displayAnswerListener.showAnswer();
        }
    }

    public void submitAnswer() {
        mPresenter.checkAnswer(mLesson.getBasicTests());
        mCountDownTimer.cancel();
        MediaPlayerManager.getInstance(new MediaPlayer()).stop();
        notifyFragments();
    }
}
