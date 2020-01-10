package com.example.haveyoueatenyet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class LogInFragment extends Fragment {
    final int REQUEST_EXIT = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    /*
    @Override
    public void onBackPressed() {
        // Retrieve account id from internal storage
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        long accountId = mPrefs.getLong("accountId", 0);
        Log.d("FOUND ACCOUNT ID", accountId+"");
        if(accountId == 0 && PersonalActivity.account == null) {
            Toast.makeText(this, "Sorry, you have to make an account first!", Toast.LENGTH_SHORT).show();
        }
        else super.onBackPressed();
    } */

    public void logIn(View view) {
        TextView usernameView = view.findViewById(R.id.usernameLogIn);
        TextView passwordView = view.findViewById(R.id.passwordLogIn);
        fetchAccount(usernameView.getText().toString(), passwordView.getText().toString());
    }

    private void fetchAccount(String username, String password) {
        // TODO: setup online retrieval
        Toast.makeText(getActivity(), "Online functionality not implemented yet", Toast.LENGTH_SHORT).show();
    }

    public void putId(long id) {
        SharedPreferences mPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.putLong("accountId", PersonalActivity.account.getId());
        prefsEditor.apply();
        prefsEditor.commit();
    }
}
