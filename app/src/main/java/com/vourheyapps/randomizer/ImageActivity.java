package com.vourheyapps.randomizer;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vourhey on 8/9/15.
 */
// TODO add option to choose a folder
// TODO add gif support
public class ImageActivity extends Activity {
    private ArrayList<String> folderArray;
    private Map<String, ArrayList<String>> allImagesMap;
    private WebView imageView;
    private String currentFolder = "All";

    // for onTouch method
    private float mDownPosX;
    private float mDownPosY;
    private float mUpPosX;
    private float mUpPosY;
    private float MOVE_THRESHOLD_DP;

    private final String HTML = "<html><body style=\"background-color: transparent;\"><img src = \"file://%s\" /></body></html>";

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.image_activity);

        MOVE_THRESHOLD_DP = 20.0F * getResources().getDisplayMetrics().density;

        allImagesMap = getImagesMap();

        imageView = (WebView) findViewById(R.id.showRandomImage);
        imageView.getSettings().setAllowFileAccess(true);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownPosX = event.getX();
                    mDownPosY = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    mUpPosX = event.getX();
                    mUpPosY = event.getY();
                    if ((Math.abs(mUpPosX - mDownPosX) < MOVE_THRESHOLD_DP) && (Math.abs(mUpPosY - mDownPosY) < MOVE_THRESHOLD_DP)) {
                        Log.i("webview", "nextImage()");
                        nextImage(view);
                    }

                    break;
                }
                return false;
            }
        });

        Spinner chooseFolderSpinner = (Spinner) findViewById(R.id.chooseFolderSpinner);
        folderArray = new ArrayList<String>(allImagesMap.keySet());
        folderArray.add(0, "All");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, folderArray);
        chooseFolderSpinner.setAdapter(spinnerAdapter);
        chooseFolderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentFolder = adapterView.getItemAtPosition(i).toString();
                Log.i("Selected", currentFolder);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        nextImage(null);
    }

    private Map<String, ArrayList<String>> getImagesMap() {
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        // which image properties are we querying
        String[] projection = new String[]{
                MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        };

        // content:// style URI for the "primary" external storage volume
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        // Make the query.
        Cursor cur = getContentResolver().query(images,
                projection, // Which columns to return
                null,       // Which rows to return (all rows)
                null,       // Selection arguments (none)
                null        // Ordering
        );

        if (cur.moveToFirst()) {
            String path;
            int filePathColumn = cur.getColumnIndex(MediaStore.MediaColumns.DATA);
            int folderColumn = cur.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

            do {
                // Get the field values
                path = cur.getString(filePathColumn);

                String display = cur.getString(folderColumn);  // folder name
                Log.i("Path", display + " " + path);

                ArrayList<String> filesArray = map.get(display);
                if(filesArray != null) {
                    filesArray.add(path);
                } else {
                    filesArray = new ArrayList<String>();
                    filesArray.add(path);
                    map.put(display, filesArray);
                }
            } while (cur.moveToNext());
        }

        return map;
    }

    public void nextImage(View v) {
        int i;
        String image;
        String cf = currentFolder;
        if(cf.equals("All")) {
            i = MainActivity.random.nextInt(folderArray.size() - 1) + 1;
            cf = folderArray.get(i);
        }

        ArrayList<String> images = allImagesMap.get(cf);
        i = MainActivity.random.nextInt(images.size());
        image = images.get(i);

        String html = String.format(HTML, image);
        Log.i("Html", html);
        imageView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        imageView.loadDataWithBaseURL("", html, "text/html", "UTF-8", "");
    }
}
