package com.example.priyanshu.newsreport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priyanshu on 12-Feb-17.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private static final String LOG_TAGS=NewsLoader.class.getName();
    private String mURL;

    public NewsLoader(Context context, String mURL) {
        super(context);
        this.mURL = mURL;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<News> loadInBackground() {
        if(mURL==null)
            return null;
        List<News> news=QueryUtils.fetchNewsData(mURL);
        return news;
    }
}
