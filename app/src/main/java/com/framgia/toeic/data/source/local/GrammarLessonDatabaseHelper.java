package com.framgia.toeic.data.source.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.framgia.toeic.data.GrammarLessonDataSource;
import com.framgia.toeic.data.model.Grammar;
import com.framgia.toeic.data.model.GrammarLesson;
import com.framgia.toeic.data.source.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrammarLessonDatabaseHelper implements GrammarLessonDataSource.Local {
    private static final String TABLE_LESSON_GRAMMAR = "tbl_lesson_grammar";
    private static final String COLUMN_ID_LESSON = "id";
    private static final String COLUMN_NAME = "name";
    private static final String TABLE_GRAMMAR = "tbl_grammar";
    private static final String COLUMN_ID_GRAMMAR = "id";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_RESULT = "result";
    private static final String COLUMN_ANSWER_A = "answer_a";
    private static final String COLUMN_ANSWER_B = "answer_b";
    private static final String COLUMN_ANSWER_C = "answer_c";
    private static final String COLUMN_ANSWER_D = "answer_d";
    private static final String COLUMN_UNIT = "unit";
    private static final String COLUMN_MAX_MARK = "max_mark";
    private DBHelper mDBHelper;

    public GrammarLessonDatabaseHelper(DBHelper DBHelper) {
        mDBHelper = DBHelper;
    }

    @Override
    public void getGrammarLessons(Callback<List<GrammarLesson>> callback) {
        try {
            mDBHelper.openDatabase();
        } catch (IOException e) {
            callback.onGetDataFail(e);
            return;
        }
        List<GrammarLesson> grammarLessons = new ArrayList<>();
        SQLiteDatabase database = mDBHelper.getReadableDatabase();
        Cursor cursorLesson = database.query(TABLE_LESSON_GRAMMAR, null,null, null, null, null, null);
        cursorLesson.moveToFirst();
        do {
            GrammarLesson grammarLesson;
            grammarLesson = new GrammarLesson(cursorLesson.getInt(
                    cursorLesson.getColumnIndex(COLUMN_ID_LESSON)),
                    cursorLesson.getString(cursorLesson.getColumnIndex(COLUMN_NAME)),
                    cursorLesson.getInt(cursorLesson.getColumnIndex(COLUMN_MAX_MARK)));
            grammarLessons.add(grammarLesson);
        } while (cursorLesson.moveToNext());
        callback.onGetDataSuccess(grammarLessons);
        mDBHelper.close();
    }



    public void getGrammars(GrammarLesson grammarLesson, Callback<List<Grammar>> callback) {
        GrammarLesson lesson = grammarLesson;
        List<Grammar> grammars = new ArrayList<>();
        try {
            mDBHelper.openDatabase();
        } catch (IOException e) {
            callback.onGetDataFail(e);
            return;
        }
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursorGrammar = db.query(TABLE_GRAMMAR,
                null, COLUMN_UNIT + "=?",
                new String[]{lesson.getId() + ""},
                null, null, null, null);
        cursorGrammar.moveToFirst();
        do {
            Grammar grammar = new Grammar.GrammarBuilder()
                    .setQuestion(cursorGrammar.getString(cursorGrammar.getColumnIndex(COLUMN_QUESTION)))
                    .setResult(cursorGrammar.getString(cursorGrammar.getColumnIndex(COLUMN_RESULT)))
                    .setAnwserA(cursorGrammar.getString(cursorGrammar.getColumnIndex(COLUMN_ANSWER_A)))
                    .setAnwserB(cursorGrammar.getString(cursorGrammar.getColumnIndex(COLUMN_ANSWER_B)))
                    .setAnwserC(cursorGrammar.getString(cursorGrammar.getColumnIndex(COLUMN_ANSWER_C)))
                    .setAnwserD(cursorGrammar.getString(cursorGrammar.getColumnIndex(COLUMN_ANSWER_D)))
                    .setId(cursorGrammar.getInt(cursorGrammar.getColumnIndex(COLUMN_ID_GRAMMAR)))
                    .setIsSelected(false).build();
            grammars.add(grammar);
        } while (cursorGrammar.moveToNext());
        callback.onGetDataSuccess(grammars);
        mDBHelper.close();
    }

    @Override
    public void updateGrammar(GrammarLesson examLesson, int mark, Callback<GrammarLesson> callback) {
        try {
            mDBHelper.openDatabase();
        } catch (IOException e) {
            callback.onGetDataFail(e);
            return;
        }
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MAX_MARK, mark);
        db.update(TABLE_LESSON_GRAMMAR, contentValues, COLUMN_ID_LESSON + "=?", new String[]{examLesson.getId() + ""});
        callback.onGetDataSuccess(examLesson);
        db.close();
    }
}
