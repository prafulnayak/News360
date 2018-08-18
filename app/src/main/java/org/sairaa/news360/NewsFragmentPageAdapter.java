package org.sairaa.news360;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class NewsFragmentPageAdapter extends FragmentPagerAdapter {

    private static final String LOG_FRAGMENT_ADAPTER = NewsFragmentPageAdapter.class.getName();
    SharedPreferenceConfig sharedPreferenceConfig;
    //Context for the app
    private Context mContext;


    public NewsFragmentPageAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
        sharedPreferenceConfig = new SharedPreferenceConfig(ctx);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NewsFragment(mContext,Constant.top100);
            case 1:
                return new NewsFragment(mContext,Constant.sports);
            case 2:
                return new NewsFragment(mContext,Constant.politics);
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.top100);
            case 1:
                return mContext.getString(R.string.sports);
            case 2:
                return mContext.getString(R.string.politics);
            default:
                return mContext.getString(R.string.politics);
        }
    }
}
