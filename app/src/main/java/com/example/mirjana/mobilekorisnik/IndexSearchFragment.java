package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import beans.Handyman;
import beans.Skill;
import database.DB;


public class IndexSearchFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private Spinner jobSpinner;
    private SeekBar urgencySeekbar;
    private RatingBar ratingBar;
    private Spinner skillSpinner;
    private EditText priceMinEdit;
    private EditText priceMaxEdit;
    private EditText dateMinEdit;
    private EditText dateMaxEdit;
    private EditText expMinEdit;
    private EditText expMaxEdit;
    private Button searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_search_handymen_fragment, container, false);
        //recycler
        mRecyclerView = view.findViewById(R.id.index_search_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //search UI commands
        jobSpinner = view.findViewById(R.id.job_spinner);
        ArrayAdapter<String> jobAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, DB.getDBInstance().jobsToString());
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobSpinner.setAdapter(jobAdapter);
        urgencySeekbar = view.findViewById(R.id.urgency_seek_bar);
        ratingBar = view.findViewById(R.id.search_rating_bar);
        skillSpinner = view.findViewById(R.id.special_skills_spinner);
        ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, DB.getDBInstance().skillsToString());
        skillAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillSpinner.setAdapter(skillAdapter);
        priceMinEdit = view.findViewById(R.id.price_min_edit);
        priceMaxEdit = view.findViewById(R.id.price_max_edit);
        dateMinEdit = view.findViewById(R.id.date_min_edit);
        dateMaxEdit = view.findViewById(R.id.date_max_edit);
        expMinEdit = view.findViewById(R.id.experience_min_edit);
        expMaxEdit = view.findViewById(R.id.experience_max_edit);
        searchButton = view.findViewById(R.id.search_handymen_button);

        //on click Search
        searchButton.setOnClickListener(l -> {
            List<Handyman> handymen = DB.getDBInstance().getHandymen();
            String jobText = jobSpinner.getSelectedItem().toString();
            String skillText = skillSpinner.getSelectedItem().toString();
            String priceMinText = priceMinEdit.getEditableText().toString();
            String priceMaxText = priceMaxEdit.getEditableText().toString();
            String expMinText = expMinEdit.getEditableText().toString();
            String expMaxText = expMaxEdit.getEditableText().toString();
            Double priceMinValue = priceMinText.isEmpty() ? null : Double.parseDouble(priceMinText);
            Double priceMaxValue = priceMaxText.isEmpty() ? null : Double.parseDouble(priceMaxText);
            Integer expMinValue = expMinText.isEmpty() ? null : Integer.parseInt(expMinText);
            Integer expMaxValue = expMaxText.isEmpty() ? null : Integer.parseInt(expMaxText);
            double ratingValue = ratingBar.getRating();
            List<Handyman> filteredHandymen = new ArrayList<>();
            for (Handyman handyman : handymen) {
                List<Handyman.Job> jobs = handyman.getJobs();
                for (Handyman.Job job : jobs) {
                    if ((jobText == null || jobText.isEmpty() || job.getOccupation().getWork().equals(jobText)) &&
                            (priceMinText.isEmpty() || job.getPrice().compareTo(priceMinValue) >= 0) &&
                            (priceMaxText.isEmpty() || job.getPrice().compareTo(priceMaxValue) <= 0) &&
                            (expMinText.isEmpty() || job.getExperience().compareTo(expMinValue) >= 0) &&
                            (expMaxText.isEmpty() || job.getExperience().compareTo(expMaxValue) <= 0) &&
                            (job.getRating() >= ratingValue)) {
                        //check if skill is null
                        if (skillText != null && !skillText.isEmpty()) {
                            for (Skill skill : handyman.getSkills()) {
                                if (skill.getDescription().equals(skillText))
                                    filteredHandymen.add(handyman);
                                break;
                            }
                        }else
                            filteredHandymen.add(handyman);
                        break;
                    }
                }

            }
            mAdapter = new SearchAdapter(filteredHandymen, getContext(), true);
            mRecyclerView.setAdapter(mAdapter);
        });
        return view;
    }
}
