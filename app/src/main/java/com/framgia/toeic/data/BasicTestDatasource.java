package com.framgia.toeic.data;

import com.framgia.toeic.data.model.BasicTest;
import com.framgia.toeic.data.model.BasicTestLesson;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public interface BasicTestDatasource {
    interface Local{
        void getLessons(Callback<List<BasicTestLesson>> callback);

        void getTests(BasicTestLesson basicTestLesson, Callback<List<BasicTest>> callback);

        void updateTestLesson(BasicTestLesson lesson, int mark, Callback<BasicTestLesson> callback);

    }

    interface Remote{

    }
}
