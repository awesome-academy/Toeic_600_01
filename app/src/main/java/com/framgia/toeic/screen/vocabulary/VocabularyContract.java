package com.framgia.toeic.screen.vocabulary;

import com.framgia.toeic.data.model.VocabularyLesson;

import java.util.List;

public interface VocabularyContract {
    interface View {
        void showVocabularies(List<VocabularyLesson> vocabularyLessons);

        void showError(Exception e);
    }

    interface Presenter {
        void getVocabularies();
    }
}
