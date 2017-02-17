package com.example.castro.sharedpreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ideal for storing preferences and usernames
        // small data to hold
        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.example.castro.sharedpreferences", Context.MODE_PRIVATE);

        // Context.MODE_PRIVATE -> allows only my app to access the data

        // if we want to save data to sharedPreferences
        sharedPreferences.edit().putString("username", "castro").apply();

        // how to retrieve data
        // sharedPreferences.getString(key value, default string)
        // in this case we leave the default blank
        String username = sharedPreferences.getString("username", "");

        Log.i("username", username);

        // to test that the username is saved comment out the line
        // that you saved the username


        // alert dialog

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you sure?")
                .setMessage("Do you definitely want to do this?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Log.i("Button tapped", "Yes");
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
