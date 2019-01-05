package com.framgia.toeic.data.source.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.framgia.toeic.data.VocabularyLessonDataSource;
import com.framgia.toeic.data.model.VocabularyLesson;
import com.framgia.toeic.data.source.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VocabularyLessonLocalDataSource implements VocabularyLessonDataSource.Local {
    private static final String TABLE_LESSON_VOCABULARY = "tbl_lesson_vocabulary";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private DBHelper mDBHelper;

    public VocabularyLessonLocalDataSource(DBHelper dbHelper) {
        this.mDBHelper = dbHelper;
    }

    @Override
    public void getVocabularies(Callback<List<VocabularyLesson>> Callback) {
        try {
            mDBHelper.createDataBase();
        } catch (IOException e) {
            Callback.onGetDataFail(e);
        }
        ArrayList<VocabularyLesson> lessonVocabularys = new ArrayList<>();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LESSON_VOCABULARY, new String[]{COLUMN_ID, COLUMN_NAME},
                null, null, null, null, null);
        cursor.moveToFirst();
        do {
            VocabularyLesson vocabularyLesson;
            vocabularyLesson = new VocabularyLesson(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)), null);
            lessonVocabularys.add(vocabularyLesson);
        } while (cursor.moveToNext());
        Callback.onGetDataSuccess(lessonVocabularys);
    }
}
