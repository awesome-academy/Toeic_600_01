package com.framgia.toeic.screen.grammar;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.framgia.toeic.R;
import com.framgia.toeic.screen.base.BaseActivity;

public class GrammarActivity extends BaseActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerLessonGrammar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_grammar;
    }

    @Override
    protected void initComponent() {
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerLessonGrammar = findViewById(R.id.recycle_lesson_grammar);
    }

    @Override
    protected void initData() {
        mToolbar.setTitle(getResources().getString(R.string.action_grammar));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitleTextColor(getResources()
                .getColor(R.color.material_white_100));
    }
}
