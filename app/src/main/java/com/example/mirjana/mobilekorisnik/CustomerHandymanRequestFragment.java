package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import beans.*;
import constants.Constants;
import database.DB;

public class CustomerHandymanRequestFragment extends Fragment {
    //request_job_spinner
    private Spinner jobSpinner;
    private EditText dateEdit;
    private SeekBar urgencySeekBar;
    private RadioGroup paymentGroup;
    private EditText countyEdit;
    private EditText streetEdit;
    private Button sendButton;
    private Button cancelButton;

    private Handyman handyman;
    private Customer customer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_handyman_request_fragment, container, false);
        handyman = (Handyman) getArguments().getSerializable(Constants.SEARCH_HANDYMAN_KEY);
        customer = DB.getDBInstance().getCurrentCustomer();
        jobSpinner = view.findViewById(R.id.request_job_spinner);
        ArrayAdapter<String> jobAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, DB.getDBInstance().handymanJobs(handyman.getJobs()));
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobSpinner.setAdapter(jobAdapter);
        dateEdit = view.findViewById(R.id.request_date);
        urgencySeekBar = view.findViewById(R.id.request_urgency);
        paymentGroup = view.findViewById(R.id.payment_type_radio_group);
        countyEdit = view.findViewById(R.id.request_county);
        streetEdit = view.findViewById(R.id.request_street);
        sendButton = view.findViewById(R.id.request_send_button);
        sendButton.setOnClickListener(l -> {
            String jobText = jobSpinner.getSelectedItem().toString();
            String dateText = dateEdit.getText().toString();
            Integer urgencyBar = urgencySeekBar.getProgress();
            String countyText = countyEdit.getText().toString();
            String streetText = streetEdit.getText().toString();
            if (jobText.isEmpty() || dateText.isEmpty() || countyText.isEmpty() || streetText.isEmpty()) {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
            } else {
                RadioButton paymentRadio = view.findViewById(paymentGroup.getCheckedRadioButtonId());
                if (paymentRadio.getText().toString().equals(getResources().getString(R.string.credit_card)) &&
                        customer.getCreditCard() == null)
                    showCreditCardPopup(view);
                else {
                    DateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Date requestDate = null;
                    try {
                        requestDate = format.parse(dateText);
                        Request request = new Request(customer, handyman, DB.getDBInstance().findJob(handyman, jobText),
                                countyText, streetText, DB.getDBInstance().currentDate(), requestDate, urgencyBar + 0.0,
                                paymentRadio.getText().toString().equals(getResources().getString(R.string.cash)));
                        ((CustomerActivity) getActivity()).loadFragment(new CustomerRequestsFragment(), null);
                    } catch (ParseException e) {
                        Toast.makeText(getContext(), getResources().getString(R.string.invalid_date), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cancelButton = view.findViewById(R.id.request_cancel_button);
        cancelButton.setOnClickListener(l -> {
            resetRequestForm();
        });
        return view;
    }

    private void showCreditCardPopup(View view) {
        View popupView = LayoutInflater.from(getContext()).inflate(R.layout.user_credit_card_form, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        EditText cardNumberEdit = popupView.findViewById(R.id.credit_card_number_edit);
        EditText cardValidityEdit = popupView.findViewById(R.id.credit_card_validity_edit);
        EditText cardCodeEdit = popupView.findViewById(R.id.credit_card_code_edit);
        Button saveButton = popupView.findViewById(R.id.credit_card_save);
        saveButton.setOnClickListener(l ->{
            DateFormat format = new SimpleDateFormat("MM/yy");
            Date date = null;
            try {
                date = format.parse(cardValidityEdit.getText().toString());
                customer.setCreditCard(customer.new CreditCard(cardNumberEdit.getText().toString(),
                        date, cardCodeEdit.getText().toString(), 0.0));
            } catch (ParseException e) {
                Toast.makeText(getContext(), getResources().getString(R.string.invalid_date), Toast.LENGTH_SHORT).show();
            }
            popupWindow.dismiss();
        });
        Button cancelButton = popupView.findViewById(R.id.credit_card_cancel);
        cancelButton.setOnClickListener(l -> popupWindow.dismiss());

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            return true;
        });
    }

    private void resetRequestForm() {
        jobSpinner.setSelection(0);
        dateEdit.setText("");
        urgencySeekBar.setProgress(0);
        countyEdit.setText("");
        streetEdit.setText("");
    }
}
