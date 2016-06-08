package productlab.atp.touchscreentest_v1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Chelsea on 5/6/2016.
 * Creates login screen for administrator to enter subject ID number
 */

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i("MAIN ONCREATE", "Running OnCreate");

        //  Create globals instance so global variables can be called throughout the application
        Globals globalVars = Globals.getInstance();

        // Set original values of dimensions
        globalVars.setImgDimArray();
        globalVars.setSpaceDimArray();
        globalVars.setRatioTouchImgArray();
        globalVars.setActiveButtonArray();
        globalVars.setPractice(true);

        //  Create large array of permutated data
        globalVars.setFullDataArray();

        //  Create arraylist of integers 0 through 64*3-1,
        // then shuffle order and assign to global variable
        ArrayList<Integer> randomOrder = new ArrayList<>();
        for (int i = 0; i < 64 * 3; i++) {
            randomOrder.add(i);
        }
        Collections.shuffle(randomOrder);
        globalVars.setRandomOrder(randomOrder);

        //  Create arraylist of integers 1-4, then create larger array of integers in random order
        ArrayList<Integer> randomOrderButtons = new ArrayList();
        Integer[] buttonIds = new Integer[4];
        for (int i = 0; i < 4; i++) {
            buttonIds[i] = i + 1;
        }
        int randomInt;
        for (int i = 0; i < 64 * 3; i++) {
            randomInt = new Random().nextInt(4);
            randomOrderButtons.add(buttonIds[randomInt]);
        }
        globalVars.setRandomActiveButtonsArray(randomOrderButtons);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send_database) {

            //  Try to send the database file
            try {

                File sd = Environment.getExternalStorageDirectory().getAbsoluteFile();

                String backupDBPath = "/" + getString(R.string.database_name);

                File backupDB = new File(sd, backupDBPath);


                //  Send database in email
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"chelsea.daniels@ucdenver.edu"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Event Data Database Sending");
                i.setType("application/octet-stream");
                i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(backupDB));
                startActivity(Intent.createChooser(i, "Send e-mail"));


                //  If error is thrown, show a toast saying database has not been saved and don't update UI
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Database save unsuccessful. Export before next subject",
                        Toast.LENGTH_SHORT).show();
            }


        }

        if (id == R.id.delete_database) {
            deleteDatabase();
        }

        if (id == R.id.change_email) {

            //Globals.setEmail()
        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteDatabase() {
        DatabaseHandler db = DatabaseHandler.getInstance(getApplicationContext());
        db.deleteDatabase(getApplicationContext());
        Snackbar snackbar = Snackbar
                .make(getWindow().getDecorView(), "Database Deleted", Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    //  Method gets subject ID from editText field in layout, then starts UserStartActivity with Intent
    public void startTest(View view) {

        //  Set global variable instance so that subject ID can be assigned
        Globals globalVars = Globals.getInstance();

        // Get user's ID # and admin's name
        EditText subjectIdField = (EditText) findViewById(R.id.subject_id_field);
        Editable subjectIdEditable = subjectIdField.getText();
        String subjectId = subjectIdEditable.toString();
        EditText adminIdField = (EditText) findViewById(R.id.admin_id_field);
        Editable adminIdEditable = adminIdField.getText();
        String adminId = adminIdEditable.toString();
        globalVars.setSubjectId(subjectId);
        globalVars.setAdminId(adminId);

        //  Send intent to start UserStartActivity
        Intent intent = new Intent(this, UserPracticeActivity.class);
        startActivity(intent);
    }
}
