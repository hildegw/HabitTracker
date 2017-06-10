package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import data.DiveContract;
import data.DiveDbHelper;

import static data.DiveContract.DiveLog.TYPE_CAVE;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    DiveDbHelper mDiveDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //open helper instance to access DB
        mDiveDbHelper = new DiveDbHelper(this);
    }

    private void insertDive() {
        //test data for a dummy Dive, todo: get user input instead
        String diveSite = "Nohoch Na Chich";
        int diveType = TYPE_CAVE;
        int depth = 8;
        int time = 215;

        //get writable db instance
        SQLiteDatabase db = mDiveDbHelper.getWritableDatabase();

        //pass dive data into values of ContentValues class
        ContentValues values = new ContentValues();
        values.put(DiveContract.DiveLog.DIVE_SITE, diveSite);
        values.put(DiveContract.DiveLog.DIVE_TYPE, diveType);
        values.put(DiveContract.DiveLog.DEPTH, depth);
        values.put(DiveContract.DiveLog.DIVE_TIME, time);

        //insert the dive data
        long newRowId = db.insert(DiveContract.DiveLog.TABLE_NAME, null, values);

        //show toast message showing success/failure to add data
        if (newRowId > -1) {
            Toast.makeText(this, "Dive was added to database", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Dive could not be added to database", Toast.LENGTH_LONG).show();
        }
    }

    private void readCaveDives() {
        //get readable db instance
        SQLiteDatabase db = mDiveDbHelper.getReadableDatabase();

        //define projection to query DB, here: all columns
        String[] projection = {
                DiveContract.DiveLog._ID,
                DiveContract.DiveLog.DIVE_SITE,
                DiveContract.DiveLog.DIVE_TYPE,
                DiveContract.DiveLog.DEPTH,
                DiveContract.DiveLog.DIVE_TIME};

        //setting up "where" clause to select only cave dives
        String whereClause = "DIVE_TYPE = ?";
        String[] whereArgs = new String[]{valueOf(DiveContract.DiveLog.TYPE_CAVE)};

        //Instantiate a curser object containing colum projection and "where" constraint
        Cursor cursor = db.query(DiveContract.DiveLog.TABLE_NAME, projection,
                whereClause, whereArgs, null, null, null);

        //Displaying the cave dives, todo: add UI to enter more dives
        TextView displayCaveDives = (TextView) findViewById(R.id.text);
        try {
            displayCaveDives.setText("# of Cave Dives: " + cursor.getCount());
            displayCaveDives.append(DiveContract.DiveLog._ID + ", " +
                    DiveContract.DiveLog.DIVE_SITE + ", " +
                    DiveContract.DiveLog.DEPTH + ", " +
                    DiveContract.DiveLog.DIVE_TIME + "\n");

            //Loop through cursor object to obtain the data
            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(cursor.getColumnIndex(DiveContract.DiveLog._ID));
                String currentDiveSite = cursor.getString(cursor.getColumnIndex(DiveContract.DiveLog.DIVE_SITE));
                int currentDepth = cursor.getInt(cursor.getColumnIndex(DiveContract.DiveLog.DEPTH));
                int currenTime = cursor.getInt(cursor.getColumnIndex(DiveContract.DiveLog.DIVE_TIME));

                //and append it to the display
                displayCaveDives.append(("\n" + currentId + ", " +
                        currentDiveSite + ", " +
                        currentDepth + ", " +
                        currenTime));
            }
        } finally {
            cursor.close();
        }
    }
}



