package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

import beans.*;
import database.DB;

public class CustomerEditFragment extends Fragment {

    private EditText nameEdit;
    private EditText surnameEdit;
    private EditText emailEdit;
    private EditText phoneEdit;
    private EditText cardEdit;

    private EditText usernameEdit;
    private EditText passEdit;
    private EditText passConfirmedEdit;

    private Customer customer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_edit_fragment, container, false);
        customer = DB.getDBInstance().getCurrentCustomer();
        nameEdit = view.findViewById(R.id.name_edit);
        nameEdit.setText(customer.getName());
        surnameEdit = view.findViewById(R.id.surname_edit);
        surnameEdit.setText(customer.getSurname());
        emailEdit = view.findViewById(R.id.email_edit);
        emailEdit.setText(customer.getEmail());
        phoneEdit = view.findViewById(R.id.phone_edit);
        phoneEdit.setText(customer.getPhone());
        cardEdit = view.findViewById(R.id.credit_card_edit);
        if (customer.getCreditCard() != null)
            cardEdit.setText(customer.getCreditCard().getNumber());

        usernameEdit = view.findViewById(R.id.username_reg_edit);
        usernameEdit.setText(customer.getAccount().getUsername());
        passEdit = view.findViewById(R.id.password_reg_edit);
        passEdit.setText(customer.getAccount().getPassword());
        passConfirmedEdit = view.findViewById(R.id.password_confirmed_reg_edit);
        passConfirmedEdit.setText(customer.getAccount().getPassword());
        passConfirmedEdit.setVisibility(View.GONE);
        enableEditWidgets(false);
        return view;
    }

    private void enableEditWidgets(boolean enable) {
        nameEdit.setEnabled(enable);
        surnameEdit.setEnabled(enable);
        emailEdit.setEnabled(enable);
        phoneEdit.setEnabled(enable);
        cardEdit.setEnabled(enable);
        usernameEdit.setEnabled(enable);
        passEdit.setEnabled(enable);
        passConfirmedEdit.setEnabled(enable);
    }
}
