package com.framgia.toeic.screen.main;

import android.util.Log;

import com.framgia.toeic.data.model.Mark;
import com.framgia.toeic.data.model.User;
import com.framgia.toeic.data.repository.UserRepository;
import com.framgia.toeic.data.source.Callback;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private UserRepository mUserRepository;
    private MainContract.View mView;

    public MainPresenter(UserRepository repository,
                         MainContract.View view) {
        mUserRepository = repository;
        mView = view;
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
}
