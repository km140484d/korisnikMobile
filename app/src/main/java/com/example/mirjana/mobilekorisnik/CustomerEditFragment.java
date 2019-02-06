package com.example.mirjana.mobilekorisnik;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private Button accountSaveButton;
    private Button accountCancelButton;
    private Button accountEditButton;

    private Button updateSaveButton;
    private Button updateCancelButton;
    //update_command_form
    private LinearLayout updateCommandsLayout;

    //credit card editTexts
    private EditText cardNumberEdit;
    private EditText cardValidityEdit;
    private EditText cardCodeEdit;

    private Customer customer;

    final int DRAWABLE_RIGHT = 2;

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
        cardEdit.setOnTouchListener((v, event) -> {
            if (cardEdit.isEnabled() && event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (cardEdit.getRight() - cardEdit.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    showCreditCardPopup(view);
                }
            }
            return true;
        });

        usernameEdit = view.findViewById(R.id.username_reg_edit);
        usernameEdit.setText(customer.getAccount().getUsername());
        usernameEdit.setEnabled(false);
        passEdit = view.findViewById(R.id.password_reg_edit);
        passEdit.setText(customer.getAccount().getPassword());
        passConfirmedEdit = view.findViewById(R.id.password_confirmed_reg_edit);
        passConfirmedEdit.setText(customer.getAccount().getPassword());
        enableEditWidgets(false);
        //remove unwanted  save and cancel buttons
        accountSaveButton = view.findViewById(R.id.registration_account_save);
        accountSaveButton.setVisibility(View.GONE);
        accountCancelButton = view.findViewById(R.id.registration_account_cancel);
        accountCancelButton.setVisibility(View.GONE);

        updateCommandsLayout = view.findViewById(R.id.update_command_form);

        accountEditButton = view.findViewById(R.id.update_edit_button);
        accountEditButton.setOnClickListener(l -> {
            enableEditWidgets(true);
            accountEditButton.setVisibility(View.GONE);
            updateCommandsLayout.setVisibility(View.VISIBLE);
        });

        updateSaveButton = view.findViewById(R.id.update_account_save);
        updateSaveButton.setOnClickListener(l -> {
            if (!passEdit.getText().toString().equals(passConfirmedEdit.getText().toString()))
                Toast.makeText(getContext(), getResources().getString(R.string.unmatching_passwords), Toast.LENGTH_SHORT).show();
            else {
                customer.setName(nameEdit.getText().toString());
                customer.setSurname(surnameEdit.getText().toString());
                customer.setEmail(emailEdit.getText().toString());
                customer.setPhone(phoneEdit.getText().toString());
                enableEditWidgets(false);
                accountEditButton.setVisibility(View.VISIBLE);
                updateCommandsLayout.setVisibility(View.GONE);
            }
        });

        updateCancelButton = view.findViewById(R.id.update_account_cancel);
        updateCancelButton.setOnClickListener(l -> {
            enableEditWidgets(false);
            accountEditButton.setVisibility(View.VISIBLE);
            updateCommandsLayout.setVisibility(View.GONE);
        });

        return view;
    }

    private void showCreditCardPopup(View view) {
        View popupView = LayoutInflater.from(getContext()).inflate(R.layout.user_credit_card_form, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        EditText cardNumberEdit = popupView.findViewById(R.id.credit_card_number_edit);
        cardNumberEdit.setText(customer.getCreditCard().getNumber());
        EditText cardValidityEdit = popupView.findViewById(R.id.credit_card_validity_edit);
        cardValidityEdit.setText(DB.getDBInstance().formattedDate(customer.getCreditCard().getValidity()));
        EditText cardCodeEdit = popupView.findViewById(R.id.credit_card_code_edit);
        cardCodeEdit.setText(customer.getCreditCard().getCode());
        Button saveButton = popupView.findViewById(R.id.credit_card_save);
        saveButton.setOnClickListener(l ->{
            DateFormat format = new SimpleDateFormat("MM/yy");
            Date date = null;
            try {
                date = format.parse(cardValidityEdit.getText().toString());
                customer.setCreditCard(customer.new CreditCard(cardNumberEdit.getText().toString(), date, cardCodeEdit.getText().toString(), 0.0));
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

    private void enableEditWidgets(boolean enable) {
        nameEdit.setEnabled(enable);
        surnameEdit.setEnabled(enable);
        emailEdit.setEnabled(enable);
        phoneEdit.setEnabled(enable);
        cardEdit.setEnabled(enable);
        //usernameEdit.setEnabled(enable);
        passEdit.setEnabled(enable);
        passConfirmedEdit.setEnabled(enable);
        passConfirmedEdit.setVisibility(enable ? View.VISIBLE : View.GONE);
    }
}
