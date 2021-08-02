package com.first.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btn;
    TextView text;
    Spinner spinner,purposespinner;
    public String landTypeSelected;
    String land = "Hilly";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activitymain);
        text = (TextView) findViewById(R.id.textView);

        btn = (Button) findViewById(R.id.submitbtn);
        spinner = (Spinner) findViewById(R.id.landtypespinner);

        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Hilly");
        categories.add("Plain");
        categories.add("Riverbank");
        categories.add("Special Purpose Houses");
        categories.add("Rural area");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id) {
        // On selecting a spinner item
        landTypeSelected = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(getApplicationContext(), "Selected: " + landTypeSelected, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    public void fetchData(View view) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String dataresult = databaseAccess.getData(landTypeSelected);
        String linksresult = databaseAccess.getLinks(landTypeSelected);
        databaseAccess.close();

        Bundle linksbundle = new Bundle();
        linksbundle.putString("links",linksresult);
        linksbundle.putString("landtypes",landTypeSelected);
        Intent linksintent = new Intent(this, refsActivity.class);
        linksintent.putExtras(linksbundle);

        Bundle databundle = new Bundle();
        databundle.putString("name",dataresult);
        databundle.putString("landtype",landTypeSelected);
        Intent dataintent = new Intent(this, dataActivity.class);
        dataintent.putExtras(databundle);
        startActivity(dataintent);

    }
    public String setstr(){
        land = landTypeSelected;
        return land;
    }
}
