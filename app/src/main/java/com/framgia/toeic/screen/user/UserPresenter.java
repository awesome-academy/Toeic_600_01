package com.framgia.toeic.screen.user;

import com.framgia.toeic.data.model.Mark;
import com.framgia.toeic.data.model.User;
import com.framgia.toeic.data.repository.MarkRepository;
import com.framgia.toeic.data.repository.UserRepository;
import com.framgia.toeic.data.source.Callback;

import java.util.List;

public class UserPresenter implements UserContract.Presenter {
    private UserRepository mUserRepository;
    private MarkRepository mMarkRepository;
    private UserContract.View mView;

    public UserPresenter(UserRepository userRepository, MarkRepository markRepository, UserContract.View view) {
        mUserRepository = userRepository;
        mMarkRepository = markRepository;
        mView = view;
    }

    @Override
    public void getMarks() {
        mMarkRepository.getMaxMark(new Callback<List<Integer>>() {
            @Override
            public void onGetDataSuccess(final List<Integer> maxMarks) {
                mMarkRepository.getMarks(new Callback<List<Mark>>() {
                    @Override
                    public void onGetDataSuccess(List<Mark> marks) {
                        mView.showProgress(maxMarks, marks);
                    }

                    @Override
                    public void onGetDataFail(Exception error) {
                        mView.showError(error);
                    }
                });
            }

            @Override
            public void onGetDataFail(Exception error) {
                mView.showError(error);
            }
        });
    }

    @Override
    public void getUser() {
        mUserRepository.getUser(new Callback<User>() {
            @Override
            public void onGetDataSuccess(User user) {
                mView.showUser(user);
            }

            @Override
            public void onGetDataFail(Exception error) {
                mView.showError(error);
            }
        });
    }

    @Override
    public void updateUser(User user) {
        mUserRepository.saveUser(user);
    }
}
