package com.framgia.toeic.data.model;

public class Vocabulary extends Question {
    private int mId;
    private boolean mCheck;

    public Vocabulary() {
    }

    public Vocabulary(VocabularyBuilder vocabularyBuilder) {
        super(vocabularyBuilder);
        mId = vocabularyBuilder.mId;
        mCheck = vocabularyBuilder.mCheck;
    }

    public static class VocabularyBuilder extends QuestionBuilder {
        private int mId;
        private boolean mCheck;

        @Override
        public VocabularyBuilder setQuestion(String question) {
            super.setQuestion(question);
            return this;
        }

        @Override
        public VocabularyBuilder setResult(String result) {
            super.setResult(result);
            return this;
        }

        @Override
        public VocabularyBuilder setAnwserA(String anwserA) {
            super.setAnwserA(anwserA);
            return this;
        }

        @Override
        public VocabularyBuilder setAnwserB(String anwserB) {
            super.setAnwserB(anwserB);
            return this;
        }

        @Override
        public VocabularyBuilder setAnwserC(String anwserC) {
            super.setAnwserC(anwserC);
            return this;
        }

        @Override
        public QuestionBuilder setAnwserD(String anwserD) {
            super.setAnwserD(anwserD);
            return this;
        }

        public VocabularyBuilder setId(int id) {
            mId = id;
            return this;
        }

        public VocabularyBuilder setCheck(boolean check) {
            mCheck = check;
            return this;
        }

        public int getId() {
            return mId;
        }

        public boolean isCheck() {
            return mCheck;
        }

        public Vocabulary build() {
            return new Vocabulary(this);
        }
    }
}
