package productlab.atp.touchscreentest_v1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by Chelsea on 5/13/2016.
 */
public class PracticeActivity extends AppCompatActivity {

    public static final String TAG = "PracticeActivity";
    public static final String FRAGTAG = "GestureDetectorClass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        // Set up "GestureDetectorClass" Main Activity
        // Sets up the listeners that sit "above" the UI and "listen" for
        // interaction from the user.
        if (getSupportFragmentManager().findFragmentByTag(FRAGTAG) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            GestureDetectorClass fragment = new GestureDetectorClass();
            transaction.add(fragment, FRAGTAG);
            transaction.commit();
        }

        updateUI();

    }

    //  Method checks global variables for next set of UI specifications, then updates the UI
    //  with the most recent set of dimensions. Then, it calls the delegates method to create
    //  touch dimensions larger than the button dimensions as appropriate.

    public void updateUI() {
        Log.i("MAIN PRACTICE UPDATEUI", "Running UpdateUI");

        //  Load instance of global variables
        Globals globalVars = Globals.getInstance();

        //  Global variables updates + calls
        globalVars.addToIndex();

        Integer mIndex = 1;                          //  Master index number
        if (mIndex == 4) {
            goToUserStart();
        }


        Integer randomIndex = globalVars.getRandomOrderElement(mIndex * 5);           //  Random row of dimensions to choose
        Double imgDim = globalVars.getFullDataArrayElement(randomIndex, 0);         //  Dimension of visual button
        int imgDimPX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Math.round(imgDim), getResources().getDisplayMetrics());  //getApplicationContext().getResources().getDimensionPixelSize((int) Math.round(imgDim));
        Double spaceDim = globalVars.getFullDataArrayElement(randomIndex, 1);       //  Dimension of space between buttons
        Double ratioTouchImg = globalVars.getFullDataArrayElement(randomIndex, 2);  //  Ratio of touch to visual
        Double touchDim = Math.sqrt(ratioTouchImg * Math.pow(imgDim, 2));           //  Dimension of touch target
        int touchDimPX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Math.round(touchDim), getResources().getDisplayMetrics());//getApplicationContext().getResources().getDimensionPixelSize((int) Math.round(touchDim));
        Double delegateDim = (touchDim - imgDim) / 2;                                 //  Dimension to be added to buttons
        double delegateDimPX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Math.round(delegateDim), getResources().getDisplayMetrics());//getApplicationContext().getResources().getDimensionPixelSize((int)Math.round(delegateDim));
        Double centerFrameDim = 2 * touchDim + (spaceDim - (touchDim - imgDim));            //  Dimension of square layout
        int centerFrameDimPX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Math.round(centerFrameDim), getResources().getDisplayMetrics());//getApplicationContext().getResources().getDimensionPixelSize((int) Math.round(centerFrameDim));
        Integer activeButton = globalVars.getActiveButton(mIndex * 5);             //  Which button should be active

        Log.i("MAIN UPDATEUI", "UI Stats:\nsquareView dim: " + Math.round(centerFrameDim) + "..." +
                centerFrameDimPX + "\nimgDim: " + Math.round(imgDim) + "..." + imgDimPX + "\ntouchDim: " + Math.round(touchDim) +
                "..." + touchDimPX + "\ndelegateDim: " + Math.round(delegateDim) + "..." + delegateDimPX +
                "\nratio: " + ratioTouchImg);

        globalVars.setDimensions("squareView dim: " + Math.round(centerFrameDim) +
                "\timgDim\t" + Math.round(imgDim) + "\ttouchDim\t" + Math.round(touchDim) +
                "\tdelegateDim\t" + Math.round(delegateDim) + "\tratio\t" + ratioTouchImg +
                "\tactiveButton\t" + activeButton + "\tspaceDim\t" + spaceDim);

        //  Update the view parameters
        Log.i("MAIN PRACTICE UPDATEUI", "Updating Dims");


        //  Update squareview - surrounding buttons + holders
        RelativeLayout squareView = (RelativeLayout) findViewById(R.id.active_layout);
        squareView.getLayoutParams().width = centerFrameDimPX;
        squareView.getLayoutParams().height = centerFrameDimPX;

        //  Update button holder views
        RelativeLayout bHolder1 = (RelativeLayout) findViewById(R.id.button1_holder);
        bHolder1.getLayoutParams().width = touchDimPX;
        bHolder1.getLayoutParams().height = touchDimPX;
        bHolder1.setLayoutParams(bHolder1.getLayoutParams());

        RelativeLayout bHolder2 = (RelativeLayout) findViewById(R.id.button2_holder);
        bHolder2.getLayoutParams().width = touchDimPX;
        bHolder2.getLayoutParams().height = touchDimPX;
        bHolder2.setLayoutParams(bHolder2.getLayoutParams());

        RelativeLayout bHolder3 = (RelativeLayout) findViewById(R.id.button3_holder);
        bHolder3.getLayoutParams().width = touchDimPX;
        bHolder3.getLayoutParams().height = touchDimPX;
        bHolder3.setLayoutParams(bHolder3.getLayoutParams());

        RelativeLayout bHolder4 = (RelativeLayout) findViewById(R.id.button4_holder);
        bHolder4.getLayoutParams().width = touchDimPX;
        bHolder4.getLayoutParams().height = touchDimPX;
        bHolder4.setLayoutParams(bHolder4.getLayoutParams());

        //  Update button dimensions
        Button b1 = (Button) findViewById(R.id.button1);
        b1.getLayoutParams().width = imgDimPX;
        b1.getLayoutParams().height = imgDimPX;
        b1.setLayoutParams(b1.getLayoutParams());

        Button b2 = (Button) findViewById(R.id.button2);
        b2.getLayoutParams().width = imgDimPX;
        b2.getLayoutParams().height = imgDimPX;
        b2.setLayoutParams(b2.getLayoutParams());

        Button b3 = (Button) findViewById(R.id.button3);
        b3.getLayoutParams().width = imgDimPX;
        b3.getLayoutParams().height = imgDimPX;
        b3.setLayoutParams(b3.getLayoutParams());

        Button b4 = (Button) findViewById(R.id.button4);
        b4.getLayoutParams().width = imgDimPX;
        b4.getLayoutParams().height = imgDimPX;
        b4.setLayoutParams(b4.getLayoutParams());

        Log.i("MAIN PRACTICE UPDATEUI", "Changing active button color");

        //  Change color of buttons using current activeButton
        switch (activeButton) {
            case 1:
                b1.setBackgroundColor(Color.parseColor("#2196F3"));
                b2.setBackgroundColor(Color.parseColor("#E3F2FD"));
                b3.setBackgroundColor(Color.parseColor("#E3F2FD"));
                b4.setBackgroundColor(Color.parseColor("#E3F2FD"));
                break;

            case 2:
                b1.setBackgroundColor(Color.parseColor("#E3F2FD"));
                b2.setBackgroundColor(Color.parseColor("#2196F3"));
                b3.setBackgroundColor(Color.parseColor("#E3F2FD"));
                b4.setBackgroundColor(Color.parseColor("#E3F2FD"));
                break;

            case 3:
                b1.setBackgroundColor(Color.parseColor("#E3F2FD"));
                b2.setBackgroundColor(Color.parseColor("#E3F2FD"));
                b3.setBackgroundColor(Color.parseColor("#2196F3"));
                b4.setBackgroundColor(Color.parseColor("#E3F2FD"));
                break;

            case 4:
                b1.setBackgroundColor(Color.parseColor("#E3F2FD"));
                b2.setBackgroundColor(Color.parseColor("#E3F2FD"));
                b3.setBackgroundColor(Color.parseColor("#E3F2FD"));
                b4.setBackgroundColor(Color.parseColor("#2196F3"));
                break;
        }


        Log.i("MAIN PRACTICE UPDATEUI", "Setting Delegates");

        //  Create larger touch targets
        setDelegates(delegateDimPX);


        Log.i("MAIN PRACTICE UPDATEUI", "Increasing Main Index to: " + mIndex);

    }

    //  Class that makes touch target larger than visual asset
    //  Input delegateDim: dimension to be added to each edge of the button view
    //
    public void setDelegates(Double delegateDim) {
        Log.i("MAIN SETDELEGATES", "Starting SetDelegates");

        // Get the parent view for button 1
        View parentView1 = findViewById(R.id.button1_holder);
        final double delegateDimension = delegateDim;

        parentView1.post(new Runnable() {
            // Post in the parent's message queue to make sure the parent
            // lays out its children before you call getHitRect()
            @Override
            public void run() {
                // The bounds for the delegate view (an ImageButton
                // in this example)
                Rect delegateArea1 = new Rect();
                Button myButton1 = (Button) findViewById(R.id.button1);
                myButton1.setEnabled(true);
                myButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //on Button 1 click...
                    }
                });

                // The hit rectangle for the ImageButton
                myButton1.getHitRect(delegateArea1);

                // Extend the touch area of the ImageButton beyond its bounds
                // on the right and bottom.
                delegateArea1.right += delegateDimension;
                delegateArea1.bottom += delegateDimension;
                delegateArea1.left -= delegateDimension;
                delegateArea1.top -= delegateDimension;

                // Instantiate a TouchDelegate.
                // "delegateArea" is the bounds in local coordinates of
                // the containing view to be mapped to the delegate view.
                // "myButton" is the child view that should receive motion
                // events.
                TouchDelegate touchDelegate1 = new TouchDelegate(delegateArea1, myButton1);

                // Sets the TouchDelegate on the parent view, such that touches
                // within the touch delegate bounds are routed to the child.
                if (View.class.isInstance(myButton1.getParent())) {
                    ((View) myButton1.getParent()).setTouchDelegate(touchDelegate1);
                }
            }
        });

        // Get the parent view
        View parentView2 = findViewById(R.id.button2_holder);

        parentView2.post(new Runnable() {
            // Post in the parent's message queue to make sure the parent
            // lays out its children before you call getHitRect()
            @Override
            public void run() {
                // The bounds for the delegate view (an ImageButton
                // in this example)
                Rect delegateArea2 = new Rect();
                Button myButton2 = (Button) findViewById(R.id.button2);
                myButton2.setEnabled(true);
                myButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //on Button 2 click...
                    }
                });

                // The hit rectangle for the ImageButton
                myButton2.getHitRect(delegateArea2);

                // Extend the touch area of the ImageButton beyond its bounds
                // on the right and bottom.
                delegateArea2.right += delegateDimension;
                delegateArea2.bottom += delegateDimension;
                delegateArea2.left -= delegateDimension;
                delegateArea2.top -= delegateDimension;

                // Instantiate a TouchDelegate.
                // "delegateArea" is the bounds in local coordinates of
                // the containing view to be mapped to the delegate view.
                // "myButton" is the child view that should receive motion
                // events.
                TouchDelegate touchDelegate2 = new TouchDelegate(delegateArea2, myButton2);

                // Sets the TouchDelegate on the parent view, such that touches
                // within the touch delegate bounds are routed to the child.
                if (View.class.isInstance(myButton2.getParent())) {
                    ((View) myButton2.getParent()).setTouchDelegate(touchDelegate2);
                }
            }
        });

        // Get the parent view
        View parentView3 = findViewById(R.id.button3_holder);

        parentView3.post(new Runnable() {
            // Post in the parent's message queue to make sure the parent
            // lays out its children before you call getHitRect()
            @Override
            public void run() {
                // The bounds for the delegate view (an ImageButton
                // in this example)
                Rect delegateArea3 = new Rect();
                Button myButton3 = (Button) findViewById(R.id.button3);
                myButton3.setEnabled(true);
                myButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //on Button 3 click...
                    }
                });

                // The hit rectangle for the ImageButton
                myButton3.getHitRect(delegateArea3);

                // Extend the touch area of the ImageButton beyond its bounds
                // on the right and bottom.
                delegateArea3.right += delegateDimension;
                delegateArea3.bottom += delegateDimension;
                delegateArea3.left -= delegateDimension;
                delegateArea3.top -= delegateDimension;

                // Instantiate a TouchDelegate.
                // "delegateArea" is the bounds in local coordinates of
                // the containing view to be mapped to the delegate view.
                // "myButton" is the child view that should receive motion
                // events.
                TouchDelegate touchDelegate3 = new TouchDelegate(delegateArea3, myButton3);

                // Sets the TouchDelegate on the parent view, such that touches
                // within the touch delegate bounds are routed to the child.
                if (View.class.isInstance(myButton3.getParent())) {
                    ((View) myButton3.getParent()).setTouchDelegate(touchDelegate3);
                }
            }
        });

        // Get the parent view
        View parentView4 = findViewById(R.id.button4_holder);

        parentView4.post(new Runnable() {
            // Post in the parent's message queue to make sure the parent
            // lays out its children before you call getHitRect()
            @Override
            public void run() {
                // The bounds for the delegate view (an ImageButton
                // in this example)
                Rect delegateArea4 = new Rect();
                Button myButton4 = (Button) findViewById(R.id.button4);
                myButton4.setEnabled(true);
                myButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //on Button 4 click..
                    }
                });

                // The hit rectangle for the ImageButton
                myButton4.getHitRect(delegateArea4);

                // Extend the touch area of the ImageButton beyond its bounds
                // on the right and bottom.
                delegateArea4.right += delegateDimension;
                delegateArea4.bottom += delegateDimension;
                delegateArea4.left -= delegateDimension;
                delegateArea4.top -= delegateDimension;

                // Instantiate a TouchDelegate.
                // "delegateArea" is the bounds in local coordinates of
                // the containing view to be mapped to the delegate view.
                // "myButton" is the child view that should receive motion
                // events.
                TouchDelegate touchDelegate4 = new TouchDelegate(delegateArea4, myButton4);

                // Sets the TouchDelegate on the parent view, such that touches
                // within the touch delegate bounds are routed to the child.
                if (View.class.isInstance(myButton4.getParent())) {
                    ((View) myButton4.getParent()).setTouchDelegate(touchDelegate4);
                }
            }
        });
        Log.i("MAIN PRACTICE UPDATEUI", "Finishing Delegates");

    }

    //  Sends intent to start finish class
    //  (triggered by button press)
    public void goToUserStart() {
        Intent intent = new Intent(this, UserStartActivity.class);
        startActivity(intent);
    }
}