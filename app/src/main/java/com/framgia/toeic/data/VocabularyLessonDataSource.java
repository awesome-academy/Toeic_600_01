package com.framgia.toeic.data;

import com.framgia.toeic.data.model.VocabularyLesson;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public interface VocabularyLessonDataSource {
    interface Local {
        void getVocabularies(Callback<List<VocabularyLesson>> Callback);
    }

    interface Remote {
    }
}
