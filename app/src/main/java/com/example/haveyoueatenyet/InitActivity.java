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
    static final short MIN_PASSWORD_LENGTH = 8;
    static final short MIN_USERNAME_LENGTH = 5;
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
        // TODO: check if username is unique
        if(name.length() == 0)
            Toast.makeText(this,
                    "Please let us know what to call you!", Toast.LENGTH_SHORT).show();
        else if(username.length() < MIN_USERNAME_LENGTH)
            Toast.makeText(this,
                    "Username too short! Minimum length is "+MIN_USERNAME_LENGTH+" characters",
                    Toast.LENGTH_SHORT).show();
        else if(/*not unique username*/ false)
            Toast.makeText(this,
                    "Somebody else already picked that username", Toast.LENGTH_SHORT).show();
        else if(password.length() < MIN_PASSWORD_LENGTH)
            Toast.makeText(this,
                    "Password too short! Minimum length is "+MIN_PASSWORD_LENGTH+" characters",
                    Toast.LENGTH_SHORT).show();
        else {
            setAccount(new Account(name, username, password));
            goToStart();
        }

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
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();
        if(username.length() == 0)
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
        else if(password.length() == 0)
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
        else {
            /*
                Collecting both and sending to fetchAccount() because
                have to maintain the abstraction of not letting them know
                what is wrong
            */
            try {
                PersonalActivity.account = fetchAccount(username, password);
            } catch (NullPointerException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Account fetchAccount(String username, String password) {
        // TODO: setup online retrieval
        if(/*incorrect password or no such username*/ false)
            throw new NullPointerException("Incorrect username/password");
        throw new NullPointerException("Online connection not implemented yet");
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
