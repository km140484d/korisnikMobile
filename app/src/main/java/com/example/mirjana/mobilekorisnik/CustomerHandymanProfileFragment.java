package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.Fragment;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import beans.Handyman;
import constants.Constants;
import database.*;

public class CustomerHandymanProfileFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Button handymanRequestButton;
    private Button handymanFavoriteButton;

    private TextView nameText;
    private TextView emailText;
    private TextView phoneText;
    private TextView skillsText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_handyman_profile_fragment, container, false);
        //basic info
        Bundle bundle = this.getArguments();
        Handyman handyman = (Handyman) bundle.getSerializable(Constants.SEARCH_HANDYMAN_KEY);
        nameText = view.findViewById(R.id.handyman_profile_name);
        emailText = view.findViewById(R.id.handyman_profile_email);
        phoneText = view.findViewById(R.id.handyman_profile_phone);
        skillsText = view.findViewById(R.id.handyman_profile_skills);

        nameText.setText(handyman.getName() + " " + handyman.getSurname());
        emailText.setText(handyman.getEmail());
        phoneText.setText(handyman.getPhone());
        skillsText.setText(handyman.skillsToString());
        //jobs
        mRecyclerView = view.findViewById(R.id.handyman_profile_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ProfileJobAdapter(DB.getDBInstance().getHandymen().get(1).getJobs(), getContext());
        mRecyclerView.setAdapter(mAdapter);
        //send handyman request
        handymanRequestButton = view.findViewById(R.id.handyman_request_button);
        handymanRequestButton.setOnClickListener(o -> {
            ((CustomerActivity) (getActivity())).loadFragment(new CustomerHandymanRequestFragment(), getArguments());
        });
        handymanFavoriteButton = view.findViewById(R.id.handyman_favorite_button);
        if (DB.getDBInstance().getCurrentCustomer().getFavoriteHandymen().contains(handyman))
            handymanFavoriteButton.setVisibility(View.GONE);
        else {
            handymanFavoriteButton.setOnClickListener(l -> {
                DB.getDBInstance().getCurrentCustomer().addFavoriteHandyman(handyman);
                handymanFavoriteButton.setVisibility(View.GONE);
            });
        }
        return view;
    }
}
