package com.framgia.toeic.screen.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.framgia.toeic.R;
import com.framgia.toeic.screen.base.BaseActivity;
import com.framgia.toeic.screen.basic_test.BasicTestActivity;
import com.framgia.toeic.screen.exam.ExamActivity;
import com.framgia.toeic.screen.grammar.GrammarActivity;
import com.framgia.toeic.screen.vocabulary.VocabularyActivity;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final int BACKGROUNDS_INTRO[] = {R.drawable.bg_intro_1,
            R.drawable.bg_intro_2, R.drawable.bg_intro_3, R.drawable.bg_intro_4};
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ViewFlipper mViewFlipper;
    private ProgressBar mProgressVocabulary;
    private ProgressBar mProgressGrammar;
    private ProgressBar mProgressBasicTest;
    private ProgressBar mProgressExam;
    private DrawerLayout mDrawer;
    private Button mButtonVocabulary, mButtonGrammar, mButtonBasicTest, mButtonExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponent() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                mDrawer, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mViewFlipper = findViewById(R.id.viewflipper);
        mProgressVocabulary = findViewById(R.id.progress_vocabulary);
        mProgressGrammar = findViewById(R.id.progress_grammar);
        mProgressBasicTest = findViewById(R.id.progress_basic_test);
        mProgressExam = findViewById(R.id.progress_exam);
        mButtonVocabulary = findViewById(R.id.button_vocabulary);
        mButtonGrammar = findViewById(R.id.button_grammar);
        mButtonBasicTest = findViewById(R.id.button_basic_test);
        mButtonExam = findViewById(R.id.button_exam);
    }

    @Override
    protected void initData() {
        slideAdvertise();
        mProgressVocabulary.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.material_orange_300),
                android.graphics.PorterDuff.Mode.MULTIPLY);
        mProgressGrammar.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.material_brown_300),
                android.graphics.PorterDuff.Mode.MULTIPLY);
        mProgressBasicTest.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.material_blue_accent_400),
                android.graphics.PorterDuff.Mode.MULTIPLY);
        mProgressExam.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.material_red_accent_200),
                android.graphics.PorterDuff.Mode.MULTIPLY);
        mButtonVocabulary.setOnClickListener(this);
        mButtonGrammar.setOnClickListener(this);
        mButtonBasicTest.setOnClickListener(this);
        mButtonExam.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_vocabulary:
                startActivity(VocabularyActivity.getVocabularyIntent(this));
                break;

            case R.id.nav_grammar:
                startActivity(GrammarActivity.getGrammarIntent(this));
                break;

            case R.id.nav_basic_test:
                startActivity(BasicTestActivity.getBasicTestIntent(this));
                break;

            case R.id.nav_exam:
                startActivity(ExamActivity.getExamIntent(this));
                break;

            case R.id.nav_share:
                break;

            case R.id.nav_setting:
                break;

            case R.id.nav_rating:
                break;

            case R.id.nav_about:
                break;

        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_vocabulary:
                startActivity(VocabularyActivity.getVocabularyIntent(this));
                break;

            case R.id.button_grammar:
                startActivity(GrammarActivity.getGrammarIntent(this));
                break;

            case R.id.button_basic_test:
                startActivity(BasicTestActivity.getBasicTestIntent(this));
                break;

            case R.id.button_exam:
                startActivity(ExamActivity.getExamIntent(this));
                break;
        }
    }

    private void slideAdvertise() {
        for (int image : BACKGROUNDS_INTRO) {
            flipperImage(image);
        }
    }

    private void flipperImage(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        mViewFlipper.addView(imageView);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        mViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
