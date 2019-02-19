package com.framgia.toeic.screen.main;

import com.framgia.toeic.data.model.Mark;
import com.framgia.toeic.data.model.User;

import java.util.List;

public interface MainContract {
    interface View {
        void showError(Exception error);

        void showUser(User user);
    }

    interface Presenter {
        void getUser();
    }
}
