package com.framgia.toeic.data.source.local;

import android.content.SharedPreferences;
import android.util.Log;

import com.framgia.toeic.data.model.User;
import com.framgia.toeic.data.source.Callback;
import com.framgia.toeic.data.UserDataSource;

import static com.framgia.toeic.utils.SharePreferenceUtils.DEFAULT_NAME;
import static com.framgia.toeic.utils.SharePreferenceUtils.DEFAULT_TARGET;
import static com.framgia.toeic.utils.SharePreferenceUtils.NAME;
import static com.framgia.toeic.utils.SharePreferenceUtils.TARGET;

public class UserLocalDataSource implements UserDataSource.Local {
    private SharedPreferences mPreferences;

    public UserLocalDataSource(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    @Override
    public void getUser(Callback<User> callback) {
        String name = mPreferences.getString(NAME, DEFAULT_NAME);
        String target = mPreferences.getString(TARGET, DEFAULT_TARGET);
        User user = new User(name, target);
        callback.onGetDataSuccess(user);
    }

    @Override
    public void saveUser(User user) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(NAME, user.getName());
        editor.putString(TARGET, user.getTarget());
        editor.apply();
    }
}
