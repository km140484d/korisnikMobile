package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.Fragment;
import android.view.*;

public class CustomerHandymanRequestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_handyman_request_fragment, container, false);
        return view;
    }
}
