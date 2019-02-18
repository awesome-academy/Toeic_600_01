package com.framgia.toeic.screen.basic_test;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.BasicTestLesson;
import com.framgia.toeic.screen.base.RatingCaculator;

import java.util.List;

public class BasicTestAdapter extends RecyclerView.Adapter<BasicTestAdapter.BasicTestViewHolder> {
    private List<BasicTestLesson> mBasicTestLessons;
    private BasicTestAdapter.OnItemClickListener mListener;

    public BasicTestAdapter(List<BasicTestLesson> basicTestLessons, BasicTestAdapter.OnItemClickListener listener) {
        mBasicTestLessons = basicTestLessons;
        mListener = listener;
    }

    @NonNull
    @Override
    public BasicTestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_basic_test, viewGroup, false);
        return new BasicTestViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BasicTestViewHolder basicTestViewHolder, int position) {
        basicTestViewHolder.bindData(mBasicTestLessons.get(position));
    }

    @Override
    public int getItemCount() {
        return mBasicTestLessons == null ? 0 : mBasicTestLessons.size();
    }

    static class BasicTestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextTitle;
        private CardView mCardView;
        private RatingBar mRatingBar;
        private BasicTestLesson mBasicTestLesson;
        private BasicTestAdapter.OnItemClickListener mListener;

        public BasicTestViewHolder(View itemView, BasicTestAdapter.OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
            mTextTitle = itemView.findViewById(R.id.text_name_basic);
            mCardView = itemView.findViewById(R.id.cardview_basic);
            mRatingBar = itemView.findViewById(R.id.rating_bar);
            mCardView.setOnClickListener(this);
        }

        public void bindData(final BasicTestLesson basicTestLesson) {
            if (basicTestLesson == null) {
                return;
            }
            mBasicTestLesson = basicTestLesson;
            mTextTitle.setText(basicTestLesson.getName());
            RatingCaculator ratingCaculator = new RatingCaculator();
            mRatingBar.setRating(ratingCaculator.rating(basicTestLesson.getRating(),
                    mBasicTestLesson.getBasicTests().size()));
        }

        @Override
        public void onClick(View view) {
            if (mListener == null) {
                return;
            }
            mListener.onClick(mBasicTestLesson);
        }
    }

    public interface OnItemClickListener {
        void onClick(BasicTestLesson basicTestLesson);
    }
}
