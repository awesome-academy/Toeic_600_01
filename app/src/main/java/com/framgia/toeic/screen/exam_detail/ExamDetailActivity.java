package com.framgia.toeic.screen.exam_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.ExamLesson;
import com.framgia.toeic.data.repository.ExamLessonRepository;
import com.framgia.toeic.data.source.local.DBHelper;
import com.framgia.toeic.data.source.local.ExamLessonDatabaseHelper;
import com.framgia.toeic.data.source.local.ExamLessonLocalDataSource;
import com.framgia.toeic.screen.base.MediaPlayerInstance;
import com.framgia.toeic.screen.base.ResultTest;

public class ExamDetailActivity extends ResultTest {
    private static final int TIMELINE = 120;
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
        Window window = getWindow();
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.material_accent_700));
        }
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
                TIMELINE * TRANFER_MINIUTE_TO_SECOND * TRANFER_SECOND_TO_MILISECOND,
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mTextViewSubmit.setOnClickListener(this);
        mImagePlayPause.setOnClickListener(this);
        playMedia(mLesson.getId(), EXTENSION_MEDIA);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSeekBar.setProgress(MediaPlayerInstance.getInstance().getCurrentPosition());
                mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        MediaPlayerInstance.getInstance().pause();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        MediaPlayerInstance.getInstance().seekTo(seekBar.getProgress());
                        MediaPlayerInstance.getInstance().start();
                    }
                });
                mHandler.postDelayed(this, TRANFER_SECOND_TO_MILISECOND);
            }
        }, 0);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text_submit:
                submitAnswer();
                break;
            case R.id.image_play_pause:
        }
    }

    @Override
    public void showDialogResult(int mark, int rating) {
        super.showDialogResult(mark, rating);
        mTextViewFalse.setText(mLesson.getExams().size() - mark + "");
    }

    @Override
    public void playMedia(int id, String extension) {
        super.playMedia(id, extension);
        MediaPlayerInstance.getInstance().start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerInstance.getInstance().stop();
        mCountDownTimer.cancel();
    }

    public void submitAnswer(){
        mCountDownTimer.cancel();
        MediaPlayerInstance.getInstance().stop();
        mSeekBar.setEnabled(false);
    }
}
