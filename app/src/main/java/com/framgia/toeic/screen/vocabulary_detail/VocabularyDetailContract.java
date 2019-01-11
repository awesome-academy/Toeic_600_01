package com.framgia.toeic.screen.vocabulary_detail;

import com.framgia.toeic.data.model.Vocabulary;

import java.util.List;

public interface VocabularyDetailContract {
    interface View{
        void showDialogResult(int mark, String time);
    }
    interface Presenter{
        void checkResult(List<Vocabulary> vocabularies);
    }
}
