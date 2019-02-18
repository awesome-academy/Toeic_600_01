package com.framgia.toeic.screen.user;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.Mark;

import java.util.List;

public class ProgressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NUMBER_MODULES = 4;
    private static final String PERCENT = "%";
    private List<Integer> mMaxMarks;
    private List<Mark> mMarks;

    public ProgressAdapter(List<Integer> values, List<Mark> marks) {
        mMaxMarks = values;
        mMarks = marks;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_progress, viewGroup, false);
        return new ProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ProgressViewHolder progressViewHolder = (ProgressViewHolder) viewHolder;
        progressViewHolder.bindData(mMaxMarks.get(i), mMarks.get(i));
    }

    @Override
    public int getItemCount() {
        return NUMBER_MODULES;
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder{
        private static final int NUMBER_CALCULATE_PERCENT = 100
                ;
        private TextView mTextViewTitle;
        private TextView mTextViewPercent;
        private ProgressBar mProgressBar;

        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.text_title);
            mProgressBar = itemView.findViewById(R.id.progressBar);
            mTextViewPercent = itemView.findViewById(R.id.text_percent);
        }
        public void bindData(int maxMark, Mark mark){
            mTextViewTitle.setText(mark.getName());
            mProgressBar.setMax(maxMark);
            mProgressBar.setProgress(mark.getMark());
            mTextViewPercent.setText(calculatePercentPerfect(maxMark, mark) + PERCENT);
        }

        public int calculatePercentPerfect(int maxMark, Mark mark){
            return mark.getMark() * NUMBER_CALCULATE_PERCENT / maxMark;
        }
    }
}
