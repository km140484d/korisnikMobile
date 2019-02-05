package com.example.mirjana.mobilekorisnik;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.view.*;

public class CustomerActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            boolean visible = false;
            switch (item.getItemId()) {
                case R.id.customer_search_tab:
                    fragment = new CustomerSearchFragment();
                    break;
                case R.id.customer_requests_tab:
                    fragment = new CustomerRequestsFragment();
                    break;
                case R.id.customer_favorites_tab:
                    fragment = new CustomerFavoritesFragment();
                    break;
                case R.id.customer_edit_tab:
                    fragment = new CustomerEditFragment();
                    break;
                case R.id.customer_log_out_tab:
                    Intent intent = new Intent(getBaseContext(), IndexActivity.class);
                    startActivity(intent);
                    break;
            }
            return loadFragment(fragment, null);
        }
    };

    public boolean loadFragment(Fragment fragment, Bundle bundle) {
        //switching fragment
        if (fragment != null) {
            if (bundle != null)
                fragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.customer_frame_layout, fragment)
                    .addToBackStack(null).commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_layout);

        BottomNavigationView navigation = findViewById(R.id.customer_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new CustomerSearchFragment(), null);

    }

}
