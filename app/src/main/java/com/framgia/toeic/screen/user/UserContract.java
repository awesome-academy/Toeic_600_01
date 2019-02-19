package com.framgia.toeic.screen.user;

import com.framgia.toeic.data.model.Mark;
import com.framgia.toeic.data.model.User;

import java.util.List;

public interface UserContract {
    interface View{
        void showError(Exception e);

        void showProgress(List<Integer> values, List<Mark> marks);

        void showUser(User user);
    }

    interface Presenter{
        void getMarks();

        void getUser();

        void updateUser(User user);
    }
}
