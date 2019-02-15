package com.framgia.toeic.screen.exam_detail;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.ExamLesson;
import com.framgia.toeic.screen.base.ResultTest;

import java.io.IOException;

public class ExamDetailActivity extends ResultTest {
    private static final int TEST_DURATION = 120;
    private static final String EXTRA_EXAM_LESSON = "EXTRA_EXAM_LESSON";
    private static final String EXTENSION_MEDIA = ".m4a";
    private ExamLesson mLesson;
    private RecyclerView mRecyclerView;
    private TextView mTextViewSubmit;
    private TextView mTextViewTime;
    private ImageView mImagePlayPause;

    public static Intent getIntent(Context context, ExamLesson examLesson) {
        Intent intent = new Intent(context, ExamDetailActivity.class);
        intent.putExtra(EXTRA_EXAM_LESSON, examLesson);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showData();
        addListener();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_exam_detail;
    }

    @Override
    protected void initComponent() {
        super.initComponent();
        mRecyclerView = findViewById(R.id.recycler_exam_detail);
        mTextViewSubmit = findViewById(R.id.text_submit);
        mTextViewTime = findViewById(R.id.text_timer_exam);
        mImagePlayPause = findViewById(R.id.image_play_pause);
        mCountDownTimer = new CountDownTimer(
                TEST_DURATION * TRANFER_MINIUTE_TO_SECOND * TRANFER_SECOND_TO_MILISECOND,
                TRANFER_SECOND_TO_MILISECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTextViewTime.setText(getStringDatefromlong(millisUntilFinished) + "");
            }

            @Override
            public void onFinish() {

            }
        };
    }

    @Override
    protected void initData() {
        super.initData();
        mLesson = getIntent().getExtras().getParcelable(EXTRA_EXAM_LESSON);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text_submit:
                submitAnswer();
                break;
        }
    }

    @Override
    public void showDialogResult(int mark, int rating) {
        super.showDialogResult(mark, rating);
        mTextViewFalse.setText(mLesson.getExams().size() - mark + "");
    }

    @Override
    public void playMedia(int id, String extension) throws IOException {
        super.playMedia(id, extension);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimer.cancel();
    }

    public void submitAnswer() {
        mCountDownTimer.cancel();
        mSeekBar.setEnabled(false);
    }

    public void showData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        try {
            playMedia(mLesson.getId(), EXTENSION_MEDIA);
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void addListener() {
        mTextViewSubmit.setOnClickListener(this);
        mImagePlayPause.setOnClickListener(this);
    }
}
