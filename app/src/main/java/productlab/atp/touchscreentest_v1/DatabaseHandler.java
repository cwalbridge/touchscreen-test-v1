package productlab.atp.touchscreentest_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chelsea on 5/6/2016.
 * <p/>
 * This class handles the creation + management of the SQLite database logging information about all the touch events
 * 'heard' by the listeners on the screen.
 * Only one instance of the database can be created at a time in order to prevent overwriting and erasing data.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Name
    public static final String DATABASE_NAME = "MotionEventDatabase.db";
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Creating Tables
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EventEntry.TABLE_NAME + " (" +
                    EventEntry._ID + " INTEGER PRIMARY KEY," +
                    EventEntry.COLUMN_NAME_SUBJECT_ID + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_ADMIN_ID + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_TIME + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_RAW_X + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_RAW_Y + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_PRESSURE + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_EVENT_TYPE + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_LISTENER_ID + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_GESTURE_TYPE + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_ACTION_MASKED + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_SQUARE_DIM + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_IMG_DIM + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_TOUCH_DIM + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_DELEGATE_DIM + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_RATIO_DIM + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_ACTIVE_BUTTON + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_SPACE_DIM + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_MASTER_INDEX + TEXT_TYPE + COMMA_SEP +
                    EventEntry.COLUMN_NAME_SIZE + TEXT_TYPE +
                    " )";
    private static DatabaseHandler sInstance;
    //
    Globals globalVars = Globals.getInstance();


    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addEvent(LoggedEvent event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventEntry.COLUMN_NAME_SUBJECT_ID, event.getSubjectNumber()); // Subject ID Number
        values.put(EventEntry.COLUMN_NAME_ADMIN_ID, event.getAdminId());
        values.put(EventEntry.COLUMN_NAME_TIME, event.getTime());
        values.put(EventEntry.COLUMN_NAME_RAW_X, event.getRawX());
        values.put(EventEntry.COLUMN_NAME_RAW_Y, event.getRawY());
        values.put(EventEntry.COLUMN_NAME_PRESSURE, event.getPressure());
        values.put(EventEntry.COLUMN_NAME_EVENT_TYPE, event.getEventType());
        values.put(EventEntry.COLUMN_NAME_LISTENER_ID, event.getListenerId());
        values.put(EventEntry.COLUMN_NAME_GESTURE_TYPE, event.getGestureType());
        values.put(EventEntry.COLUMN_NAME_ACTION_MASKED, event.getActionMasked());
        values.put(EventEntry.COLUMN_NAME_SQUARE_DIM, event.getSquareDim());
        values.put(EventEntry.COLUMN_NAME_IMG_DIM, event.getImgDim());
        values.put(EventEntry.COLUMN_NAME_TOUCH_DIM, event.getTouchDim());
        values.put(EventEntry.COLUMN_NAME_DELEGATE_DIM, event.getDelegateDim());
        values.put(EventEntry.COLUMN_NAME_RATIO_DIM, event.getRatio());
        values.put(EventEntry.COLUMN_NAME_ACTIVE_BUTTON, event.getActiveButton());
        values.put(EventEntry.COLUMN_NAME_SPACE_DIM, event.getSpaceDim());
        values.put(EventEntry.COLUMN_NAME_MASTER_INDEX, event.getMasterIndex());
        values.put(EventEntry.COLUMN_NAME_SIZE, event.getSize());

        // Inserting Row
        long newRowId;
        newRowId = db.insert(EventEntry.TABLE_NAME, null, values);
        Log.i("DB", "The database added row " + newRowId);
        db.close(); // Closing database connection
    }

    // Getting All Contacts
    public List<LoggedEvent> getAllEvents() {
        List<LoggedEvent> eventList = new ArrayList<LoggedEvent>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + EventEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LoggedEvent event = new LoggedEvent();
                event.setID(Integer.parseInt(cursor.getString(0)));
                event.setSubjectNumber(cursor.getString(1));
                event.setAdminId(cursor.getString(2));
                event.setTime(cursor.getLong(3));
                event.setRawX(cursor.getFloat(4));
                event.setRawY(cursor.getFloat(5));
                event.setPressure(cursor.getFloat(6));
                event.setEventType(cursor.getInt(7));
                event.setListenerId(cursor.getString(8));
                event.setGestureType(cursor.getString(9));
                event.setActionMasked(cursor.getInt(10));
                event.setSquareDim(cursor.getInt(11));
                event.setImgDim(cursor.getInt(12));
                event.setTouchDim(cursor.getInt(13));
                event.setDelegateDim(cursor.getInt(14));
                event.setRatio(cursor.getFloat(15));
                event.setActiveButton(cursor.getInt(16));
                event.setSpaceDim(cursor.getFloat(17));
                event.setMasterIndex(cursor.getInt(18));
                event.setSize(cursor.getFloat(19));

                // Adding contact to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        // return contact list
        return eventList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + EventEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    //  Deleting database
    public void deleteDatabase(Context context) {
        context.deleteDatabase(getDatabaseName());
    }

    /* Define Table Contents*/
    public static abstract class EventEntry implements BaseColumns {

        // Events table name
        private static final String TABLE_NAME = "motion_event";

        // Logged Event Table Columns names
        private static final String COLUMN_NAME_SUBJECT_ID = "subject_id";
        private static final String COLUMN_NAME_ADMIN_ID = "admin_id";
        private static final String COLUMN_NAME_TIME = "time";
        private static final String COLUMN_NAME_RAW_X = "raw_x";
        private static final String COLUMN_NAME_RAW_Y = "raw_y";
        private static final String COLUMN_NAME_PRESSURE = "pressure";
        private static final String COLUMN_NAME_EVENT_TYPE = "event_type";
        private static final String COLUMN_NAME_LISTENER_ID = "listener_id";
        private static final String COLUMN_NAME_GESTURE_TYPE = "gesture_type";
        private static final String COLUMN_NAME_ACTION_MASKED = "action_masked";
        private static final String COLUMN_NAME_SQUARE_DIM = "square_dim";
        private static final String COLUMN_NAME_IMG_DIM = "img_dim";
        private static final String COLUMN_NAME_TOUCH_DIM = "touch_dim";
        private static final String COLUMN_NAME_DELEGATE_DIM = "delegate_dim";
        private static final String COLUMN_NAME_RATIO_DIM = "ratio";
        private static final String COLUMN_NAME_ACTIVE_BUTTON = "active_button";
        private static final String COLUMN_NAME_SPACE_DIM = "space_dim";
        private static final String COLUMN_NAME_MASTER_INDEX = "master_index";
        private static final String COLUMN_NAME_SIZE = "size";
    }

}
