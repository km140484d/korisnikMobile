package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;

import database.*;

public class CustomerRequestsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_requests_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.customer_requests_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RequestsAdapter(null, getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
