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


}
