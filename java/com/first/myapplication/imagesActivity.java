package com.first.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class imagesActivity extends AppCompatActivity {

    MainActivity ma;
    ImageView image1, image2, image3, image4;
    TextView imageHead;
    Button databtn,imagesbtn,refsbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        databtn =(Button) findViewById(R.id.databtn);
        imagesbtn =(Button) findViewById(R.id.imagesbtn);
        imageHead = (TextView) findViewById(R.id.imagesHead);
        refsbtn =(Button) findViewById(R.id.refsbtn);
        image1 = (ImageView) findViewById(R.id.img1);
        image2 = (ImageView) findViewById(R.id.img2);
        image3 = (ImageView) findViewById(R.id.img3);
        image4 = (ImageView) findViewById(R.id.img4);
        ma = new MainActivity();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Bitmap imagesresult1 = databaseAccess.getImages(ma.land);
        Bitmap imagesresult2 = databaseAccess.getImages2(ma.land);
        Bitmap imagesresult3 = databaseAccess.getImages3(ma.land);
        Bitmap imagesresult4 = databaseAccess.getImages4(ma.land);
        image1.setImageBitmap(imagesresult1);
        image2.setImageBitmap(imagesresult2);
        image3.setImageBitmap(imagesresult3);
        image4.setImageBitmap(imagesresult4);
        databaseAccess.close();    }

    public void gotoData(View view) {

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        ma = new MainActivity();
        String ans = ma.land;
        String datafetched = databaseAccess.getData(ans);
        databaseAccess.close();

        Bundle bundle = new Bundle();
        bundle.putString("name", datafetched);
        Intent intent = new Intent(this, dataActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void gotoImages(View view) {
        Toast.makeText(getApplicationContext(), "Already in Images avtivity", Toast.LENGTH_SHORT).show();
    }

    public void gotoRefs(View view) {

        Intent refsIntent = new Intent(this, refsActivity.class);
        startActivity(refsIntent);
    }
}