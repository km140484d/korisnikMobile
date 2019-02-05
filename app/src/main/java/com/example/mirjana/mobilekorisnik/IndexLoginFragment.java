package com.example.mirjana.mobilekorisnik;

import android.content.*;
import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

import beans.*;
import database.DB;


public class IndexLoginFragment extends Fragment {

    private EditText usernameEdit;
    private EditText passwordEdit;

    private Button loginButton;
    private Button regButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_login_fragment, container, false);
        //login username and password
        usernameEdit = view.findViewById(R.id.username_edit);
        passwordEdit = view.findViewById(R.id.password_edit);
        //login buttons
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(l -> {
            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            String message = "";
            if (username.isEmpty() || password.isEmpty()) {
                if (username.isEmpty())
                    message = getResources().getString(R.string.username_empty);
                else
                    message = getResources().getString(R.string.password_empty);
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            } else {
                User user = DB.getDBInstance().findUser(username, password);
                if (user == null)
                    Toast.makeText(getContext(), getResources().getString(R.string.invalid_user), Toast.LENGTH_SHORT).show();
                else {
                    if (!user.getAccount().getPassword().equals(password))
                        Toast.makeText(getContext(), getResources().getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
                    else {
                        if (user instanceof Customer)
                            DB.getDBInstance().setCurrentCustomer((Customer) user);
                        else
                            DB.getDBInstance().setCurrentHandyman((Handyman) user);
                        Intent intent = new Intent(getActivity(), HandymanActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
        regButton = view.findViewById(R.id.registration_button);
        regButton.setOnClickListener(l -> ((IndexActivity) getActivity()).loadFragment(new IndexRegistrationFragment()));

        return view;
    }
}
