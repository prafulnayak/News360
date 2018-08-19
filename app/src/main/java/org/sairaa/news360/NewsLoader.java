package org.sairaa.news360;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private static final String LOG_NEWS_LOADER = NewsLoader.class.getName();
    private String url;
    private Context ctx;

    public NewsLoader(Context newsFragment, String guardianRequestUrl) {
        super(newsFragment);
        url = guardianRequestUrl;
        this.ctx = newsFragment;
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {

        Log.i(LOG_NEWS_LOADER,"loadInBackground");
        Log.i(LOG_NEWS_LOADER,""+url);
        List<News> newsList = new ArrayList<News>();

        newsList = new QueryUtil().QueryUtilsForNewtwork(url,ctx);

        return newsList;
    }
}
