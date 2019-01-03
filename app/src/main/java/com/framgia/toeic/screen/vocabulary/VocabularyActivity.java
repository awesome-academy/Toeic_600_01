package com.framgia.toeic.screen.vocabulary;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;

import com.framgia.toeic.R;
import com.framgia.toeic.screen.base.BaseActivity;
import com.framgia.toeic.screen.vocabulary_detail.VocabularyDetailActivity;

public class VocabularyActivity extends BaseActivity implements View.OnClickListener {

    public static Intent getVocabularyIntent(Context context){
        return new Intent(context,VocabularyActivity.class);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_vocabulary;
    }

    @Override
    protected void initComponent() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.action_vocabulary));
        findViewById(R.id.fab_next).setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View view) {
        startActivity(VocabularyDetailActivity.getVocabularyIntent(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
