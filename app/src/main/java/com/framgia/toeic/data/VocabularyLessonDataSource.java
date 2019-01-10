package com.framgia.toeic.data;

import com.framgia.toeic.data.model.VocabularyLessonItem;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public interface VocabularyLessonDataSource {
    interface Local {
        void getLessonVocabularies(Callback<List<VocabularyLessonItem>> callback);
    }

    interface Remote {
    }
}
