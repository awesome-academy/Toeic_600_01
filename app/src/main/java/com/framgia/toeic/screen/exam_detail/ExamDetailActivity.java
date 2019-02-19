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
import com.framgia.toeic.data.repository.ExamLessonRepository;
import com.framgia.toeic.data.source.local.DBHelper;
import com.framgia.toeic.data.source.local.ExamLessonDatabaseHelper;
import com.framgia.toeic.data.source.local.ExamLessonLocalDataSource;
import com.framgia.toeic.screen.base.MediaPlayerManager;
import com.framgia.toeic.screen.base.ResultTest;

import java.io.IOException;

public class ExamDetailActivity extends ResultTest implements ExamDetailContract.View {
    private static final int TEST_DURATION = 120;
    private static final String EXTRA_EXAM_LESSON = "EXTRA_EXAM_LESSON";
    private static final String EXTENSION_MEDIA = ".m4a";
    private ExamLesson mLesson;
    private RecyclerView mRecyclerView;
    private TextView mTextViewSubmit;
    private TextView mTextViewTime;
    private ImageView mImagePlayPause;
    private ExamDetailContract.Presenter mPresenter;
    private ExamAdapter mAdapter;

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
        mPresenter = new ExamDetailPresenter(this,
                ExamLessonRepository.getInstance(
                        new ExamLessonLocalDataSource(new ExamLessonDatabaseHelper(new DBHelper(this)))));
    }

    @Override
    protected void initData() {
        mLesson = getIntent().getExtras().getParcelable(EXTRA_EXAM_LESSON);
        mAdapter = new ExamAdapter(this, mLesson.getExams(), false);
        MediaPlayerManager.getInstance(new MediaPlayer()).setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mImagePlayPause.setImageResource(R.drawable.ic_play_button);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text_submit:
                submitAnswer();
                break;
            case R.id.image_play_pause:
                mPresenter.checkListening();
                break;
        }
    }

    @Override
    public void showDialogResult(int mark, int rating) {
        super.showDialogResult(mark, rating);
        mTextViewFalse.setText(mLesson.getExams().size() - mark + "");
        mPresenter.updateLesson(mLesson, mark);
        mImagePlayPause.setEnabled(false);
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
    public void showError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void playMedia(int id, String extension) throws IOException {
        super.playMedia(id, extension);
        mSeekBar.setMax(MediaPlayerManager.getInstance(new MediaPlayer()).getDurationMedia());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimer.cancel();
    }

    @Override
    public void showData() {
        super.showData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        try {
            playMedia(mLesson.getId(), EXTENSION_MEDIA);
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
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
        mCountDownTimer.start();
    }

    public void addListener() {
        mTextViewSubmit.setOnClickListener(this);
        mImagePlayPause.setOnClickListener(this);
    }

    public void submitAnswer() {
        mPresenter.checkAnswer(mLesson.getExams());
        mCountDownTimer.cancel();
        MediaPlayerManager.getInstance(new MediaPlayer()).stop();
        mSeekBar.setEnabled(false);
        mAdapter.setChecked(true);
        mAdapter.notifyDataSetChanged();
    }
}
