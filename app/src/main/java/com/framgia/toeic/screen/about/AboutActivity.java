package com.framgia.toeic.screen.about;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.webkit.WebView;

import com.framgia.toeic.R;
import com.framgia.toeic.screen.base.BaseActionBar;

public class AboutActivity extends BaseActionBar {
    private static final String URL = "file:///android_asset/about.html";
    private WebView mWebView;

    public static Intent getAboutActivity(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_about;
    }

    @Override
    protected void initComponent() {
        initActionBar(getResources().getString(R.string.action_about));
        mWebView = findViewById(R.id.web_view);
    }

    @Override
    protected void initData() {
        mWebView.loadUrl(URL);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
