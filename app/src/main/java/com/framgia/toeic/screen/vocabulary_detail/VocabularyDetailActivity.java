package com.framgia.toeic.screen.vocabulary_detail;

import android.content.Context;
import android.content.Intent;

import com.framgia.toeic.R;
import com.framgia.toeic.screen.base.BaseActivity;
import com.framgia.toeic.screen.vocabulary.VocabularyActivity;

public class VocabularyDetailActivity extends BaseActivity {
    public static Intent getVocabularyIntent(Context context){
        return new Intent(context,VocabularyDetailActivity.class);
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

    }
}
