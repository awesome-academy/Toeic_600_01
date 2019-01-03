package com.framgia.toeic.data.model;

import java.util.List;

public class VocabularyLesson {
    private int mId;
    private String mName;
    private List<Vocabulary> mVocabularies;

    public VocabularyLesson() {
    }

    public VocabularyLesson(int id, String name, List<Vocabulary> vocabularies) {
        mId = id;
        mName = name;
        mVocabularies = vocabularies;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Vocabulary> getVocabularies() {
        return mVocabularies;
    }

    public void setVocabularies(List<Vocabulary> vocabularies) {
        mVocabularies = vocabularies;
    }
}
