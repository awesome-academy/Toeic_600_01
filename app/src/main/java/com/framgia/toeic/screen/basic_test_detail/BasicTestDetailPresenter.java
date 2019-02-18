package com.framgia.toeic.screen.basic_test_detail;

import android.media.MediaPlayer;

import com.framgia.toeic.data.model.BasicTest;
import com.framgia.toeic.data.model.BasicTestLesson;
import com.framgia.toeic.data.repository.BasicTestRepository;
import com.framgia.toeic.data.source.Callback;
import com.framgia.toeic.screen.base.MediaPlayerManager;
import com.framgia.toeic.screen.base.RatingCaculator;
import com.framgia.toeic.screen.base.RatingResult;

import java.util.List;

public class BasicTestDetailPresenter extends RatingCaculator implements BasicTestDetailContract.Presenter {
    private static final int NUMBER_MEDIA_EACH_LESSON = 4;
    private BasicTestDetailContract.View mView;
    private BasicTestRepository mRepository;

    public BasicTestDetailPresenter(BasicTestDetailContract.View view, BasicTestRepository repository) {
        mView = view;
        mRepository = repository;
    }

    public int calculateMark(List<BasicTest> basicTests) {
        int mark = 0;
        for (BasicTest basicTest : basicTests) {
            if (basicTest.isCheckAnswerA() && basicTest.getAnwserA().equals(basicTest.getResult())) {
                mark++;
                break;
            }

            if (basicTest.isCheckAnswerB() && basicTest.getAnwserB().equals(basicTest.getResult())) {
                mark++;
                break;
            }

            if (basicTest.isCheckAnswerC() && basicTest.getAnwserC().equals(basicTest.getResult())) {
                mark++;
                break;
            }

            if (basicTest.isCheckAnswerD() && basicTest.getAnwserD().equals(basicTest.getResult())) {
                mark++;
                break;
            }
        }
        return mark;
    }

    @Override
    public void checkAnswer(List<BasicTest> basicTests) {
        int mark = calculateMark(basicTests);
        @RatingResult int rating = rating(mark, basicTests.size());
        mView.showDialogResult(mark, rating);
    }

    @Override
    public void checkListening() {
        if (MediaPlayerManager.getInstance(new MediaPlayer()).isPlaying()) {
            mView.pauseAudio();
            return;
        }
        mView.playAudio();
    }

    @Override
    public void updateLesson(BasicTestLesson lesson, final int mark) {
        if (mark > lesson.getRating()) {
            mRepository.updateTestLesson(lesson, mark, new Callback<BasicTestLesson>() {
                @Override
                public void onGetDataSuccess(BasicTestLesson basicTestLesson) {
                    basicTestLesson.setRating(mark);
                }

                @Override
                public void onGetDataFail(Exception error) {
                    mView.showError(error);
                }
            });
        }
    }

    @Override
    public void changeMediaFile(int part, int idLesson) {
        if (part <= NUMBER_MEDIA_EACH_LESSON) {
            int id = (idLesson - 1) * NUMBER_MEDIA_EACH_LESSON + part;
            mView.changeMedia(id);
            return;
        }
        mView.hideSeekBar();
    }
}
