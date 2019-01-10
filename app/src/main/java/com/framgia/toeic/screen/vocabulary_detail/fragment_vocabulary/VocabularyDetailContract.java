package com.framgia.toeic.screen.vocabulary_detail.fragment_vocabulary;

public interface VocabularyDetailContract {
    interface View{
        void onRightAnswer();

        void onWrongAnswer();
    }

    interface Presenter{
        void checkAnwser(String chosen, String result);
    }
}
