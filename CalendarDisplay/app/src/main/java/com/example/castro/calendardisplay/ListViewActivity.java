package com.example.castro.calendardisplay;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listVAndroid;
    private AndroidListAdapter listAdapter;
    private Button buttonCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        CalendarCollection.dateCollectionArray = new ArrayList<CalendarCollection>();
        CalendarCollection.dateCollectionArray.add(
                new CalendarCollection("2016-11-04", "Kyrans Birthday"));

        getWidget();
    } // onCreate()

    public void getWidget()
    {
        buttonCal = (Button) findViewById(R.id.buttonCal);
        buttonCal.setOnClickListener(this);

        listVAndroid = (ListView) findViewById(R.id.listViewAndroid);
        listAdapter = new AndroidListAdapter(ListViewActivity.this, R.layout.listItem,
                CalendarCollection.dateCollectionArray);
        listVAndroid.setAdapter(listAdapter);
    } // getWidget()

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.buttonCal:
                startActivity(ListViewAcivity.this, CalendarActivity.class);
                break;
            default:
                break;
        } // switch
    } // onClick

} // {}


