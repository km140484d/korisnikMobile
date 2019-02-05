package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.EditText;

public class HandymanEditFragment extends Fragment {

    private EditText jobsEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_edit_fragment, container, false);
        jobsEdit = view.findViewById(R.id.handyman_jobs_edit);
        jobsEdit.setVisibility(View.VISIBLE);
        return view;
    }
}
