package com.framgia.toeic.screen.base;


import android.support.annotation.IntDef;

import static com.framgia.toeic.screen.base.ModuleID.BASIC_TEST;
import static com.framgia.toeic.screen.base.ModuleID.EXAM;
import static com.framgia.toeic.screen.base.ModuleID.GRAMMAR;
import static com.framgia.toeic.screen.base.ModuleID.VOCABULARY;

@IntDef({VOCABULARY, GRAMMAR, BASIC_TEST, EXAM})
public @interface ModuleID {
    int VOCABULARY = 1;
    int GRAMMAR = 2;
    int BASIC_TEST = 3;
    int EXAM = 4;
}
