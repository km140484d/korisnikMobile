package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.Button;


public class HandymanRequestDetailFragment extends Fragment {

    private Button customerProfileButton;
    //handyman_request_customer_button

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.handyman_request_detail_fragment, container, false);
        customerProfileButton = view.findViewById(R.id.handyman_request_customer_button);
        customerProfileButton.setOnClickListener(o ->{
            ((HandymanActivity)(getActivity())).loadFragment(new HandymanCustomerProfileFragment());
        });
        return view;
    }

}
