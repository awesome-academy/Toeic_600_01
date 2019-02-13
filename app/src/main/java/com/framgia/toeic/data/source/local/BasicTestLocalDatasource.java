package com.framgia.toeic.data.source.local;

import com.framgia.toeic.data.BasicTestDatasource;
import com.framgia.toeic.data.model.BasicTest;
import com.framgia.toeic.data.model.BasicTestLesson;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public class BasicTestLocalDatasource implements BasicTestDatasource.Local {
    private BasicTestDatabaseHelper mDBHelper;

    public BasicTestLocalDatasource(BasicTestDatabaseHelper DBHelper) {
        mDBHelper = DBHelper;
    }

    @Override
    public void getLessons(Callback<List<BasicTestLesson>> callback) {
        mDBHelper.getLessons(callback);
    }

    @Override
    public void getTests(BasicTestLesson basicTestLesson, Callback<List<BasicTest>> callback) {
        mDBHelper.getTests(basicTestLesson, callback);
    }

    @Override
    public void updateTestLesson(BasicTestLesson lesson, int mark, Callback<BasicTestLesson> callback) {
        mDBHelper.updateTestLesson(lesson, mark, callback);
    }
}
