package com.framgia.toeic.screen.vocabulary_detail;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Vocabulary;
import com.framgia.toeic.screen.base.BaseActivity;
import com.framgia.toeic.screen.vocabulary_detail.fragment_vocabulary.VocabularyFragment;

import java.util.ArrayList;
import java.util.List;

public class VocabularyDetailActivity extends BaseActivity
        implements VocabularyDetailContract.View, VocabularyFragment.OnAnswerChangeListener,
        ShowAnswerListener, View.OnClickListener, ViewPager.OnPageChangeListener {
    private static final String EXTRA_LIST_QUESTION = "EXTRA_LIST_QUESTION";
    private ViewPager mViewPager;
    private TextView mTextViewCheck;
    private ArrayList<Vocabulary> mVocabularies;
    private List<Fragment> mVocabularyFragments;
    private Dialog mDialogResult;
    private VocabularyDetailContract.Presenter mPresenter;
    public static Intent getIntent(Context context, List<Vocabulary> vocabularies) {
        Intent intent = new Intent(context, VocabularyDetailActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_LIST_QUESTION,
                (ArrayList<? extends Parcelable>) vocabularies);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_vocabulary_detail;
    }

    @Override
    protected void initComponent() {
        mViewPager = findViewById(R.id.viewPager);
        mTextViewCheck = findViewById(R.id.text_submit);
        mTextViewCheck.setOnClickListener(this);
        mVocabularyFragments = new ArrayList();
        mPresenter = new VocabularyDetailPresenter(this);
    }

    @Override
    protected void initData() {
        mVocabularies = getIntent().getParcelableArrayListExtra(EXTRA_LIST_QUESTION);
        VocabularyViewPager vocabularyViewPager =
                new VocabularyViewPager(getSupportFragmentManager(), mVocabularies, mVocabularyFragments);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(vocabularyViewPager);
    }

    @Override
    public void onChanged(Vocabulary vocabulary) {
        int index = mVocabularies.indexOf(vocabulary);
        if (index != -1) {
            mVocabularies.get(index).setSelected(vocabulary.isSelected());
        }
    }

    @Override
    public void notifyFragments() {
        for (Fragment fragment: mVocabularyFragments) {
            DisplayAnwser displayAnwser = (DisplayAnwser) fragment;
            displayAnwser.showAnswer();
            displayAnwser.disableClick();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_submit:
                mPresenter.checkResult(mVocabularies);
                notifyFragments();
                mViewPager.addOnPageChangeListener(this);
                break;
        }
    }

    @Override
    public void showDialogResult(int mark, String time) {
        mDialogResult = new Dialog(this);
        mDialogResult.setContentView(R.layout.dialog_result);
        TextView textViewMark = mDialogResult.findViewById(R.id.text_mark);
        Button buttonReview = mDialogResult.findViewById(R.id.button_review);
        Button buttonContinue = mDialogResult.findViewById(R.id.button_continue);
        textViewMark.setText(mark + "/" + mVocabularies.size());
        buttonReview.setOnClickListener(this);
        buttonContinue.setOnClickListener(this);
        mDialogResult.setCanceledOnTouchOutside(false);
        mDialogResult.show();
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
}
