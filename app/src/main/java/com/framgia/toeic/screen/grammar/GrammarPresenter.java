package com.framgia.toeic.screen.grammar;

import com.framgia.toeic.data.model.GrammarLesson;
import com.framgia.toeic.data.repository.GrammarLessonRepository;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public class GrammarPresenter implements GrammarContract.Presenter {
    private GrammarContract.View mView;
    private GrammarLessonRepository mRepository;

    public GrammarPresenter(GrammarContract.View view, GrammarLessonRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getGrammars() {
        mRepository.getGrammarLessons(new Callback<List<GrammarLesson>>() {
            @Override
            public void onGetDataSuccess(List<GrammarLesson> grammarLessons) {
                mView.showGrammars(grammarLessons);
            }

            @Override
            public void onGetDataFail(Exception error) {
                mView.showError(error);
            }
        });
    }
}
