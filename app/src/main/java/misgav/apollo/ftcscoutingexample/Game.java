package misgav.apollo.ftcscoutingexample;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import static java.lang.System.in;
import static java.security.AccessController.getContext;

public class Game extends AppCompatActivity {

    int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        verifyStoragePermissions(this);

        final Button button = (Button) findViewById(R.id.new_game);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveFile("Hello World!");
            }
        });

        buttonHandler(R.id.box_auto_1_1);
        buttonHandler(R.id.box_auto_1_2);
        buttonHandler(R.id.box_auto_1_3);

        buttonHandler(R.id.box_auto_2_1);
        buttonHandler(R.id.box_auto_2_2);
        buttonHandler(R.id.box_auto_2_3);

        buttonHandler(R.id.box_auto_3_1);
        buttonHandler(R.id.box_auto_3_2);
        buttonHandler(R.id.box_auto_3_3);

        buttonHandler(R.id.box_auto_4_1);
        buttonHandler(R.id.box_auto_4_2);
        buttonHandler(R.id.box_auto_4_3);


        buttonHandler(R.id.box_a_1_1);
        buttonHandler(R.id.box_a_1_2);
        buttonHandler(R.id.box_a_1_3);

        buttonHandler(R.id.box_a_2_1);
        buttonHandler(R.id.box_a_2_2);
        buttonHandler(R.id.box_a_2_3);

        buttonHandler(R.id.box_a_3_1);
        buttonHandler(R.id.box_a_3_2);
        buttonHandler(R.id.box_a_3_3);

        buttonHandler(R.id.box_a_4_1);
        buttonHandler(R.id.box_a_4_2);
        buttonHandler(R.id.box_a_4_3);

        buttonHandler(R.id.box_b_1_1);
        buttonHandler(R.id.box_b_1_2);
        buttonHandler(R.id.box_b_1_3);

        buttonHandler(R.id.box_b_2_1);
        buttonHandler(R.id.box_b_2_2);
        buttonHandler(R.id.box_b_2_3);

        buttonHandler(R.id.box_b_3_1);
        buttonHandler(R.id.box_b_3_2);
        buttonHandler(R.id.box_b_3_3);

        buttonHandler(R.id.box_b_4_1);
        buttonHandler(R.id.box_b_4_2);
        buttonHandler(R.id.box_b_4_3);


    }

    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public synchronized static String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }
        return uniqueID;


    }



    public File getApolloStorageDir() {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "apollo");
        if (!file.mkdirs()) {
            Log.e("", "Directory not created");
        }
        return file;
    }



    private File getApolloFile() {
        return new File(getApolloStorageDir(), "games_" + id(getApplicationContext())  + ".csv");

    }


    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    private void saveFile(String content) {

/*

        String fileName = "test.csv";
        String headings = "Hello, world!";
        File path = Environment.getExternalStorageDirectory();

        //create path
        File directoryFile = new File(Environment.getExternalStorageDirectory(), "");
        directoryFile.mkdirs();

//create file
        File outFile = new File(Environment.getExternalStorageDirectory(), fileName);

        File file = new File(path, fileName);
        path.mkdirs();

        OutputStream os = null;
        try {
            os = new FileOutputStream(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            os.write(headings.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


*/
        File file = getApolloFile();
        if (file.exists ())
            file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(content.getBytes());
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*
    private void loadFile() {

        try {
            File file = new File(getFileName());
            FileInputStream in = openFileInput(getFileName());
        } catch (FileNotFoundException e) {
            File file = new File(filename);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {

        }
    }

    private void saveToFile() {
        String filename = "games_" + id(getApplicationContext());
        try {
            FileInputStream in = openFileInput(filename);
        } catch (FileNotFoundException e) {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {

        }
    }
    */

    private void buttonHandler(@IdRes int id) {
        final Button button = (Button) findViewById(id);
        //button.setBackgroundColor(Color.rgb(211,211,211));
        Integer filter = 0xf5f2d0;
        LightingColorFilter darken = new LightingColorFilter(filter, 0x000000);
        button.getBackground().setColorFilter(darken);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (c) {
                    case 0:
                        c=1;
                        //button.setBackgroundColor(Color.rgb(128,128,128));
                        Integer filter = 0xA52A2A;
                        LightingColorFilter darken = new LightingColorFilter(filter, 0x000000);
                        button.getBackground().setColorFilter(darken);
                        break;
                    case 1:
                        c=2;
                        filter = 0xA9A9A9;
                        darken = new LightingColorFilter(filter, 0x000000);
                        button.getBackground().setColorFilter(darken);
                        // button.setBackgroundColor(Color.rgb(165,42,42));
                        break;
                    default:
                        c=0;
                        //button.setBackgroundColor(Color.rgb(211,211,211));

                        filter = 0xf5f2d0;
                        darken = new LightingColorFilter(filter, 0x000000);
                        button.getBackground().setColorFilter(darken);

                        break;
                }

            }
        });

    }

}
