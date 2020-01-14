package com.example.haveyoueatenyet;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class InitActivity extends AppCompatActivity {
    static boolean first_open = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        setContentView((R.layout.activity_init));

        // Retrieve account id from internal storage
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        long accountId = mPrefs.getLong("accountId", 0);
        if(accountId != 0 && !first_open) {
            first_open = true;
            //TODO: fetch account from online and init account
            PersonalActivity.account = new Account("a", "b", "c");
            goToStart();
        }
        else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LogInFragment logIn = new LogInFragment();
            fragmentTransaction.add(R.id.fragment_container, logIn);
            fragmentTransaction.commit();
        }
    }

    public void goToStart() {
        Intent intentSwitch = new Intent(this, StartActivity.class);
        startActivity(intentSwitch);
    }

    public void goToSignUp(View view) {
        Log.d("GOTOSIGNUP", "CALLED");
        // Create new fragment and transaction
        Fragment signUp = new SignUpFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container, signUp);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void signUp(View view) {
        EditText nameView = (EditText) findViewById(R.id.nameSignUp);
        EditText usernameView = (EditText) findViewById(R.id.usernameSignUp);
        EditText passwordView = (EditText) findViewById(R.id.passwordSignUp);
        String name = nameView.getText().toString();
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();
        setAccount(new Account(name, username, password));
        goToStart();
    }

    public void setAccount(Account account) {
        PersonalActivity.account = account;
        // TODO: figure out how to upload this account and its info online if new (CHECK IF NEW)
        // Save id to internal storage
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.putLong("accountId", account.getId());
        prefsEditor.apply();
        prefsEditor.commit();

        long accountId = mPrefs.getLong("accountId", 0);
        Log.d("STORED ACCOUNT ID", accountId+"");
    }

    public void logIn(View view) {
        EditText usernameView = (EditText) findViewById(R.id.usernameLogIn);
        EditText passwordView = (EditText) findViewById(R.id.passwordLogIn);
        PersonalActivity.account = fetchAccount(usernameView.getText().toString(), passwordView.getText().toString());
        // TODO: online retrieval
    }

    private Account fetchAccount(String username, String password) {
        // TODO: setup online retrieval
        Toast.makeText(this, "Online functionality not implemented yet", Toast.LENGTH_SHORT).show();
        return null;
    }
    public void putId(long id) {
        SharedPreferences mPrefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.putLong("accountId", PersonalActivity.account.getId());
        prefsEditor.apply();
        prefsEditor.commit();
    }
}
