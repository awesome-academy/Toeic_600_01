package com.framgia.toeic.screen.basic_test_detail;

import com.framgia.toeic.data.model.BasicTest;
import com.framgia.toeic.data.model.BasicTestLesson;

import java.util.List;

public interface BasicTestDetailContract {
    interface View {
        void showDialogResult(int mark, int rating);

        void playAudio();

        void pauseAudio();

        void hideSeekBar();

        void changeAudio(int id);

        void showError(Exception e);
    }

    interface Presenter {
        void checkAnswer(List<BasicTest> basicTests);

        void changeAudioStatus();

        void updateLesson(BasicTestLesson lesson, int mark);

        void changeAudioFile(int part, int idLesson);

    }
}
