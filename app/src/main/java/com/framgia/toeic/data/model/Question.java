package com.framgia.toeic.data.model;

public class Question {
    protected String mQuestion;
    protected String mResult;
    protected String mAnwserA;
    protected String mAnwserB;
    protected String mAnwserC;
    protected String mAnwserD;

    public Question() {
    }

    public Question(QuestionBuilder questionBuilder) {
        mQuestion = questionBuilder.mQuestion;
        mResult = questionBuilder.mResult;
        mAnwserA = questionBuilder.mAnwserA;
        mAnwserB = questionBuilder.mAnwserB;
        mAnwserC = questionBuilder.mAnwserC;
        mAnwserD = questionBuilder.mAnwserD;
    }

    public static class QuestionBuilder {
        private String mQuestion;
        private String mResult;
        private String mAnwserA;
        private String mAnwserB;
        private String mAnwserC;
        private String mAnwserD;

        public QuestionBuilder() {
        }

        public QuestionBuilder setQuestion(String question) {
            mQuestion = question;
            return this;
        }

        public QuestionBuilder setResult(String result) {
            mResult = result;
            return this;
        }

        public QuestionBuilder setAnwserA(String anwserA) {
            mAnwserA = anwserA;
            return this;
        }

        public QuestionBuilder setAnwserB(String anwserB) {
            mAnwserB = anwserB;
            return this;
        }

        public QuestionBuilder setAnwserC(String anwserC) {
            mAnwserC = anwserC;
            return this;
        }

        public QuestionBuilder setAnwserD(String anwserD) {
            mAnwserD = anwserD;
            return this;
        }

        public String getQuestion() {
            return mQuestion;
        }

        public String getResult() {
            return mResult;
        }

        public String getAnwserA() {
            return mAnwserA;
        }

        public String getAnwserB() {
            return mAnwserB;
        }

        public String getAnwserC() {
            return mAnwserC;
        }

        public String getAnwserD() {
            return mAnwserD;
        }

        public Question build() {
            return new Question(this);
        }
    }
}
