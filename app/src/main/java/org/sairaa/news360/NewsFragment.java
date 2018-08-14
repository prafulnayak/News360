package org.sairaa.news360;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.LoaderManager;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class NewsFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<List<News>> {
    private Context mContext;
    private ConstraintLayout constraintLayout;
    private ConstraintSet constraintSet1 = new ConstraintSet();
    private ConstraintSet constraintSet2 = new ConstraintSet();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<News> newsList;
//    private final String LOG_NEWS = getActivity().getClass().getName();

    public NewsFragment(Context mContext){
    this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_news_fragment, container, false);

        newsList = new ArrayList<News>();
        addDemoNewsDetails();
        recyclerView = rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    private void addDemoNewsDetails() {
//        newsList.add(new News("123","news",
//                "ndtv","hello","120516","i am doing good","hhhhtyyo",
//                "nkjnsdv","false","4584954","praful"));
//        newsList.add(new News("123","news",
//                "ndtv","hello","120516","i am doing good","hhhhtyyo",
//                "nkjnsdv","false","4584954","praful"));
//        newsList.add(new News("123","news",
//                "ndtv","hello","120516","i am doing good","hhhhtyyo",
//                "nkjnsdv","false","4584954","praful"));
        getLoaderManager().initLoader(0,null,NewsFragment.this).forceLoad();
    }

    @Override
    public void onClick(View view) {
//        TransitionManager.beginDelayedTransition(constraintLayout);
//        constraintSet2.applyTo(constraintLayout);
        Log.e("Hello","Hello");
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.i("l1","onCreateLoader");
        return new NewsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        Log.i("l2","onLoadFinished");
        newsList.addAll(data);
        adapter = new RecyclerAdapter(newsList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        Log.i("l3","onLoaderReset");
    }
}
