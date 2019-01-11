package com.framgia.toeic.screen.vocabulary_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Vocabulary;
import com.framgia.toeic.screen.base.BaseActivity;
import com.framgia.toeic.screen.vocabulary_detail.fragment_vocabulary.VocabularyFragment;

import java.util.ArrayList;
import java.util.List;

public class VocabularyDetailActivity extends BaseActivity
        implements VocabularyFragment.OnAnswerChangeListener {
    private static final String EXTRA_LIST_QUESTION = "EXTRA_LIST_QUESTION";
    private ViewPager mViewPager;
    private ArrayList<Vocabulary> mVocabularies;

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
    }

    @Override
    protected void initData() {
        mVocabularies = getIntent().getParcelableArrayListExtra(EXTRA_LIST_QUESTION);
        VocabularyViewPager vocabularyViewPager =
                new VocabularyViewPager(getSupportFragmentManager(), mVocabularies);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(vocabularyViewPager);
    }

    public ArrayList<Vocabulary> getVocabularies() {
        return mVocabularies;
    }

    @Override
    public void onChanged(Vocabulary vocabulary) {
        int index = mVocabularies.indexOf(vocabulary);
        if (index != -1) {
            mVocabularies.get(index).setSelected(vocabulary.isSelected());
        }
    }
}
