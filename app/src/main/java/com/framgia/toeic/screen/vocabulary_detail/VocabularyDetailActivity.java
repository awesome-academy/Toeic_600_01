package com.framgia.toeic.screen.vocabulary_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Vocabulary;
import com.framgia.toeic.screen.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class VocabularyDetailActivity extends BaseActivity {
    static final String EXTRA_LIST_QUESTION = "com.framgia.toeic.screen.vocabulary.EXTRA_LIST_QUESTION";

    public static Intent getVocabularyDetailIntent(Context context, List<Vocabulary> vocabularies) {
        Intent intent = new Intent(context, VocabularyDetailActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_LIST_QUESTION, (ArrayList<? extends Parcelable>) vocabularies);
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

    }

    @Override
    protected void initData() {
        List<Vocabulary> vocabularies = getIntent().getParcelableArrayListExtra(EXTRA_LIST_QUESTION);
    }
}
