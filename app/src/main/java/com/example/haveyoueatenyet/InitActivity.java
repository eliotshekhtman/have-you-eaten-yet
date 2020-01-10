package com.example.haveyoueatenyet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class InitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        setContentView((R.layout.activity_init));

        // Retrieve account id from internal storage
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        long accountId = mPrefs.getLong("accountId", 0);
        if(accountId != 0) {
            //TODO: fetch account from online and init account
            PersonalActivity.account = new Account("a", "b", "c");
            Intent intentSwitch = new Intent(this, StartActivity.class);
            startActivity(intentSwitch);
        }
    }

    public void goToSignUp(View view) {
        // Create new fragment and transaction
        Fragment signUp = new SignUpFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment, signUp);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
