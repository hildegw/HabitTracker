package data;

import android.provider.BaseColumns;

public final class DiveContract {

    //constructor
    private DiveContract() {};

    //DB columns and values
    public static final class DiveLog implements BaseColumns {
        public static final String TABLE_NAME = "diveLog";
        public static final String _ID = BaseColumns._ID;
        public static final String DIVE_SITE = "dive site";
        public static final String DIVE_TYPE = "dive type";
        public static final String DEPTH = "depth";
        public static final String DIVE_TIME = "dive time";

        //values available for dive type
        public static final int TYPE_OPEN_WATER = 0;
        public static final int TYPE_CAVE = 1;
        public static final int TYPE_TECH = 2;
    }
}
