package com.framgia.toeic.data;

import com.framgia.toeic.data.model.ExamLesson;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public interface ExamLessonDataSource {
    interface Local {
        void getExamLessons(Callback<List<ExamLesson>> callback);
    }

    interface Remote {
    }
}
