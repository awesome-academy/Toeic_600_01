package com.framgia.toeic.data.source.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.framgia.toeic.data.ExamLessonDataSource;
import com.framgia.toeic.data.model.ExamLesson;
import com.framgia.toeic.data.source.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamLessonDatabaseHelper implements ExamLessonDataSource.Local {
    private static final String TABLE_LESSON_EXAM = "tbl_lesson_exam";
    private static final String COLUMN_ID_LESSON = "id";
    private static final String COLUMN_NAME = "name";
    private DBHelper mDBHelper;

    public ExamLessonDatabaseHelper(DBHelper DBHelper) {
        mDBHelper = DBHelper;
    }

    @Override
    public void getExamLessons(Callback<List<ExamLesson>> callback) {
        try {
            mDBHelper.openDatabase();
        } catch (IOException e) {
            callback.onGetDataFail(e);
            return;
        }
        List<ExamLesson> examLessons = new ArrayList<>();
        SQLiteDatabase database = mDBHelper.getReadableDatabase();
        Cursor cursorLesson = database.query(TABLE_LESSON_EXAM,
                new String[]{COLUMN_ID_LESSON, COLUMN_NAME},
                null, null, null, null, null);
        cursorLesson.moveToFirst();
        do {
            ExamLesson examLesson;
            examLesson = new ExamLesson(cursorLesson.getInt(
                    cursorLesson.getColumnIndex(COLUMN_ID_LESSON)),
                    cursorLesson.getString(cursorLesson.getColumnIndex(COLUMN_NAME)));
            examLessons.add(examLesson);
        } while (cursorLesson.moveToNext());
        callback.onGetDataSuccess(examLessons);
        mDBHelper.close();
    }
}
