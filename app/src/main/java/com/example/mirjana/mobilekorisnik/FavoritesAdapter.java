package com.example.mirjana.mobilekorisnik;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import java.util.List;

import beans.*;
import database.DB;

class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<Handyman> handymen;
    private Context context;

    public FavoritesAdapter(List<Handyman> handymen, Context context) {
        this.handymen = handymen;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_favorite_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Handyman handyman = handymen.get(i);
        viewHolder.getHandymanNameText().setText(handyman.getName() + " " + handyman.getSurname());
        viewHolder.getRequestsRealizedView().setText(DB.getDBInstance().getRequestStateNumber(Request.RequestStates.REALIZOVAN) + "");
        viewHolder.getRequestsDeniedView().setText(DB.getDBInstance().getRequestStateNumber(Request.RequestStates.ODBIJEN) + "");
        List<Request> requests = DB.getRequestArchive();
        if (requests.size() > 0){
            viewHolder.getFirstDate().setText(requests.get(0).formattedDate(requests.get(0).getRequestDate()));
            viewHolder.getFirstStatus().setText(requests.get(0).getCurrentState().toString());
            if (requests.size() > 1){
                viewHolder.getSecondDate().setText(requests.get(1).formattedDate(requests.get(1).getRequestDate()));
                viewHolder.getSecondStatus().setText(requests.get(1).getCurrentState().toString());
                if (requests.size() > 2){
                    viewHolder.getThirdDate().setText(requests.get(2).formattedDate(requests.get(2).getRequestDate()));
                    viewHolder.getThirdStatus().setText(requests.get(2).getCurrentState().toString());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return handymen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView handymanNameText;
        private Button facebookButton;
        private Button instagramButton;
        private Button twitterButton;
        private TextView requestsRealizedView;
        private TextView requestsDeniedView;

        private TextView firstDate;
        private TextView firstStatus;
        private TextView secondDate;
        private TextView secondStatus;
        private TextView thirdDate;
        private TextView thirdStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            handymanNameText = itemView.findViewById(R.id.favorite_item_name);
            facebookButton = itemView.findViewById(R.id.favorite_item_facebook);
            facebookButton.setOnClickListener(l ->{
                //TODO: facebook
            });
            instagramButton = itemView.findViewById(R.id.favorite_item_instagram);
            instagramButton.setOnClickListener(l ->{
                //TODO: instagram
            });
            twitterButton = itemView.findViewById(R.id.favorite_item_twitter);
            twitterButton.setOnClickListener(l ->{
                //TODO: twitter
            });
            requestsRealizedView = itemView.findViewById(R.id.favorite_item_requests_realized);
            requestsDeniedView = itemView.findViewById(R.id.favorite_item_requests_denied);

            firstDate = itemView.findViewById(R.id.favorite_item_first_date);
            firstStatus = itemView.findViewById(R.id.favorite_item_first_status);
            secondDate = itemView.findViewById(R.id.favorite_item_second_date);
            secondStatus = itemView.findViewById(R.id.favorite_item_second_status);
            thirdDate = itemView.findViewById(R.id.favorite_item_third_date);
            thirdStatus = itemView.findViewById(R.id.favorite_item_third_status);
        }

        public TextView getHandymanNameText() {
            return handymanNameText;
        }

        public Button getFacebookButton() {
            return facebookButton;
        }

        public Button getInstagramButton() {
            return instagramButton;
        }

        public Button getTwitterButton() {
            return twitterButton;
        }

        public TextView getRequestsRealizedView() {
            return requestsRealizedView;
        }

        public TextView getRequestsDeniedView() {
            return requestsDeniedView;
        }

        public TextView getFirstDate() {
            return firstDate;
        }

        public TextView getFirstStatus() {
            return firstStatus;
        }

        public TextView getSecondDate() {
            return secondDate;
        }

        public TextView getSecondStatus() {
            return secondStatus;
        }

        public TextView getThirdDate() {
            return thirdDate;
        }

        public TextView getThirdStatus() {
            return thirdStatus;
        }
    }
}
