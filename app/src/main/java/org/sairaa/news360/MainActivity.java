package org.sairaa.news360;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private int noOfTab;
    private static NewsFragmentPageAdapter newsAdapter;
    ViewPager viewPager;
    private SharedPreferenceConfig sharedPreferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferenceConfig = new SharedPreferenceConfig(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        newsAdapter = new NewsFragmentPageAdapter(this,getSupportFragmentManager());
        // Set the adapter onto the view pager
        viewPager.setAdapter(newsAdapter);
        // Find the tab layout that shows the tabs
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        menu.findItem(R.id.action_sports).setChecked(sharedPreferenceConfig.readSportsStatus());
        menu.findItem(R.id.action_politcs).setChecked(sharedPreferenceConfig.readPoliticsStatus());
        Log.i("tag:","tah");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        item.notifyAll();
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
//                insertPet();
                //displayDatabaseInfo();
                createTabs();
//                newsAdapter = new NewsFragmentPageAdapter(this,getSupportFragmentManager());
//                viewPager.setAdapter(newsAdapter);
//                // Find the tab layout that shows the tabs
//                TabLayout tabLayout = findViewById(R.id.tabLayout);
//                // Connect the tab layout with the view pager. This will
//                //   1. Update the tab layout when the view pager is swiped
//                //   2. Update the view pager when a tab is selected
//                //   3. Set the tab layout's tab names with the view pager's adapter's titles
//                //      by calling onPageTitle()
//                tabLayout.setupWithViewPager(viewPager);
                Log.i("tag:","tah");
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
            case R.id.action_sports:
                sharedPreferenceConfig.writeSportsStatus(!sharedPreferenceConfig.readSportsStatus());
                item.setChecked(sharedPreferenceConfig.readSportsStatus());
                createTabs();
                return true;
            case R.id.action_politcs:
                sharedPreferenceConfig.writePoliticsStatus(!sharedPreferenceConfig.readPoliticsStatus());
                item.setChecked(sharedPreferenceConfig.readPoliticsStatus());
                createTabs();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void createTabs() {
        newsAdapter = new NewsFragmentPageAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(newsAdapter);
        // Find the tab layout that shows the tabs
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);
        Log.i("tag:","tah");
    }
}

