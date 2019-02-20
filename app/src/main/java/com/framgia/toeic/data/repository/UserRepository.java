package com.framgia.toeic.data.repository;

import com.framgia.toeic.data.model.User;
import com.framgia.toeic.data.source.Callback;
import com.framgia.toeic.data.UserDataSource;
import com.framgia.toeic.data.source.local.UserLocalDataSource;

public class UserRepository implements UserDataSource.Local {
    private static UserRepository sRepository;
    private UserLocalDataSource mLocal;

    private UserRepository(UserLocalDataSource local) {
        mLocal = local;
    }

    public static UserRepository getInstance(UserLocalDataSource local){
        if (sRepository == null){
            sRepository = new UserRepository(local);
        }

        return sRepository;
    }
    @Override
    public void getUser(Callback<User> callback) {
        mLocal.getUser(callback);
    }

    @Override
    public void saveUser(User user) {
        mLocal.saveUser(user);
    }
}
