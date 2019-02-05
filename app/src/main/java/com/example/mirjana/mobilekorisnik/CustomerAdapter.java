package com.example.mirjana.mobilekorisnik;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

import beans.*;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private List<Customer> customers;
    private Context context;

    public CustomerAdapter(List<Customer> customers, Context context){
        this.customers = customers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.handyman_customer_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Customer customer = customers.get(i);
        viewHolder.getNameText().setText(customer.getName());
        viewHolder.getEmailText().setText(customer.getEmail());
        viewHolder.getPhoneText().setText(customer.getPhone());
        viewHolder.getRankText().setText(customer.getRank().toString());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameText;
        private TextView emailText;
        private TextView phoneText;
        private TextView rankText;
        private Button profileButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.customer_item_name);
            emailText = itemView.findViewById(R.id.customer_item_email);
            phoneText = itemView.findViewById(R.id.customer_item_phone);
            rankText = itemView.findViewById(R.id.customer_item_rank);
            profileButton = itemView.findViewById(R.id.customer_item_profile_button);
            profileButton.setOnClickListener(l ->{
                ((HandymanActivity)(context)).loadFragment(new HandymanCustomerProfileFragment());
            });
        }

        public TextView getNameText() {
            return nameText;
        }

        public TextView getEmailText() {
            return emailText;
        }

        public TextView getPhoneText() {
            return phoneText;
        }

        public TextView getRankText() {
            return rankText;
        }
    }
}
