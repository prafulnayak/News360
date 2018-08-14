package org.sairaa.news360;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    public NewsLoader(Context newsFragment) {
        super(newsFragment);
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        List<News> newsList = new ArrayList<News>();
        newsList.add(new News("123","news",
                "ndtv","hello","120516","i am doing good","hhhhtyyo",
                "nkjnsdv","false","4584954","praful"));
        newsList.add(new News("123","news",
                "ndtv","hello","120516","i am doing good","hhhhtyyo",
                "nkjnsdv","false","4584954","praful"));
        newsList.add(new News("123","news",
                "ndtv","hello","120516","i am doing good","hhhhtyyo",
                "nkjnsdv","false","4584954","praful"));
        return newsList;
    }
}
