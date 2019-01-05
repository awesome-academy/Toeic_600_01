package com.framgia.toeic.screen.vocabulary;

import com.framgia.toeic.data.model.VocabularyLesson;
import com.framgia.toeic.data.repository.VocabularyLessonRepository;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public class VocabularyPresenter implements VocabularyContract.Presenter {
    private VocabularyLessonRepository mRepository;
    private VocabularyContract.View mView;

    public VocabularyPresenter(VocabularyContract.View view, VocabularyLessonRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getVocabularies() {
        mRepository.getVocabularies(new Callback<List<VocabularyLesson>>() {
            @Override
            public void onGetDataSuccess(List<VocabularyLesson> vocabularyLessons) {
                mView.showVocabularies(vocabularyLessons);
            }

            @Override
            public void onGetDataFail(Exception e) {
                mView.showError(e);
            }
        });
    }
}
