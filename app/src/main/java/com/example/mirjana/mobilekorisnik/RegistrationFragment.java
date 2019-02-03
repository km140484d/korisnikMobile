package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;

public class RegistrationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_registration_fragment, container, false);
        return view;
    }
}
