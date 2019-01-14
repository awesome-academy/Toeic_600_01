package com.framgia.toeic.screen.vocabulary_detail;

import com.framgia.toeic.data.model.Vocabulary;

import java.util.List;

public class VocabularyDetailPresenter implements VocabularyDetailContract.Presenter {

    private VocabularyDetailContract.View mView;

    public VocabularyDetailPresenter(VocabularyDetailContract.View view) {
        mView = view;
    }

    @Override
    public void checkResult(List<Vocabulary> vocabularies) {
        int mark = 0;
        for (Vocabulary vocabulary: vocabularies){
            if (vocabulary.isSelected()){
                mark++;
            }
        }
        mView.showDialogResult(mark, "");
    }
}
