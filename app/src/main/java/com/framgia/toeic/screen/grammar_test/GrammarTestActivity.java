package com.framgia.toeic.screen.grammar_test;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Grammar;
import com.framgia.toeic.data.repository.MarkRepository;
import com.framgia.toeic.data.source.local.DBHelper;
import com.framgia.toeic.data.source.local.MarkDatabaseHelper;
import com.framgia.toeic.data.source.local.MarkLocalDataSource;
import com.framgia.toeic.screen.base.DisplayAnswerListener;
import com.framgia.toeic.screen.base.ResultTest;
import com.framgia.toeic.screen.base.ShowAnswerListener;
import com.framgia.toeic.screen.grammar.GrammarActivity;
import com.framgia.toeic.screen.grammar_test.fragment_grammar.GrammarTestFragment;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.toeic.screen.base.ModuleID.GRAMMAR;

public class GrammarTestActivity extends ResultTest implements ShowAnswerListener,
        GrammarTestContract.View, GrammarTestFragment.OnAnswerChangeListener, ViewPager.OnPageChangeListener {
    static final String EXTRA_LIST_GRAMMAR = "EXTRA_LIST_GRAMMAR";
    private ViewPager mViewPager;
    private TextView mTextCheck;
    private TextView mTextTime;
    private ImageView mImageNext;
    private ImageView mImageBack;
    private List<Grammar> mGrammars;
    private List<Fragment> mFragments;
    private GrammarTestContract.Presenter mPresenter;

    public static Intent getIntent(Context context, List<Grammar> grammars) {
        Intent intent = new Intent(context, GrammarTestActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_LIST_GRAMMAR, (ArrayList<? extends Parcelable>) grammars);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDefaultBack();
        addViewPagerListener();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_grammar_test;
    }

    @Override
    protected void initComponent() {
        mViewPager = findViewById(R.id.view_pager);
        mTextCheck = findViewById(R.id.text_submit);
        mTextCheck.setOnClickListener(this);
        mTextTime = findViewById(R.id.text_timer);
        mImageNext = findViewById(R.id.image_next_first);
        mImageBack = findViewById(R.id.image_back_first);
        mFragments = new ArrayList<>();
        mPresenter = new GrammarTestPresenter(this,
                MarkRepository.getInstance(new MarkLocalDataSource(
                        new MarkDatabaseHelper(new DBHelper(this)))));
        mHandler = new Handler();
        mCountTime = START_TIME;
    }

    @Override
    protected void initData() {
        mGrammars = getIntent().getParcelableArrayListExtra(EXTRA_LIST_GRAMMAR);
        GrammarViewPagerAdapter adapter = new GrammarViewPagerAdapter(
                getSupportFragmentManager(), mGrammars, mFragments);
        mViewPager.setAdapter(adapter);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCountTime++;
                mTextTime.setText(getStringDatefromlong(mCountTime*TRANFER_SECOND_TO_MILISECOND));
                mHandler.postDelayed(this, TRANFER_SECOND_TO_MILISECOND);
            }
        });
    }

    @Override
    public void notifyFragments() {
        for (Fragment fragment : mFragments) {
            DisplayAnswerListener displayAnswerListener = (DisplayAnswerListener) fragment;
            displayAnswerListener.showAnswer();
            displayAnswerListener.disableClick();
        }
    }

    @Override
    public void showError(Exception error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onChanged(Grammar grammar) {
        int index = mGrammars.indexOf(grammar);
        if (index != -1) {
            mGrammars.get(index).setSelected(grammar.isSelected());
        }
    }

    @Override
    public void showDialogResult(int mark, int rating) {
        super.showDialogResult(mark, rating);
        mTextViewFalse.setText(mGrammars.size() - mark + "");
        mTextViewTimeResult.setText(mTextTime.getText());
    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_submit:
                mPresenter.checkResult(GRAMMAR, mGrammars);
                mHandler.removeCallbacksAndMessages(null);
                notifyFragments();
                mViewPager.addOnPageChangeListener(this);
                break;
            case R.id.button_review:
                mDialogResult.dismiss();
                break;
            case R.id.button_continue:
                finish();
                break;
        }
    }
    public void addViewPagerListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    mImageBack.setVisibility(View.GONE);
                }else if(position == mGrammars.size()-1){
                    mImageNext.setVisibility(View.GONE);
                } else {
                    mImageBack.setVisibility(View.VISIBLE);
                    mImageNext.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setDefaultBack() {
        mImageBack.setVisibility(View.GONE);
    }
}
