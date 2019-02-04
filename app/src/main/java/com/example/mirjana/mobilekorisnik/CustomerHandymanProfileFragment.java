package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.Fragment;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import database.*;

public class CustomerHandymanProfileFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Button handymanRequestButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_handyman_profile_fragment, container, false);
        //jobs
        mRecyclerView = view.findViewById(R.id.handyman_profile_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ProfileJobAdapter(DB.getDBInstance().getHandymen().get(1).getJobs(), getContext());
        mRecyclerView.setAdapter(mAdapter);
        //send handyman request
        handymanRequestButton = view.findViewById(R.id.handyman_request_button);
        handymanRequestButton.setOnClickListener(o ->{
            ((CustomerActivity)(getActivity())).loadFragment(new CustomerHandymanRequestFragment(), false);
        });
        return view;
    }
}
