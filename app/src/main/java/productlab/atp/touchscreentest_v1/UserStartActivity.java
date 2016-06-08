package productlab.atp.touchscreentest_v1;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;

/**
 * Created by Chelsea on 5/7/2016.
 */
public class UserStartActivity extends AppCompatActivity {

    public static final String FRAGTAG = "GestureDetectorClass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_start);
        Log.i("USER START ONCREATE", "Running OnCreate");


    }

    //  Sends intent to start Main Activity
    //  (triggered by button press)
    public void userStartTest(View view) {

        DatabaseHandler db = DatabaseHandler.getInstance(getApplicationContext());
        Globals globalVars = Globals.getInstance();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //  Creates object + enters information into the database
        LoggedEvent loggedEvent = new LoggedEvent(globalVars.getSubjectId(), globalVars.getAdminId()
                , System.currentTimeMillis(), (float) 0.0, (float) width, (float) height, 0,
                "Start Event", "", 1, 0, 0, 0, 0, (float) 0.0, 0, (float) 0.0, 0, (float) 0.0);
        db.addEvent(loggedEvent);
        globalVars.setPractice(false);
        globalVars.setMasterIndex(0);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
