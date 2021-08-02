package com.first.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dataActivity extends AppCompatActivity {
    MainActivity ma;
    Button databtn, imagesbtn, refsbtn;
    TextView dataHeading;
    TextView dataResult;
    String landtype1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activitydata);
        ma = new MainActivity();
        dataHeading = (TextView) findViewById(R.id.dataHead);
        dataResult = (TextView) findViewById(R.id.dataText);
        databtn = (Button) findViewById(R.id.databtn);
        imagesbtn = (Button) findViewById(R.id.imagesbtn);
        refsbtn = (Button) findViewById(R.id.refsbtn);
        Bundle bundle = getIntent().getExtras();
        String datares = bundle.getString("name");
        landtype1 = bundle.getString("landtype");
        dataResult.setText(datares);
        dataResult.append(landtype1);
    }


    public void gotoData(View view) {
        Toast.makeText(getApplicationContext(), "Already in data avtivity", Toast.LENGTH_SHORT).show();
    }

    public void gotoImages(View view) {
        Intent imagesintent = new Intent(this, imagesActivity.class);
        startActivity(imagesintent);
    }

    public void gotoRefs(View view) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String linksresult = databaseAccess.getLinks(landtype1);
        Toast.makeText(this, linksresult, Toast.LENGTH_SHORT).show();
        databaseAccess.close();

        Bundle linksbundle = new Bundle();
        linksbundle.putString("links",linksresult);
        linksbundle.putString("landtypes",landtype1);
        Intent linksintent = new Intent(this, refsActivity.class);
        linksintent.putExtras(linksbundle);
        startActivity(linksintent);
    }
}