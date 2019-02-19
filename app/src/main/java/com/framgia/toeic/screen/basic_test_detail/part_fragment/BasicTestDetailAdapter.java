package com.framgia.toeic.screen.basic_test_detail.part_fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.BasicTest;
import com.framgia.toeic.data.model.Exam;
import com.framgia.toeic.screen.exam_detail.ExamAdapter;

import java.util.List;

public class BasicTestDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_PART_2 = 2;
    private static final int VIEW_TYPE_PART_1 = 1;
    private static final int VIEW_TYPE_PART_3 = 3;
    private static final String EXTENSION_PNG = ".PNG";
    private Context mContext;
    private List<BasicTest> mBasicTests;
    private boolean isChecked;
    private int mPart;

    public BasicTestDetailAdapter(Context context, List<BasicTest> basicTests, boolean isChecked, int part) {
        mContext = context;
        mBasicTests = basicTests;
        this.isChecked = isChecked;
        mPart = part;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int getItemViewType(int position) {
        if (mPart == 1) {
            return VIEW_TYPE_PART_1;
        }

        if (mPart == 2) {
            return VIEW_TYPE_PART_2;
        }

        return VIEW_TYPE_PART_3;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_PART_1:
                return new BasicTestDetailAdapter.ViewHolderType1(
                        LayoutInflater.from(mContext).inflate(R.layout.item_exam_question_type_1,
                                viewGroup, false));
            case VIEW_TYPE_PART_2:
                return new BasicTestDetailAdapter.ViewHolderType2(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_exam_question_type_2, viewGroup, false));
            default:
                return new BasicTestDetailAdapter.ViewHolderType3(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_exam_question_type_3, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()) {
            case VIEW_TYPE_PART_1:
                ExamAdapter.ViewHolderPart1 viewHolderType1 = (ExamAdapter.ViewHolderPart1) viewHolder;
                viewHolderType1.bindData(mBasicTests.get(i), mContext, isChecked, EXTENSION_PNG);
                break;
            case VIEW_TYPE_PART_2:
                ExamAdapter.ViewHolderPart2 viewHolderType2 = (ExamAdapter.ViewHolderPart2) viewHolder;
                viewHolderType2.bindData(mBasicTests.get(i), isChecked);
                break;
            case VIEW_TYPE_PART_3:
                ExamAdapter.ViewHolderPart3 viewHolderType3 = (ExamAdapter.ViewHolderPart3) viewHolder;
                viewHolderType3.bindData(mBasicTests.get(i), isChecked, mContext, EXTENSION_PNG);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mBasicTests.size();
    }

    public static class ViewHolderType1 extends ExamAdapter.ViewHolderPart1 {

        public ViewHolderType1(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void showAnswer(Exam exam) {
            super.showAnswer(exam);
        }
    }

    public static class ViewHolderType2 extends ExamAdapter.ViewHolderPart2 {

        public ViewHolderType2(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class ViewHolderType3 extends ExamAdapter.ViewHolderPart3 {

        public ViewHolderType3(@NonNull View itemView) {
            super(itemView);
        }
    }
}
