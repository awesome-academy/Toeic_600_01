package com.framgia.toeic.screen.vocabulary_detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.framgia.toeic.data.model.Vocabulary;
import com.framgia.toeic.screen.vocabulary_detail.fragment_vocabulary.VocabularyFragment;

import java.util.List;

public class VocabularyViewPager extends FragmentPagerAdapter {
    private List<Vocabulary> mVocabularies;

    public VocabularyViewPager(FragmentManager fm, List<Vocabulary> vocabularies) {
        super(fm);
        mVocabularies = vocabularies;
    }

    @Override
    public Fragment getItem(int i) {
        return VocabularyFragment.newInstance(mVocabularies.get(i), i);
    }

    @Override
    public int getCount() {
        return mVocabularies.size();
    }

}
