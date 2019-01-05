package com.framgia.toeic.screen.vocabulary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.VocabularyLesson;
import com.framgia.toeic.data.repository.VocabularyLessonRepository;
import com.framgia.toeic.data.source.local.DBHelper;
import com.framgia.toeic.data.source.local.VocabularyLessonLocalDataSource;
import com.framgia.toeic.screen.base.BaseActivity;
import com.framgia.toeic.screen.vocabulary_detail.VocabularyDetailActivity;

import java.util.List;

public class VocabularyActivity extends BaseActivity implements View.OnClickListener, VocabularyContract.View {
    private VocabularyLessonAdapter mVocabularyLessonAdapter;
    private RecyclerView mRecyclerView;
    private VocabularyContract.Presenter mPresenter;

    public static Intent getVocabularyIntent(Context context) {
        return new Intent(context, VocabularyActivity.class);
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
        mRecyclerView = findViewById(R.id.recycler_vocabulary_lesson);
    }

    @Override
    protected void initData() {
        mPresenter = new VocabularyPresenter(this, new VocabularyLessonRepository(
                new VocabularyLessonLocalDataSource(new DBHelper(this))));
        mPresenter.getVocabularies();
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

    @Override
    public void showVocabularies(List<VocabularyLesson> vocabularyLessons) {
        mVocabularyLessonAdapter = new VocabularyLessonAdapter(this, vocabularyLessons);
        mRecyclerView.setLayoutManager(new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mVocabularyLessonAdapter);
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
