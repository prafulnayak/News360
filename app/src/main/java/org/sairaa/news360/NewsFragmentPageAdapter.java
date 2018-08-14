package org.sairaa.news360;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

class NewsFragmentPageAdapter extends FragmentPagerAdapter {

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

        return new NewsFragment(mContext);
    }

    @Override
    public int getCount() {
        sharedPreferenceConfig.writeNoOfTabs();
        return sharedPreferenceConfig.readNoOfTabs();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        Log.i("page title : ","Page ");
        switch (position){
            case 0:
                return mContext.getString(R.string.top100);
            case 1:
                if(sharedPreferenceConfig.readSportsStatus())
                    return mContext.getString(R.string.sports);
                if(sharedPreferenceConfig.readPoliticsStatus())
                    return mContext.getString(R.string.politics);
            case 2:
                return mContext.getString(R.string.politics);
            default:
                return mContext.getString(R.string.politics);

        }

    }
}
