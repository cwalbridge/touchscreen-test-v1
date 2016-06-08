package productlab.atp.touchscreentest_v1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;


/**
 * Created by Chelsea on 5/6/2016.
 * Requests external write access from user; saves database and confirms to user that task is complete.
 */
public class FinishActivity extends Activity {

    private final static int WRITE_EXTERNAL_RESULT = 105;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> permissionsToRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Log.i("MAIN ONCREATE", "Running OnCreate");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }


    public void saveDatabase(View view) {

        //  Gets state of external storage on device (needs to be "mounted")
        String storageState = Environment.getExternalStorageState();

        //  Check if external storage writing permission has been granted
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //  If permission has not been granted, ask for it
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }

        //  Try to save the database file
        try {

            File sd = Environment.getExternalStorageDirectory().getAbsoluteFile();
            File data = Environment.getDataDirectory();

            if (storageState.equals("mounted")) {
                String currentDBPath = "//data//" + getString(R.string.package_name) + "//databases//" + getString(R.string.database_name);
                String backupDBPath = "/" + getString(R.string.database_name);
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();

                    //  If the database was saved, add a message to the UI confirming save

                    Snackbar savedsnackbar = Snackbar
                            .make(view, "Database Saved at: " + backupDBPath, Snackbar.LENGTH_INDEFINITE);
                    savedsnackbar.show();
                    Log.i("FINISH", "Database saved at: " + backupDBPath);


                    //  Send database in email
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"chelsea.daniels@ucdenver.edu"});
                    i.putExtra(Intent.EXTRA_SUBJECT, "Event Data Database Sending");
                    i.setType("application/octet-stream");
                    i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(backupDB));
                    startActivity(Intent.createChooser(i, "Send e-mail"));
                }
            }
            //  If error is thrown, show a toast saying database has not been saved and don't update UI
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Database save unsuccessful. Export before next subject",
                    Toast.LENGTH_SHORT).show();
        }


    }


    //  NOT CURRENTLY USED: to delete database after saving
    private void deleteDatabase() {
        getApplicationContext().deleteDatabase(DatabaseHandler.DATABASE_NAME);
    }

    //  If user grants permission request, update the system content to show permission granted
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        switch (permsRequestCode) {

            case WRITE_EXTERNAL_RESULT:

                boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;

        }

    }
}
