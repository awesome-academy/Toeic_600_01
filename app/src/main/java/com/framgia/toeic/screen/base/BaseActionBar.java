package com.framgia.toeic.screen.base;

import android.graphics.drawable.ColorDrawable;

import com.framgia.toeic.R;

public abstract class BaseActionBar extends BaseActivity {
    public void initActionBar(String s) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(s);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor
                (R.color.material_teal_500)));
    }
}
