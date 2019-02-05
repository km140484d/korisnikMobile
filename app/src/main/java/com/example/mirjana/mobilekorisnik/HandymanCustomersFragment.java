package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.Fragment;
import android.support.v7.widget.*;
import android.view.*;

import database.DB;

public class HandymanCustomersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.handyman_customers_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.handyman_customers_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CustomerAdapter(DB.getCustomers(), getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
