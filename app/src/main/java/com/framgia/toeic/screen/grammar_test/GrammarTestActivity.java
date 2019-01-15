package com.framgia.toeic.screen.grammar_test;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Grammar;
import com.framgia.toeic.data.model.GrammarLesson;
import com.framgia.toeic.screen.base.BaseActivity;
import com.framgia.toeic.screen.base.DisplayAnswerListener;
import com.framgia.toeic.screen.base.ShowAnswerListener;

import java.util.ArrayList;
import java.util.List;

public class GrammarTestActivity extends BaseActivity implements ShowAnswerListener {
    static final String EXTRA_LIST_GRAMMAR = "EXTRA_LIST_GRAMMAR";
    private ViewPager mViewPager;
    private TextView mTextViewCheck;
    private TextView mTextViewTime;
    private ArrayList<Grammar> mGrammars;
    private List<Fragment> mFragments;
    private Dialog mDialogResult;

    public static Intent getIntent(Context context, List<Grammar> grammars) {
        Intent intent = new Intent(context, GrammarTestActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_LIST_GRAMMAR, (ArrayList<? extends Parcelable>) grammars);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_grammar_test;
    }

    @Override
    protected void initComponent() {
        mViewPager = findViewById(R.id.viewPager);
        mTextViewCheck = findViewById(R.id.text_submit);
        mTextViewTime = findViewById(R.id.text_time);
        mFragments = new ArrayList<>();
    }

    @Override
    protected void initData() {
        mGrammars = getIntent().getParcelableArrayListExtra(EXTRA_LIST_GRAMMAR);
        GrammarViewPagerAdapter grammarViewPagerAdapter = new GrammarViewPagerAdapter(getSupportFragmentManager(), mGrammars, mFragments);
        mViewPager.setAdapter(grammarViewPagerAdapter);
    }

    @Override
    public void notifyFragments() {
        for (Fragment fragment: mFragments){
            DisplayAnswerListener displayAnswerListener = (DisplayAnswerListener) fragment;
            displayAnswerListener.showAnswer();
            displayAnswerListener.disableClick();
        }
    }
}
