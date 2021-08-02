package com.first.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class DatabaseAccess {
    private final SQLiteOpenHelper openHelper;
    public SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;
    Cursor a = null;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);

        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public String getData(String landtype) {
        c = db.rawQuery("select * from housingTable1 where LandType = '" + landtype + "'", new String[]{});
        c = db.rawQuery("select * from housingTable1 where LandType = '" + landtype + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String datarec = c.getString(2);
            String datarec2 = c.getString(3);
            buffer.append("data1\n").append(datarec).append("\n");
            buffer.append("data2\n").append(datarec2).append("\n");
        }
        return buffer.toString();
    }

    public Bitmap getImages(String landtype) {
        c = db.rawQuery("select * from housingTable1 where LandType = '" + landtype + "'", null);

        c.moveToNext();
        byte[] image1 = c.getBlob(4);
        c.close();

        Bitmap bitmapimage = BitmapFactory.decodeByteArray(image1, 0, image1.length);
        return bitmapimage  ;
    }
    public Bitmap getImages2(String landtype) {
        c = db.rawQuery("select * from housingTable1 where LandType = '" + landtype + "'", null);

        c.moveToNext();
        byte[] image2 = c.getBlob(5);
        c.close();

        Bitmap bitmapimage2 = BitmapFactory.decodeByteArray(image2, 0, image2.length);
        return bitmapimage2;
    }
    public Bitmap getImages3(String landtype) {
        c = db.rawQuery("select * from housingTable1 where LandType = '" + landtype + "'", null);

        c.moveToNext();
        byte[] image3 = c.getBlob(6);
        c.close();

        Bitmap bitmapimage3 = BitmapFactory.decodeByteArray(image3, 0, image3.length);
        return bitmapimage3;
    }
    public Bitmap getImages4(String landtype) {
        c = db.rawQuery("select * from housingTable1 where LandType = '" + landtype + "'", null);

        c.moveToNext();
        byte[] image4 = c.getBlob(7);
        c.close();

        Bitmap bitmapimage4 = BitmapFactory.decodeByteArray(image4, 0, image4.length);
        return bitmapimage4;
    }

    public String getLinks(String landtype) {
        c = db.rawQuery("select * from housingTable1 where LandType = '" + landtype + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String linkrec = c.getString(8);
            buffer.append("").append(linkrec).append("\n");
        }
        return buffer.toString();
    }


}
