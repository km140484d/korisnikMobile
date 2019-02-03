package com.example.mirjana.mobilekorisnik;

import android.content.*;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.util.List;

import beans.*;

public class ProfileJobAdapter extends RecyclerView.Adapter<ProfileJobAdapter.ViewHolder> {

    private List<Handyman.Job> jobs;
    private Context context;

    public ProfileJobAdapter(List<Handyman.Job> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.handyman_profile_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Handyman.Job job = jobs.get(i);
        Resources res = context.getResources();
        viewHolder.getJobText().setText(job.getOccupation().getWork());
        viewHolder.getPriceText().setText(String.format(res.getString(R.string.job_hyphen), job.getPrice() + ""));
        viewHolder.getRatingBar().setRating(job.getRating());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView jobText;
        private TextView priceText;
        private RatingBar ratingBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobText = itemView.findViewById(R.id.profile_job_item);
            priceText = itemView.findViewById(R.id.profile_price_item);
            ratingBar = itemView.findViewById(R.id.profile_rating_item);
        }

        public TextView getJobText() {
            return jobText;
        }

        public TextView getPriceText() {
            return priceText;
        }

        public RatingBar getRatingBar() {
            return ratingBar;
        }
    }
}
