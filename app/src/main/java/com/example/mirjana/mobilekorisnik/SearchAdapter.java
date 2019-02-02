package com.example.mirjana.mobilekorisnik;

import android.content.Context;
import android.support.annotation.*;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.util.List;

import beans.*;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private List<Handyman> handymen;
    private Context context;

    public SearchAdapter(List<Handyman> handymen, Context context){
        this.handymen = handymen;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return handymen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameText;
        private TextView jobText;
        private RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.search_item_name);
            jobText = itemView.findViewById(R.id.search_item_job);
            ratingBar = itemView.findViewById(R.id.search_item_rating_bar);
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
    }
}
