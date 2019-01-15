package com.framgia.toeic.screen.grammar_test.fragment_grammar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Grammar;
import com.framgia.toeic.screen.base.BaseFragment;
import com.framgia.toeic.screen.base.DisplayAnswerListener;
import com.framgia.toeic.screen.base.QuestionFragment;

public class GrammarTestFragment extends QuestionFragment {
    static final String ARGUMENT_QUESTION = "ARGUMENT_QUESTION";
    static final String ARGUMENT_NUMBER_QUESTION = "ARGUMENT_NUMBER_QUESTION";
    private Grammar mGrammar;

    public static Fragment newInstance(Grammar grammar, int numberQuestion) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_QUESTION, grammar);
        bundle.putInt(ARGUMENT_NUMBER_QUESTION, numberQuestion);
        Fragment fragment = new GrammarTestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initComponent(View view) {
        super.initComponent(view);
    }

    @Override
    public void initData() {
        mGrammar = getArguments().getParcelable(ARGUMENT_QUESTION);
        mCurrentQuestionPosition = getArguments().getInt(ARGUMENT_NUMBER_QUESTION);
    }

    @Override
    public void showData() {
        int question = mCurrentQuestionPosition + 1;
        mTextViewNumberQuestion.setText(getResources().getString(R.string.title_question) + (question));
        mTextViewContentQuestion.setText(mGrammar.getQuestion());
        mRadioAnswerA.setText(mGrammar.getAnwserA());
        mRadioAnswerB.setText(mGrammar.getAnwserB());
        mRadioAnswerC.setText(mGrammar.getAnwserC());
        mRadioAnswerD.setText(mGrammar.getAnwserD());
    }
}
