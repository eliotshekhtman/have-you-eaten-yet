package com.example.haveyoueatenyet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class SignUpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    public void signUp(View view) {
        EditText nameView = view.findViewById(R.id.nameSignUp);
        EditText usernameView = view.findViewById(R.id.usernameSignUp);
        EditText passwordView = view.findViewById(R.id.passwordSignUp);
        String name = nameView.getText().toString();
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();
        setAccount(new Account(name, username, password));
    }

    public void setAccount(Account account) {
        PersonalActivity.account = account;
        // TODO: figure out how to upload this account and its info online if new (CHECK IF NEW)
        // Save id to internal storage
        SharedPreferences mPrefs = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.putLong("accountId", account.getId());
        prefsEditor.apply();
        prefsEditor.commit();

        long accountId = mPrefs.getLong("accountId", 0);
        Log.d("STORED ACCOUNT ID", accountId+"");
    }

}
