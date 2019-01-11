package com.framgia.toeic.screen.vocabulary_detail.fragment_vocabulary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Vocabulary;
import com.framgia.toeic.screen.base.BaseFragment;

public class VocabularyFragment extends BaseFragment
        implements RadioGroup.OnCheckedChangeListener, VocabularyDetailContract.View {
    static final String ARGUMENT_QUESTION = "ARGUMENT_QUESTION";
    static final String ARGUMENT_NUMBER_QUESTION = "ARGUMENT_NUMBER_QUESTION";
    private TextView mTextViewNumberQuestion;
    private TextView mTextViewContentQuestion;
    private RadioButton mRadioAnswerA;
    private RadioButton mRadioAnswerB;
    private RadioButton mRadioAnswerC;
    private RadioGroup mRadioGroup;
    private VocabularyDetailContract.Presenter mPresenter;
    private Vocabulary mVocabulary;
    private int mCurrentQuestionPosition;
    private OnAnswerChangeListener mCallback;

    public static Fragment newInstance(Vocabulary vocabulary, int numberQuestion) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_QUESTION, vocabulary);
        bundle.putInt(ARGUMENT_NUMBER_QUESTION, numberQuestion);
        Fragment fragment = new VocabularyFragment();
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
    public int getLayoutResource() {
        return R.layout.fragment_vocabulary;
    }

    @Override
    public void initComponent(View view) {
        mTextViewNumberQuestion = view.findViewById(R.id.text_number_question);
        mTextViewContentQuestion = view.findViewById(R.id.text_content_question);
        mRadioAnswerA = view.findViewById(R.id.radio_a);
        mRadioAnswerB = view.findViewById(R.id.radio_b);
        mRadioAnswerC = view.findViewById(R.id.radio_c);
        mRadioGroup = view.findViewById(R.id.radio_group);
        mPresenter = new VocabularyPresenter(this);
    }

    @Override
    public void initData() {
        mVocabulary = getArguments().getParcelable(ARGUMENT_QUESTION);
        mCurrentQuestionPosition = getArguments().getInt(ARGUMENT_NUMBER_QUESTION);
        int question = mCurrentQuestionPosition + 1;
        mTextViewNumberQuestion.setText(getResources().getString(R.string.title_question) + (question));
        StringBuilder builder = new StringBuilder();
        builder.append(mVocabulary.getQuestion()).append(getString(R.string.title_vocabulary_question));
        mTextViewContentQuestion.setText(builder.toString());
        mRadioAnswerA.setText(mVocabulary.getAnwserA());
        mRadioAnswerB.setText(mVocabulary.getAnwserB());
        mRadioAnswerC.setText(mVocabulary.getAnwserC());
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnswerChangeListener) {
            mCallback = (OnAnswerChangeListener) context;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String result = null;
        switch (checkedId) {
            case R.id.radio_a:
                result = mRadioAnswerA.getText().toString();
                break;
            case R.id.radio_b:
                result = mRadioAnswerB.getText().toString();
                break;
            case R.id.radio_c:
                result = mRadioAnswerC.getText().toString();
                break;
        }
        mPresenter.checkAnwser(result, mVocabulary.getResult());
    }

    @Override
    public void onRightAnswer() {
        mVocabulary.setSelected(true);
        if (mCallback != null) {
            mCallback.onChanged(mVocabulary);
        }
    }

    @Override
    public void onWrongAnswer() {
        mVocabulary.setSelected(false);
        if (mCallback != null) {
            mCallback.onChanged(mVocabulary);
        }
    }

    public interface OnAnswerChangeListener {
        void onChanged(Vocabulary vocabulary);
    }
}
