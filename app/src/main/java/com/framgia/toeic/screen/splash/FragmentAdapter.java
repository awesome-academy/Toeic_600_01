package com.framgia.toeic.screen.splash;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.toeic.R;

public class FragmentAdapter extends PagerAdapter {
    private static final int[] IMAGES = {
            R.drawable.bg_intro_5,
            R.drawable.bg_intro_6,
            R.drawable.bg_intro_7
    };

    private static final int[] TITLES = {
            R.string.title_intro_1,
            R.string.title_intro_2,
            R.string.title_intro_3
    };

    private static final int[] DESCRIPTIONS = {
            R.string.des_intro_1,
            R.string.des_intro_2,
            R.string.des_intro_3
    };

    private static final int[] BACKGROUND_COLORS = {
            R.color.material_red_400,
            R.color.material_brown_600,
            R.color.material_cyan_500
    };

    private static final int[] BUTTONS = {
            R.string.button_intro_1,
            R.string.button_intro_1,
            R.string.button_intro_2
    };
    private Context mContext;
    private OnPageChangeListener mOnNext;
    private ConstraintLayout mLayoutIntro;
    private ImageView mImageIntro;
    private TextView mTitleIntro;
    private TextView mDesIntro;
    private Button mButtonIntro;

    public FragmentAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_intro, container, false);
        mLayoutIntro = view.findViewById(R.id.layout);
        mImageIntro = view.findViewById(R.id.image_intro);
        mTitleIntro = view.findViewById(R.id.text_title_intro);
        mDesIntro = view.findViewById(R.id.text_des_intro);
        mButtonIntro = view.findViewById(R.id.button_intro);
        addItem(position);
        container.addView(view);
        return view;
    }
    public void addItem(final int position){
        mOnNext = (OnPageChangeListener) mContext;
        mButtonIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnNext.setPosision(position);
            }
        });
        mLayoutIntro.setBackgroundResource(BACKGROUND_COLORS[position]);
        mImageIntro.setImageResource(IMAGES[position]);
        mTitleIntro.setText(TITLES[position]);
        mDesIntro.setText(DESCRIPTIONS[position]);
        mButtonIntro.setText(BUTTONS[position]);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnPageChangeListener {
        void setPosision(int i);
    }
}
