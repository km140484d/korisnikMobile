package com.example.mirjana.mobilekorisnik;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.util.List;

import beans.*;
import database.DB;

class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {

    private List<Request> requests;
    private Context context;

    public RequestsAdapter(Context context) {
        this.requests = DB.getCurrentCustomer().getRequests();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_request_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Request request = requests.get(i);
        viewHolder.getJobText().setText(request.getJob().getOccupation().getWork());
        viewHolder.getNameText().setText(request.getHandyman().getName() + " " + request.getHandyman().getSurname());
        viewHolder.getAddressText().setText(request.getAddress().getCounty() + ", " + request.getAddress().getStreetNumber());
        viewHolder.getTimeText().setText(request.getRequestDate().toString());
        viewHolder.getTimeCreatedText().setText(DB.getDBInstance().currentDate().toString());
        viewHolder.getStatusText().setText(request.getCurrentState().toString());
        viewHolder.getCustomerRequestsLayout().setOnClickListener(l ->{
            if (request.getCurrentState().equals(Request.RequestStates.POSLAT)){
                viewHolder.showLayout(viewHolder.getCancelLayout());
                viewHolder.getRequestCanceledButton().setOnClickListener(c ->{
                    request.setCanceledDate(DB.getDBInstance().currentDate());
                    request.setCurrentState(Request.RequestStates.OTKAZAN);
                    viewHolder.getStatusText().setText(request.getCurrentState().toString());
                    viewHolder.showLayout(viewHolder.getCancelLayout());
                });
            }else{
                if (request.getCurrentState().equals(Request.RequestStates.REALIZOVAN)){
                    viewHolder.showLayout(viewHolder.getRateLayout());
                    viewHolder.getRequestRatingButton().setOnClickListener(c -> {
                        request.setRating(viewHolder.getRequestRatingBar().getRating());
                        request.setComment(viewHolder.getRequestCommentEdit().getText().toString());
                        request.setCurrentState(Request.RequestStates.OCENJEN);
                        viewHolder.getStatusText().setText(request.getCurrentState().toString());
                        viewHolder.showLayout(viewHolder.getRateLayout());
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout customerRequestsLayout;
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
        private LinearLayout rateLayout;
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
            customerRequestsLayout = itemView.findViewById(R.id.customer_requests_linear_layout);
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

        public LinearLayout getCustomerRequestsLayout() {
            return customerRequestsLayout;
        }

        public LinearLayout getRateLayout() {
            return rateLayout;
        }

        public LinearLayout getCancelLayout() {
            return cancelLayout;
        }

        public void showLayout(LinearLayout layout){
            layout.setVisibility(layout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }
    }
}
