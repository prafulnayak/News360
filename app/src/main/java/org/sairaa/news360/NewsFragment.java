package org.sairaa.news360;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class NewsFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<List<News>> {
    public static final String LOG_NEWS_FRAGMENT = NewsFragment.class.getName();
    private Context mContext;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<News> newsList;
//  URL for news data from Guardian
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?api-key=08c7211d-6fb6-4f1e-a865-190be5274e9f&order-by=newest";
//  Constant value for the news loader ID.
    private static final int NEWS_LOADER_TOP100 = 11;
    private static final int NEWS_LOADER_SPORTS = 22;
    private static final int NEWS_LOADER_POLITICS = 33;
    private int typeFragment;
    private String fragmentS;
    private Uri.Builder uriBuilder;
    private CheckConnection checkConnection;
    private DialogAction dialogAction;
    private SharedPreferenceConfig sharedPreferenceConfig;
    private TextView emptyTextViewT;
    public NewsFragment(Context mContext, int fragmentPosition){
    this.mContext = mContext;
    this.typeFragment = fragmentPosition;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_news_fragment, container, false);
        sharedPreferenceConfig = new SharedPreferenceConfig(getActivity());

        newsList = new ArrayList<News>();
        checkConnection = new CheckConnection(getActivity());
        dialogAction = new DialogAction(getActivity());
        if(checkConnection.isConnected()){
            dialogAction.showDialog(getString(R.string.app_name),getString(R.string.fatch));
            if(typeFragment == Constant.top100){
                getLoaderManager().initLoader(NEWS_LOADER_TOP100,null,NewsFragment.this).forceLoad();
            }
            if(typeFragment == Constant.sports){
                getLoaderManager().initLoader(NEWS_LOADER_SPORTS,null,NewsFragment.this).forceLoad();
            }
            if(typeFragment == Constant.politics){
                getLoaderManager().initLoader(NEWS_LOADER_POLITICS,null,NewsFragment.this).forceLoad();
            }
        }
        recyclerView = rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
//        Log.i(LOG_NEWS_FRAGMENT,"onCreateLoader");
        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);

        // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
        uriBuilder = baseUri.buildUpon();
        // parse breaks apart the URI string that's passed into its parameter
        switch (id){
            case NEWS_LOADER_TOP100:
                uriBuilder.appendQueryParameter("show-fields", "thumbnail");
                uriBuilder.appendQueryParameter("show-tags", "contributor");
                uriBuilder.appendQueryParameter("page-size", "15");
                Log.i(LOG_NEWS_FRAGMENT,"case ID : "+NEWS_LOADER_TOP100);
                break;
            case NEWS_LOADER_SPORTS:
                uriBuilder.appendQueryParameter("show-fields", "thumbnail");
                uriBuilder.appendQueryParameter("show-tags", "contributor");
                uriBuilder.appendQueryParameter("page-size", "15");
                uriBuilder.appendQueryParameter("q", "sports");
                Log.i(LOG_NEWS_FRAGMENT,"case ID : "+NEWS_LOADER_SPORTS);
                break;
            case NEWS_LOADER_POLITICS:
                uriBuilder.appendQueryParameter("show-fields", "thumbnail");
                uriBuilder.appendQueryParameter("show-tags", "contributor");
                uriBuilder.appendQueryParameter("page-size", "15");
                uriBuilder.appendQueryParameter("q", "politics");
                Log.i(LOG_NEWS_FRAGMENT,"case ID : "+NEWS_LOADER_POLITICS);
                break;
            default:
        }
        return new NewsLoader(getActivity(),uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        dialogAction.hideDialog();
        Log.i(LOG_NEWS_FRAGMENT,"onLoadFinished");
//        emptyTextViewT.setText("Sorry try after some time");
        emptyTextViewT = getActivity().findViewById(R.id.emptyTextView);
        newsList.clear();
        if(data!=null && !data.isEmpty()){
            newsList.addAll(data);
            adapter = new RecyclerAdapter(newsList, getActivity());
            emptyTextViewT.setVisibility(View.INVISIBLE);
        }else {
            emptyTextViewT.setVisibility(View.VISIBLE);
            emptyTextViewT.setText(getString(R.string.nullData));
        }


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        Log.i(LOG_NEWS_FRAGMENT,"onLoaderReset");
    }
}
