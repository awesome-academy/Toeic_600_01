package com.framgia.toeic.screen.vocabulary_detail;

import android.util.Log;

import com.framgia.toeic.data.model.Mark;
import com.framgia.toeic.data.model.Vocabulary;
import com.framgia.toeic.data.repository.MarkRepository;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public class VocabularyDetailPresenter implements VocabularyDetailContract.Presenter {

    private VocabularyDetailContract.View mView;
    private MarkRepository mRepository;

    public VocabularyDetailPresenter(VocabularyDetailContract.View view, MarkRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void checkResult(final int id, List<Vocabulary> vocabularies) {
        int score = 0;
        for (Vocabulary vocabulary : vocabularies) {
            if (vocabulary.isSelected()) {
                score++;
            }
        }

        final int finalScore = score;
        mRepository.getMark(id, new Callback<Mark>() {
            @Override
            public void onGetDataSuccess(Mark mark) {
                if (finalScore > mark.getMark()){
                    mRepository.updateMark(id, finalScore);
                }
                mView.showDialogResult(finalScore);
            }

            @Override
            public void onGetDataFail(Exception error) {
                mView.showError(error);
            }
        });
    }
}
