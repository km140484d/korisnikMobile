package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;

import database.DB;

public class CustomerFavoritesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_favorites_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.customer_favorites_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new FavoritesAdapter(DB.getDBInstance().getCurrentCustomer().getFavoriteHandymen(), getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
