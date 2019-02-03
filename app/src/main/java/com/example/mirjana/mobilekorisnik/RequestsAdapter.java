package com.example.mirjana.mobilekorisnik;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.util.List;

import beans.*;

class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {

    private List<Request> requests;
    private Context context;

    public RequestsAdapter(List<Request> requests, Context context) {
        this.requests = requests;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.request_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //TODO: vezati podatke za ViewHoldere
    }

    @Override
    public int getItemCount() {
        //return requests.size();
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView jobText;
        private TextView nameText;
        private TextView addressText;
        private TextView timeText;
        private TextView statusText;
        private TextView timeCreatedText;
        private Button requestCanceledButton;
        private Button requestRatingButton;
        private EditText requestCommentEdit;
        private RatingBar requestRatingBar;
        private LinearLayout rateLayout;  //request_item_rate_layout
        private LinearLayout cancelLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobText = itemView.findViewById(R.id.request_item_job);
            nameText = itemView.findViewById(R.id.request_item_handyman_name);
            addressText = itemView.findViewById(R.id.request_item_address);
            timeText = itemView.findViewById(R.id.request_item_time);
            statusText = itemView.findViewById(R.id.request_item_status);
            timeCreatedText = itemView.findViewById(R.id.request_item_time_created);
            requestCanceledButton = itemView.findViewById(R.id.request_item_cancel_button);
            requestRatingButton = itemView.findViewById(R.id.request_item_rate_button);
            requestCommentEdit = itemView.findViewById(R.id.request_item_comment);
            requestRatingBar = itemView.findViewById(R.id.request_item_rating_bar);
            rateLayout = itemView.findViewById(R.id.request_item_rate_layout);
            cancelLayout = itemView.findViewById(R.id.request_item_cancel_layout);
        }

        public TextView getJobText() {
            return jobText;
        }

        public TextView getNameText() {
            return nameText;
        }

        public TextView getAddressText() {
            return addressText;
        }

        public TextView getTimeText() {
            return timeText;
        }

        public TextView getStatusText() {
            return statusText;
        }

        public TextView getTimeCreatedText() {
            return timeCreatedText;
        }

        public Button getRequestCanceledButton(){
            return requestCanceledButton;
        }

        public Button getRequestRatingButton() {
            return requestRatingButton;
        }

        public EditText getRequestCommentEdit() {
            return requestCommentEdit;
        }

        public RatingBar getRequestRatingBar() {
            return requestRatingBar;
        }

        public LinearLayout getRateLayout() {
            return rateLayout;
        }

        public LinearLayout getCancelLayout() {
            return cancelLayout;
        }
    }
}
