package com.framgia.toeic.data;

import com.framgia.toeic.data.model.GrammarLesson;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public interface GrammarLessonDataSource {
    interface Local {
        void getGrammarLessons(Callback<List<GrammarLesson>> callback);
    }

    interface Remote {
    }
}
