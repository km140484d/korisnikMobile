package com.example.mirjana.mobilekorisnik;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.*;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.util.List;

import beans.*;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    public static final String SEARCH_HANDYMAN_KEY = "SEARCH_HANDYMAN_KEY";

    private List<Handyman> handymen;
    private Context context;
    private boolean indexSearch;

    public SearchAdapter(List<Handyman> handymen, Context context, boolean indexSearch){
        this.handymen = handymen;
        this.context = context;
        this.indexSearch = indexSearch;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.index_search_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Handyman handyman = handymen.get(i);
        viewHolder.getNameText().setText(handyman.getName() + " " + handyman.getSurname());
        viewHolder.getJobText().setText(handyman.jobsToString());
        viewHolder.getRatingBar().setRating(handyman.getAverageRating());
        if (indexSearch){
            viewHolder.getProfileButton().setVisibility(View.GONE);
            viewHolder.getRequestButton().setVisibility(View.GONE);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(SEARCH_HANDYMAN_KEY, handymen.get(i));
        viewHolder.getProfileButton().setOnClickListener(c -> {
            ((CustomerActivity)(context)).loadFragment(new CustomerHandymanProfileFragment(), bundle);
        });
    }

    @Override
    public int getItemCount() {
        return handymen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameText;
        private TextView jobText;
        private RatingBar ratingBar;
        private Button profileButton;
        private Button requestButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.search_item_name);
            jobText = itemView.findViewById(R.id.search_item_job);
            ratingBar = itemView.findViewById(R.id.search_item_rating_bar);
            profileButton = itemView.findViewById(R.id.profile_item_button);

            requestButton = itemView.findViewById(R.id.mail_item_button);
        }

        public TextView getNameText() {
            return nameText;
        }

        public TextView getJobText() {
            return jobText;
        }

        public RatingBar getRatingBar() {
            return ratingBar;
        }

        public Button getProfileButton() {
            return profileButton;
        }

        public Button getRequestButton() {
            return requestButton;
        }
    }
}
