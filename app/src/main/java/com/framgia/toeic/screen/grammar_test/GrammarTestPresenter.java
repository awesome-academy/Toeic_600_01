package com.framgia.toeic.screen.grammar_test;

import android.util.Log;

import com.framgia.toeic.data.model.Grammar;
import com.framgia.toeic.data.model.GrammarLesson;
import com.framgia.toeic.data.repository.GrammarLessonRepository;
import com.framgia.toeic.data.source.Callback;
import com.framgia.toeic.screen.base.RatingCaculator;

import java.util.List;

public class GrammarTestPresenter extends RatingCaculator implements GrammarTestContract.Presenter {
    private GrammarTestContract.View mView;
    private GrammarLessonRepository mRepository;

    public GrammarTestPresenter(GrammarTestContract.View view, GrammarLessonRepository repository) {
        mView = view;
        mRepository = repository;
    }

    public int caculateScore(List<Grammar> grammars) {
        int score = 0;
        for (Grammar grammar : grammars) {
            if (grammar.isSelected()) {
                score++;
            }
        }
        return score;
    }

    @Override
    public void checkResult(final int id, List<Grammar> grammars) {
        final int score = caculateScore(grammars);
        final int rating = rating(score, grammars.size());
        mView.showDialogResult(score, rating);
    }

    @Override
    public void updateLesson(GrammarLesson lesson, final int mark) {
        if (mark > lesson.getRating()) {
            mRepository.updateGrammar(lesson, mark, new Callback<GrammarLesson>() {
                @Override
                public void onGetDataSuccess(GrammarLesson grammarLesson) {
                    grammarLesson.setRating(mark);
                }

                @Override
                public void onGetDataFail(Exception error) {
                    mView.showError(error);
                }
            });
        }
    }
}
