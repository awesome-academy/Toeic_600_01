package com.framgia.toeic.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Exam extends Question implements Parcelable {
    private int mId;
    private boolean mIsSeclected;
    private String mUrlImage;
    private String mUrlAudio;

    public Exam() {
    }

    public Exam(Exam.BasicTestBuilder grammarBuilder) {
        super(grammarBuilder);
        mId = grammarBuilder.mId;
        mIsSeclected = grammarBuilder.mIsSeclected;
        mUrlImage = grammarBuilder.mUrlImage;
        mUrlAudio = grammarBuilder.mUrlAudio;
    }

    protected Exam(Parcel in) {
        super(in);
        mId = in.readInt();
        mIsSeclected = in.readByte() != 0;
        mUrlImage = in.readString();
        mUrlAudio = in.readString();
    }

    public static final Creator<Exam> CREATOR = new Creator<Exam>() {
        @Override
        public Exam createFromParcel(Parcel in) {
            return new Exam(in);
        }

        @Override
        public Exam[] newArray(int size) {
            return new Exam[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(mId);
        parcel.writeByte((byte) (mIsSeclected ? 1 : 0));
        parcel.writeString(mUrlImage);
        parcel.writeString(mUrlAudio);
    }

    public static class BasicTestBuilder extends QuestionBuilder {
        private int mId;
        private boolean mIsSeclected;
        private String mUrlImage;
        private String mUrlAudio;

        @Override
        public Exam.BasicTestBuilder setQuestion(String question) {
            super.setQuestion(question);
            return this;
        }

        @Override
        public Exam.BasicTestBuilder setResult(String result) {
            super.setResult(result);
            return this;
        }

        @Override
        public Exam.BasicTestBuilder setAnwserA(String anwserA) {
            super.setAnwserA(anwserA);
            return this;
        }

        @Override
        public Exam.BasicTestBuilder setAnwserB(String anwserB) {
            super.setAnwserB(anwserB);
            return this;
        }

        @Override
        public Exam.BasicTestBuilder setAnwserC(String anwserC) {
            super.setAnwserC(anwserC);
            return this;
        }

        @Override
        public QuestionBuilder setAnwserD(String anwserD) {
            super.setAnwserD(anwserD);
            return this;
        }

        public Exam.BasicTestBuilder setId(int id) {
            mId = id;
            return this;
        }

        public Exam.BasicTestBuilder setCheck(boolean check) {
            mIsSeclected = check;
            return this;
        }

        public Exam.BasicTestBuilder setUrlImage(String urlImage) {
            mUrlImage = urlImage;
            return this;
        }

        public Exam.BasicTestBuilder setUrlAudio(String urlAudio) {
            mUrlAudio = urlAudio;
            return this;
        }

        public int getId() {
            return mId;
        }

        public boolean isCheck() {
            return mIsSeclected;
        }

        public String getUrlImage() {
            return mUrlImage;
        }

        public String getUrlAudio() {
            return mUrlAudio;
        }

        public Exam build() {
            return new Exam(this);
        }
    }
}
