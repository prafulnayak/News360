package org.sairaa.news360;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private static NewsFragmentPageAdapter newsAdapter;
    ViewPager viewPager;
    private SharedPreferenceConfig sharedPreferenceConfig;
    private CheckConnection checkConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferenceConfig = new SharedPreferenceConfig(this);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        checkConnection = new CheckConnection(this);
        if(checkConnection.isConnected()){
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
        }else{
            Toast.makeText(this,getString(R.string.check_connection),Toast.LENGTH_LONG).show();
            alertDialogBuilder.setTitle(getString(R.string.check_connection)).setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).create().show();
        }
    }


}

