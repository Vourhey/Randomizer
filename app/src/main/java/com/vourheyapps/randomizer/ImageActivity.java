package com.vourheyapps.randomizer;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by vourhey on 8/9/15.
 */
// TODO add option to choose a folder
// TODO add gif support
public class ImageActivity extends Activity {
    private ArrayList<String> folderArray;
    private Map<String, ArrayList<String>> allImagesMap;
    private ImageView imageView;
    private String currentFolder;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.image_activity);

        imageView = (ImageView) findViewById(R.id.showRandomImage);
        allImagesMap = getImagesMap();

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

        int imageHeight = imageView.getHeight();
        int imageWidth = imageView.getWidth();
        imageView.setImageBitmap(decodeSampledBitmap(image, imageWidth, imageHeight));
        imageView.invalidate();
    }

    private Bitmap decodeSampledBitmap(String path, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        Log.i("inSampleSize", "" + inSampleSize);
        return inSampleSize;
    }
}
