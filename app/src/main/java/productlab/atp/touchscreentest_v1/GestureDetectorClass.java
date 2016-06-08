package productlab.atp.touchscreentest_v1;

/**
 * Created by Chelsea on 5/3/2016.
 * Extends: Fragment
 * <p/>
 * Creates a gesture listener (see GestureListener class), then tells the app what to do when listeners are triggered by the user.
 * The gesture detector takes different actions based on which gesture listener was triggered.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class GestureDetectorClass extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.i("GESTUREDETECTOR", "Running OnCreate in GestureDetector");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        Log.i("GESTUREDETECTOR", "Running onActivityCreated in GestureDetector");
        super.onActivityCreated(savedInstanceState);

        // create the GestureListener that will include all callbacks
        GestureDetector.SimpleOnGestureListener gestureListener = new GestureListener(this.getContext());

        // Then create the GestureDetector, which takes that listener as an argument
        final GestureDetector gd = new GestureDetector(getActivity(), gestureListener);

        //  Set listener on background view
        View backgroundView = getActivity().findViewById(R.id.main_layout);
        backgroundView.setClickable(true);
        backgroundView.setFocusable(true);
        backgroundView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Globals globalVars = Globals.getInstance();
                globalVars.setTriggeredListener("0");
                gd.onTouchEvent(motionEvent);
                Log.i("BACKGROUND", "off target touch");

                // If the motion event is an "UP" event, a gesture is ending.
                // Update the UI with the next task (i.e. update masterIndex and move to the next row of data)
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    nextUI();
                }

                return false;
            }
        });

        //  Set listener on Button 1
        final View buttonView1 = getActivity().findViewById(R.id.button1);
        buttonView1.setClickable(true);
        buttonView1.setFocusable(true);

        buttonView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Globals globalVars = Globals.getInstance();
                globalVars.setTriggeredListener("1");
                gd.onTouchEvent(motionEvent);
                Log.i("BUTTON 1", "target 1 touch");

                // If the motion event is an "UP" event, a gesture is ending.
                // Update the UI with the next task (i.e. update masterIndex and move to the next row of data)
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    nextUI();
                }
                return false;
            }
        });

        //  Set listener on Button 2
        final View buttonView2 = getActivity().findViewById(R.id.button2);
        buttonView2.setClickable(true);
        buttonView2.setFocusable(true);

        buttonView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Globals globalVars = Globals.getInstance();
                globalVars.setTriggeredListener("2");
                gd.onTouchEvent(motionEvent);
                Log.i("BUTTON 2", "target 2 touch");

                // If the motion event is an "UP" event, a gesture is ending.
                // Update the UI with the next task (i.e. update masterIndex and move to the next row of data)
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    nextUI();

                }
                return false;
            }
        });

        final View buttonView3 = getActivity().findViewById(R.id.button3);
        buttonView3.setClickable(true);
        buttonView3.setFocusable(true);

        buttonView3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Globals globalVars = Globals.getInstance();
                globalVars.setTriggeredListener("3");
                gd.onTouchEvent(motionEvent);
                Log.i("BUTTON 3", "target 3 touch");

                // If the motion event is an "UP" event, a gesture is ending.
                // Update the UI with the next task (i.e. update masterIndex and move to the next row of data)
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    nextUI();
                }
                return false;
            }
        });

        final View buttonView4 = getActivity().findViewById(R.id.button4);
        buttonView4.setClickable(true);
        buttonView4.setFocusable(true);

        buttonView4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Globals globalVars = Globals.getInstance();
                globalVars.setTriggeredListener("4");
                gd.onTouchEvent(motionEvent);
                Log.i("BUTTON 4", "target 4 touch");

                // If the motion event is an "UP" event, a gesture is ending.
                // Update the UI with the next task (i.e. update masterIndex and move to the next row of data)
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    nextUI();
                }
                return false;
            }
        });

    }

    public void nextUI() {
        Globals globalVars = Globals.getInstance();
        Integer masterIndex = globalVars.getMasterIndex();
        Integer totalRuns = globalVars.getRandomOrder().size();

        if (masterIndex >= totalRuns) {
            ((MainActivity) getActivity()).goToFinish();
        } else {
            ((MainActivity) getActivity()).updateUI();
        }
    }

}

