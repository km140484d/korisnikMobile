package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.Fragment;
import android.support.v7.widget.*;
import android.view.*;

import database.*;

public class HandymanSearchFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.handyman_search_requests_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.handyman_search_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new HandymanSearchAdapter(DB.getDBInstance().getCurrentHandyman().getRequests(), getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
