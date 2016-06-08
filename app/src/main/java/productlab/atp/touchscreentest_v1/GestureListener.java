package productlab.atp.touchscreentest_v1;

/**
 * Created by Chelsea on 5/3/2016.
 * Extends: SimpleOnGestureListener
 */

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    public static final String TAG = "GestureListener";
    final Context mContext;
    public String gestureType;

    public GestureListener(Context context) {
        mContext = context;
    }

    /**
     * Returns a human-readable string describing the type of touch that triggered a MotionEvent.
     * Updates global variables and passes data into the database as appropriate.
     */
    private static void getTouchType(MotionEvent e, Context mContext, String gestureType) {

        String touchTypeToString = " ";
        int touchType = e.getToolType(0);
        Globals globalVars = Globals.getInstance();
        DatabaseHandler db = DatabaseHandler.getInstance(mContext);


        switch (touchType) {
            case MotionEvent.TOOL_TYPE_FINGER:

                float fingerPressure = e.getPressure();
                float rawX = e.getRawX();
                float rawY = e.getRawY();
                float eSize = e.getSize();
                int actionMasked = e.getActionMasked();
                String subjectId = globalVars.getSubjectId();
                String adminId = globalVars.getAdminId();
                touchTypeToString += e.toString();

                //  Logs event (for debugging)
                Log.i(TAG, "Subject ID: " + subjectId + "\nTime: " + System.currentTimeMillis() + "\nTool: " + touchType + "\nPressure: " + fingerPressure
                        + "\nX: " + rawX + "\nY: " + rawY + "\nToString: " + touchTypeToString);

                //  Creates object + enters information into the databasers
                LoggedEvent loggedEvent = new LoggedEvent(subjectId, adminId, System.currentTimeMillis(), fingerPressure, rawX, rawY, touchType,
                        globalVars.getTriggeredListener(), gestureType, actionMasked, globalVars.getSquareDim(),
                        globalVars.getImgDim(), globalVars.getTouchDim(), globalVars.getDelegateDim(), globalVars.getRatio(),
                        globalVars.getActiveButtonValue(), globalVars.getSpaceDim(), globalVars.getMasterIndex(), eSize);

                db.addEvent(loggedEvent);

                //  If the event is a 2D gesture, there is more than one event grouped into the gesture.
                //  Pull out information about all of these events.
                final int historySize = e.getHistorySize();
                final int pointerCount = e.getPointerCount();

                if (historySize > 1) {
                    for (int h = 0; h < historySize; h++) {
                        Log.i(TAG, "Time: " + e.getHistoricalEventTime(h));
                        for (int p = 0; p < pointerCount; p++) {
                            Log.i(TAG, "  pointer " + e.getPointerId(p) + "\nHisX" + e.getHistoricalX(p, h) +
                                    "\nHisY" + e.getHistoricalY(p, h));
                        }
                    }
                }

                break;

            default:
                Log.i("GESTURE LISTENER", "(unknown tool)");
                break;
        }
    }

    // BEGIN_INCLUDE(init_gestureListener)
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // Up motion completing a single tap occurred.
        Log.i(TAG, "Single Tap Up");
        gestureType = "Single Tap Up";
        getTouchType(e, mContext, gestureType);

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // Touch has been long enough to indicate a long press.
        // Does not indicate motion is complete yet (no up event necessarily)
        Log.i(TAG, "Long Press");
        gestureType = "Long Press";
        getTouchType(e, mContext, gestureType);

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // User attempted to scroll
        Log.i(TAG, "Scroll");
        gestureType = "Scroll";
        getTouchType(e1, mContext, gestureType);
        getTouchType(e2, mContext, gestureType);
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // Fling event occurred.  Notification of this one happens after an "up" event.
        Log.i(TAG, "Fling");
        gestureType = "Fling";
        getTouchType(e1, mContext, gestureType);
        getTouchType(e2, mContext, gestureType);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // User performed a down event, and hasn't moved yet.
        Log.i(TAG, "Show Press");
        gestureType = "Show Press";
        getTouchType(e, mContext, gestureType);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // "Down" event - User touched the screen.
        Log.i(TAG, "Down");
        gestureType = "Down";
        getTouchType(e, mContext, gestureType);
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        // User tapped the screen twice.
        Log.i(TAG, "Double tap");
        gestureType = "Double tap";
        getTouchType(e, mContext, gestureType);
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        // Since double-tap is actually several events which are considered one aggregate
        // gesture, there's a separate callback for an individual event within the doubletap
        // occurring.  This occurs for down, up, and move.
        Log.i(TAG, "Event within double tap");
        gestureType = "Event within double tap";
        getTouchType(e, mContext, gestureType);
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        // A confirmed single-tap event has occurred.  Only called when the detector has
        // determined that the first tap stands alone, and is not part of a double tap.
        Log.i(TAG, "Single tap confirmed");
        gestureType = "Single Tap confirmed";
        return false;
    }


}

