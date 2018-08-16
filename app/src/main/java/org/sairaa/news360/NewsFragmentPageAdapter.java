package org.sairaa.news360;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

class NewsFragmentPageAdapter extends FragmentPagerAdapter {

    private static final String LOG_FRAGMENT_ADAPTER = NewsFragmentPageAdapter.class.getName();
    SharedPreferenceConfig sharedPreferenceConfig;
    //Context for the app
    private Context mContext;

    public NewsFragmentPageAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
//        sharedPreferenceConfig = new SharedPreferenceConfig(ctx);
    }



    @Override
    public Fragment getItem(int position) {
        Log.i(LOG_FRAGMENT_ADAPTER, ""+position);
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

//        switch (position){
//            case 0:
//                Log.i(LOG_FRAGMENT_ADAPTER, ""+position+" case 0 executed");
//                return new NewsFragment(mContext,position);
//
//            case 1:
//                if(sharedPreferenceConfig.readSportsStatus()){
//                    Log.i(LOG_FRAGMENT_ADAPTER, ""+position+" case 1 sports executed");
//                    return new NewsFragment(mContext,position);
//                }
//
//                if(sharedPreferenceConfig.readPoliticsStatus()){
//                    Log.i(LOG_FRAGMENT_ADAPTER, ""+position+" case 1 politics executed");
//                    return new NewsFragment(mContext,position);
//                }
//
//            case 2:
//                Log.i(LOG_FRAGMENT_ADAPTER, ""+position+" case 2 politics executed");
//                return new NewsFragment(mContext,position);
//            default:
//                return new NewsFragment(mContext,position);
//
//        }
    }

    @Override
    public int getCount() {
//        sharedPreferenceConfig.writeNoOfTabs();
//        return sharedPreferenceConfig.readNoOfTabs();
        return 3;

    }
    @Override
    public CharSequence getPageTitle(int position) {
//        Log.i("page title : ","Page ");
        switch (position){
            case 0:

//                Log.i(LOG_FRAGMENT_ADAPTER, ""+position+mContext.getString(R.string.top100));
                return mContext.getString(R.string.top100);
            case 1:
                return mContext.getString(R.string.sports);

            case 2:
//                Log.i(LOG_FRAGMENT_ADAPTER, ""+position+mContext.getString(R.string.politics));
                return mContext.getString(R.string.politics);

            default:

                return mContext.getString(R.string.politics);

        }

    }
}
