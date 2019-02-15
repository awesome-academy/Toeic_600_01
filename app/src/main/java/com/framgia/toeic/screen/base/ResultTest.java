package com.framgia.toeic.screen.base;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.framgia.toeic.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.framgia.toeic.screen.base.RatingResult.BAD;
import static com.framgia.toeic.screen.base.RatingResult.GOOD;
import static com.framgia.toeic.screen.base.RatingResult.NONE;
import static com.framgia.toeic.screen.base.RatingResult.NORMAL;
import static com.framgia.toeic.screen.base.RatingResult.VERY_BAD;
import static com.framgia.toeic.screen.base.RatingResult.VERY_GOOD;

public abstract class ResultTest extends BaseActivity implements View.OnClickListener {
    private static final String FORMAT_TIME = "%02d : %02d";
    public static final int TRANFER_MINIUTE_TO_SECOND = 60;
    public static final int TRANFER_SECOND_TO_MILISECOND = 1000;
    public static final int START_TIME = 0;
    protected Dialog mDialogResult;
    protected TextView mTextViewFalse;
    protected CountDownTimer mCountDownTimer;
    protected int mCountTime;
    protected Handler mHandler;
    protected SeekBar mSeekBar;
    protected TextView mTextViewTimeResult;

    @Override
    protected void initComponent() {
        mSeekBar = findViewById(R.id.seek_bar);
        mHandler = new Handler(getMainLooper());
    }

    @Override
    protected void initData() {
        mCountDownTimer.start();
    }

    public void showDialogResult(int mark, int rating) {
        mDialogResult = new Dialog(this);
        mDialogResult.setContentView(R.layout.dialog_result);
        TextView textViewRating = mDialogResult.findViewById(R.id.text_rating);
        RatingBar ratingBar = mDialogResult.findViewById(R.id.ratingBar);
        TextView textViewTrue = mDialogResult.findViewById(R.id.text_number_question_true);
        mTextViewTimeResult = mDialogResult.findViewById(R.id.text_time);
        mTextViewFalse = mDialogResult.findViewById(R.id.text_number_question_false);
        Button buttonReview = mDialogResult.findViewById(R.id.button_review);
        Button buttonContinue = mDialogResult.findViewById(R.id.button_continue);
        ratingBar.setRating(rating);
        textViewTrue.setText(mark + "");
        buttonReview.setOnClickListener(this);
        buttonContinue.setOnClickListener(this);
        mDialogResult.setCanceledOnTouchOutside(false);
        mDialogResult.show();
        switch (rating) {
            case NONE:
                textViewRating.setText(getResources().getString(R.string.title_very_bad));
                break;
            case VERY_BAD:
                textViewRating.setText(getResources().getString(R.string.title_very_bad));
                break;
            case BAD:
                textViewRating.setText(getResources().getString(R.string.title_bad));
                break;
            case NORMAL:
                textViewRating.setText(getResources().getString(R.string.title_normal));
                break;
            case GOOD:
                textViewRating.setText(getResources().getString(R.string.title_good));
                break;
            case VERY_GOOD:
                textViewRating.setText(getResources().getString(R.string.title_very_good));
                break;
        }
    }

    protected String getStringDatefromlong(long countTime) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(countTime);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(countTime) - TimeUnit.MINUTES.toSeconds(minutes);
        return String.format(FORMAT_TIME, minutes, seconds);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_review:
                mDialogResult.dismiss();
                break;
            case R.id.button_continue:
                finish();
                break;
        }
    }

    public void playMedia(int id, String extension) throws IOException {
    }
}
