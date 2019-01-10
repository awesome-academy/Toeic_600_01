package com.framgia.toeic.screen.grammar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.toeic.R;
import com.framgia.toeic.data.model.GrammarLesson;

import java.util.List;

public class GrammarLessonAdapter extends RecyclerView.Adapter<GrammarLessonAdapter.Viewholder> {
    private Context mContext;
    private List<GrammarLesson> mLessons;

    public GrammarLessonAdapter(Context context, List<GrammarLesson> lessons) {
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

        public void bindData(final GrammarLesson grammarLesson) {
            if (grammarLesson == null) {
                return;
            }
            mTextTitle.setText(grammarLesson.getName());
        }
    }
}
