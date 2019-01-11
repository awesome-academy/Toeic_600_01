package com.framgia.toeic.screen.vocabulary_detail.fragment_vocabulary;

public class VocabularyPresenter implements VocabularyDetailContract.Presenter{
    private VocabularyDetailContract.View mView;

    public VocabularyPresenter(VocabularyDetailContract.View view) {
        mView = view;
    }

    @Override
    public void checkAnwser(String chosen, String result) {
        if (chosen.equals(chosen)){
            mView.onRightAnswer();
        } else {
            mView.onWrongAnswer();
        }
    }
}
