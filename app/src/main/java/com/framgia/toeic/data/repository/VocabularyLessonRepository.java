package com.framgia.toeic.data.repository;

import com.framgia.toeic.data.VocabularyLessonDataSource;
import com.framgia.toeic.data.model.VocabularyLesson;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public class VocabularyLessonRepository implements VocabularyLessonDataSource.Local {
    public static VocabularyLessonRepository sVocabularyLessonRepository;
    private VocabularyLessonDataSource.Local mVocabularyLessonDataSource;

    public VocabularyLessonRepository(VocabularyLessonDataSource.Local vocabularyLessonDataSource) {
        mVocabularyLessonDataSource = vocabularyLessonDataSource;
    }

    public static VocabularyLessonRepository getInstance(VocabularyLessonDataSource.Local local) {
        if (sVocabularyLessonRepository == null) {
            sVocabularyLessonRepository = new VocabularyLessonRepository(local);
        }
        return sVocabularyLessonRepository;
    }

    @Override
    public void getVocabularies(Callback<List<VocabularyLesson>> Callback) {
        mVocabularyLessonDataSource.getVocabularies(Callback);
    }
}
