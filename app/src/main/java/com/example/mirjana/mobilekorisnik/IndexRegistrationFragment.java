package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

import java.text.*;
import java.util.*;

import beans.*;
import database.*;

public class IndexRegistrationFragment extends Fragment {
    private EditText nameEdit;
    private EditText surnameEdit;
    private EditText accountEdit;
    private EditText phoneEdit;
    private EditText emailEdit;
    private EditText cardEdit;
    private EditText jobEdit;
    private EditText commentEdit;

    private RadioGroup userGroup;
    private RadioButton userButton;

    private Button saveButton;

    //bean objects
    private Customer.CreditCard creditCard;
    private Customer customer;
    private Handyman handyman;

    final int DRAWABLE_RIGHT = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        customer = new Customer();
        handyman = new Handyman();
        View view = inflater.inflate(R.layout.index_registration_fragment, container, false);
        nameEdit = view.findViewById(R.id.name_edit);
        surnameEdit = view.findViewById(R.id.surname_edit);
        accountEdit = view.findViewById(R.id.account_edit);
        phoneEdit = view.findViewById(R.id.phone_edit);
        emailEdit = view.findViewById(R.id.email_edit);
        cardEdit = view.findViewById(R.id.credit_card_edit);
        cardEdit.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (cardEdit.getRight() - cardEdit.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    showCreditCardPopup(view);
                }
            }
            return true;
        });
        jobEdit = view.findViewById(R.id.job_edit);
        jobEdit.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (cardEdit.getRight() - cardEdit.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    showJobPopup(view);
                }
            }
            return true;
        });
        commentEdit = view.findViewById(R.id.registration_comment);

        userGroup = view.findViewById(R.id.user_type_radio_group);
        userGroup.setOnCheckedChangeListener((group, checkedId) -> {
            userButton = view.findViewById(checkedId);
            if (userButton.getText().toString().equals(getResources().getString(R.string.customer))) {
                jobEdit.setVisibility(View.GONE);
                cardEdit.setVisibility(View.VISIBLE);
            } else {
                jobEdit.setVisibility(View.VISIBLE);
                cardEdit.setVisibility(View.GONE);
            }
        });

        saveButton = view.findViewById(R.id.user_registration_save);
        saveButton.setOnClickListener(l -> {
            //TODO: check to see if Account is set -> if not show job popupWindow
            User user = customer;
            if (jobEdit.getVisibility() == View.VISIBLE) {
                user = handyman;
            }
            user.setName(nameEdit.getText().toString());
            user.setSurname(surnameEdit.getText().toString());
            user.setEmail(emailEdit.getText().toString());
            user.setPhone(phoneEdit.getText().toString());
            user.setComment(commentEdit.getText().toString());
            if (user instanceof Customer){
                DB.getDBInstance().addCustomer((Customer)user);
                DB.getDBInstance().setCurrentCustomer(customer);
            }else{
                DB.getDBInstance().addCustomer((Customer)user);
                DB.getDBInstance().setCurrentHandyman(handyman);
            }
            ((IndexActivity)getActivity()).loadFragment(new IndexLoginFragment());
        });
        return view;
    }

    private void showJobPopup(View view) {
//        View popupView = LayoutInflater.from(getContext()).inflate(R.layout.user_credit_card_form, null);
//        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
//        TableLayout jobTable = popupView.findViewById(R.id.registration_job_table);
//
//
//
//        Button saveButton = popupView.findViewById(R.id.credit_card_save);
//        saveButton.setOnClickListener(l ->{
//            popupWindow.dismiss();
//        });
//        Button cancelButton = popupView.findViewById(R.id.credit_card_cancel);
//        cancelButton.setOnClickListener(l -> popupWindow.dismiss());
//
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//        popupView.setOnTouchListener((v, event) -> {
//            popupWindow.dismiss();
//            return true;
//        });
    }

    public void showCreditCardPopup(View view) {
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
                creditCard = customer.new CreditCard(cardNumberEdit.getText().toString(), date, cardCodeEdit.getText().toString(), 0.0);
            } catch (ParseException e) {
                Toast.makeText(getContext(), "date conversion error", Toast.LENGTH_SHORT).show();
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


}
