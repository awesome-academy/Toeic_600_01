package com.framgia.toeic.data.source.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.framgia.toeic.data.GrammarLessonDataSource;
import com.framgia.toeic.data.model.GrammarLesson;
import com.framgia.toeic.data.source.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrammarLessonDatabaseHelper implements GrammarLessonDataSource.Local {
    private static final String TABLE_LESSON_GRAMMAR = "tbl_lesson_grammar";
    private static final String COLUMN_ID_LESSON = "id";
    private static final String COLUMN_NAME = "name";
    private DBHelper mDBHelper;

    public GrammarLessonDatabaseHelper(DBHelper DBHelper) {
        mDBHelper = DBHelper;
    }

    @Override
    public void getGrammarLessons(Callback<List<GrammarLesson>> callback) {
        try {
            mDBHelper.createDataBase();
        } catch (IOException e) {
            callback.onGetDataFail(e);
        }
        List<GrammarLesson> grammarLessons = new ArrayList<>();
        SQLiteDatabase database = mDBHelper.getReadableDatabase();
        Cursor cursorLesson = database.query(TABLE_LESSON_GRAMMAR,
                new String[]{COLUMN_ID_LESSON, COLUMN_NAME},
                null, null, null, null, null);
        cursorLesson.moveToFirst();
        do {
            GrammarLesson grammarLesson;
            grammarLesson = new GrammarLesson(cursorLesson.getInt(
                    cursorLesson.getColumnIndex(COLUMN_ID_LESSON)),
                    cursorLesson.getString(cursorLesson.getColumnIndex(COLUMN_NAME)));
            grammarLessons.add(grammarLesson);
        } while (cursorLesson.moveToNext());
        callback.onGetDataSuccess(grammarLessons);
        mDBHelper.close();
    }
}
