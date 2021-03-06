package com.framgia.toeic.screen.exam_detail;

import android.media.MediaPlayer;

import com.framgia.toeic.data.model.Exam;
import com.framgia.toeic.data.model.ExamLesson;
import com.framgia.toeic.data.repository.ExamLessonRepository;
import com.framgia.toeic.data.source.Callback;
import com.framgia.toeic.screen.base.MediaPlayerManager;
import com.framgia.toeic.screen.base.RatingCaculator;
import com.framgia.toeic.screen.base.RatingResult;

import java.util.List;

public class ExamDetailPresenter extends RatingCaculator implements ExamDetailContract.Presenter {
    private ExamDetailContract.View mView;
    private ExamLessonRepository mRepository;

    public ExamDetailPresenter(ExamDetailContract.View view, ExamLessonRepository repository) {
        mView = view;
        mRepository = repository;
    }

    public int calculateMark(List<Exam> exams) {
        int mark = 0;
        for (Exam exam : exams) {
            if (exam.isCheckAnswerA() && exam.getAnwserA().equals(exam.getResult())) {
                mark++;
            }

            if (exam.isCheckAnswerB() && exam.getAnwserB().equals(exam.getResult())) {
                mark++;
            }

            if (exam.isCheckAnswerC() && exam.getAnwserC().equals(exam.getResult())) {
                mark++;
            }

            if (exam.isCheckAnswerD() && exam.getAnwserD().equals(exam.getResult())) {
                mark++;
            }
        }
        return mark;
    }

    @Override
    public void checkAnswer(List<Exam> exams) {
        int mark = calculateMark(exams);
        @RatingResult int rating = rating(mark, exams.size());
        mView.showDialogResult(mark, rating);
    }

    @Override
    public void checkListening() {
        if (MediaPlayerManager.getInstance(new MediaPlayer()).isPlaying()) {
            mView.pauseMedia();
            return;
        }
        mView.listenMedia();
    }

    @Override
    public void updateLesson(ExamLesson examLesson, int mark) {
        if (mark > examLesson.getRating()){
            mRepository.updateExam(examLesson, mark, new Callback<ExamLesson>() {
                @Override
                public void onGetDataSuccess(ExamLesson examLesson) {
                    
                }

                @Override
                public void onGetDataFail(Exception error) {
                    mView.showError(error);
                }
            });
            examLesson.setRating(mark);
        }
    }
}
