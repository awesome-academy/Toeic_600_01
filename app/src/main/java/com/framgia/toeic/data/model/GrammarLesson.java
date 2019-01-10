package com.framgia.toeic.data.model;

import android.os.Parcel;

import java.util.List;

public class GrammarLesson extends Lesson {
    private List<Grammar> mGrammars;

    public GrammarLesson() {
    }

    public GrammarLesson(int id, String name) {
        super(id, name);
    }

    public GrammarLesson(Parcel in) {
        super(in);
    }

    public List<Grammar> getGrammars() {
        return mGrammars;
    }

    public void setGrammars(List<Grammar> grammars) {
        mGrammars = grammars;
    }
}
