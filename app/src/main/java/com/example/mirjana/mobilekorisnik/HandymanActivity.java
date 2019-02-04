package com.example.mirjana.mobilekorisnik;

import android.content.*;
import android.os.Bundle;
import android.support.annotation.*;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.*;

public class HandymanActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.handyman_search_tab:
                    //findViewById(R.id.index_search_include).setVisibility(View.VISIBLE);
                    fragment = new HandymanSearchFragment();
                    break;
                case R.id.handyman_requests_tab:
                    //findViewById(R.id.index_search_include).setVisibility(View.GONE);
                    fragment = new HandymanRequestsFragment();
                    break;
                case R.id.handyman_edit_tab:
                    //findViewById(R.id.index_search_include).setVisibility(View.GONE);
                    fragment = new HandymanEditFragment();
                    break;
                case R.id.handyman_log_out_tab:
                    Intent intent = new Intent(getBaseContext(), IndexActivity.class);
                    startActivity(intent);
            }
            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.handyman_frame_layout, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handyman_layout);

        BottomNavigationView navigation = findViewById(R.id.handyman_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HandymanSearchFragment());

    }

}
