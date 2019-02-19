package com.framgia.toeic.data;

import com.framgia.toeic.data.model.User;
import com.framgia.toeic.data.source.Callback;

public interface UserDataSource {
    interface Local {
        void getUser(Callback<User> callback);

        void saveUser(User user);
    }

    interface Remote {
    }
}
