package com.framgia.toeic.screen.base;


import android.support.annotation.IntDef;

import static com.framgia.toeic.screen.base.IDModules.ID_BASIC_TEST;
import static com.framgia.toeic.screen.base.IDModules.ID_EXAM;
import static com.framgia.toeic.screen.base.IDModules.ID_GRAMMAR;
import static com.framgia.toeic.screen.base.IDModules.ID_VOCABULARY;

@IntDef({ID_VOCABULARY, ID_GRAMMAR, ID_BASIC_TEST, ID_EXAM})
public @interface IDModules {
    int ID_VOCABULARY = 1;
    int ID_GRAMMAR = 2;
    int ID_BASIC_TEST = 3;
    int ID_EXAM = 4;
}
