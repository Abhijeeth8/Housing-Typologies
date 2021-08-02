package com.first.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class refsActivity extends AppCompatActivity {
    MainActivity ma;
    TextView linkHeading, linkResult;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refs);
        ma = new MainActivity();
        linkHeading = (TextView) findViewById(R.id.linkHead);
        linkResult = (TextView) findViewById(R.id.linkText);
        linkResult.setMovementMethod(LinkMovementMethod.getInstance());

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String linksresult = databaseAccess.getLinks(ma.land);
        Toast.makeText(this, linksresult, Toast.LENGTH_SHORT).show();
        databaseAccess.close();

        linkResult.append("\n" + linksresult);


    }

    public void gotoData(View view) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        ma = new MainActivity();
        String datafetched = databaseAccess.getData(ma.land);
        databaseAccess.close();

        Bundle bundle1 = new Bundle();
        bundle1.putString("name", datafetched);
        Intent intent = new Intent(this, dataActivity.class);
        intent.putExtras(bundle1);
        startActivity(intent);
    }

    public void gotoImages(View view) {
        Intent imagesIntent = new Intent(this, imagesActivity.class);
        startActivity(imagesIntent);
    }

    public void gotoRefs(View view) {
        Toast.makeText(getApplicationContext(), "Already in References avtivity", Toast.LENGTH_SHORT).show();
    }
}