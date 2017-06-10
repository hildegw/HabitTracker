package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//DB helper class to access DB from Activities
public class DiveDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DiveLog.db";

    //constructor
    public DiveDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //required methods to override
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL command string
        String SQL_CREATE =
                "CREATE TABLE " +  DiveContract.DiveLog.TABLE_NAME + " (" +
                        DiveContract.DiveLog._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DiveContract.DiveLog.DIVE_SITE + " TEXT NOT NULL," +
                        DiveContract.DiveLog.DIVE_TYPE + " INTEGER NOT NULL," +
                        DiveContract.DiveLog.DEPTH + " INTEGER," +
                        DiveContract.DiveLog.DIVE_TIME + " INTEGER)";
        db.execSQL(SQL_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //SQL command string
        String SQL_UPGRADE =
                "DROP TABLE IF EXISTS" + DiveContract.DiveLog.TABLE_NAME;
        db.execSQL(SQL_UPGRADE);
        onCreate(db);
    }
}
