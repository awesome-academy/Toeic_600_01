package com.framgia.toeic.data.model;

public class User {
    private String mName;
    private String mTarget;

    public User(String name, String target) {
        mName = name;
        mTarget = target;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTarget() {
        return mTarget;
    }

    public void setTarget(String target) {
        mTarget = target;
    }
}
