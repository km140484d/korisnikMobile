package com.example.mirjana.mobilekorisnik;

import android.content.*;
import android.support.annotation.*;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.util.List;

import beans.*;

public class HandymanSearchAdapter extends RecyclerView.Adapter<HandymanSearchAdapter.ViewHolder> {

    private List<Request> requests;
    private Context context;

    public HandymanSearchAdapter(List<Request> requests, Context context) {
        this.requests = requests;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.handyman_search_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        Request request = requests.get(i);
//        viewHolder.getDateView().setText(request.getRequestDate().toString());
//        viewHolder.getAddressView().setText(request.getAddress().getStreetNumber() + ", " + request.getAddress().getCounty());
//        viewHolder.getStatusView().setText(request.getCurrentState().toString());
    }

    @Override
    public int getItemCount() {
        return 5;
//        return requests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView dateView;
        private TextView addressView;
        private TextView statusView;
        private Button requestButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.handyman_search_item_date);
            addressView = itemView.findViewById(R.id.handyman_search_item_address);
            statusView = itemView.findViewById(R.id.handyman_search_item_status);
            requestButton = itemView.findViewById(R.id.search_item_request_button);
            requestButton.setOnClickListener(e -> {
                ((HandymanActivity)context).loadFragment(new HandymanRequestDetailFragment());
            });
        }

        public TextView getDateView() {
            return dateView;
        }

        public TextView getAddressView() {
            return addressView;
        }

        public TextView getStatusView() {
            return statusView;
        }

        public Button getRequestButton() {
            return requestButton;
        }
    }
}
