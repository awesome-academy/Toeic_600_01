package com.framgia.toeic.data.model;

import android.os.Parcel;

import java.util.List;

public class ExamLesson extends Lesson {
    private List<Exam> mGrammars;

    public ExamLesson() {
    }

    public ExamLesson(int id, String name) {
        super(id, name);
    }

    public ExamLesson(Parcel in) {
        super(in);
    }

    public List<Exam> getGrammars() {
        return mGrammars;
    }

    public void setGrammars(List<Exam> grammars) {
        mGrammars = grammars;
    }
}

