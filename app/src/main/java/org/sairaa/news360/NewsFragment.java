package org.sairaa.news360;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class NewsFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private ConstraintLayout constraintLayout;
    private ConstraintSet constraintSet1 = new ConstraintSet();
    private ConstraintSet constraintSet2 = new ConstraintSet();
    public NewsFragment(Context mContext){
    this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_news_fragment, container, false);
        constraintLayout = rootView.findViewById(R.id.csFragment);
        constraintSet1.clone(constraintLayout);
        constraintSet2.clone(rootView.getContext(),R.layout.news_details);
        TextView textView = rootView.findViewById(R.id.textViewX);
        textView.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        TransitionManager.beginDelayedTransition(constraintLayout);
        constraintSet2.applyTo(constraintLayout);
        Log.e("Hello","Hello");
    }
}
