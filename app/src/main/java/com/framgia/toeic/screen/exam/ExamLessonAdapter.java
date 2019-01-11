package com.framgia.toeic.screen.exam;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.ExamLesson;

import java.util.List;

public class ExamLessonAdapter extends RecyclerView.Adapter<ExamLessonAdapter.Viewholder> {
    private Context mContext;
    private List<ExamLesson> mLessons;

    public ExamLessonAdapter(Context context, List<ExamLesson> lessons) {
        mContext = context;
        mLessons = lessons;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_grammar, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        viewholder.bindData(mLessons.get(i));
    }

    @Override
    public int getItemCount() {
        return mLessons != null ? mLessons.size() : 0;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextTitle;

        public Viewholder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_grammar);
            mTextTitle = itemView.findViewById(R.id.text_name_grammar);
        }

        public void bindData(final ExamLesson examLesson) {
            if (examLesson == null) {
                return;
            }
            mTextTitle.setText(examLesson.getName());
        }
    }
}
