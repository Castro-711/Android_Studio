package com.example.castro.countriesauto;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {

        protected void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.activity_main);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, COUNTRIES);

            AutoCompleteTextView textView = (AutoCompleteTextView)
                    findViewById(R.id.countries_list);
            // how many letters before suggestions appear
            textView.setThreshold(3);
            textView.setAdapter(adapter);
        }

        private static final String[] COUNTRIES = new String[] {
                "Belgium", "Britain", "France", "Ireland", "Italy", "Germany",
                "Slovenia", "Slovakia", "Spain", "Sweden"
        };


}
