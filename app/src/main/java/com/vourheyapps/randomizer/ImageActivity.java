package com.vourheyapps.randomizer;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by vourhey on 8/9/15.
 */
// TODO add option to choose a folder
// TODO add gif support
public class ImageActivity extends Activity {
    private ArrayList<String> allImagesPath;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.image_activity);

        allImagesPath = getAllImagesPath();
        imageView = (ImageView) findViewById(R.id.showRandomImage);
    }

    private ArrayList<String> getAllImagesPath() {
        ArrayList<String> paths = new ArrayList<String>();

        // which image properties are we querying
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.MediaColumns.DATA
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
            int dataColumn = cur.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

            do {
                // Get the field values
                path = cur.getString(dataColumn);

                paths.add(path);
            } while (cur.moveToNext());
        }

        Log.i("Image paths size", "" + paths.size());
        return paths;
    }

    public void nextImage(View v) {
        Log.i("Image", "" + allImagesPath.size());
        int i = MainActivity.random.nextInt(allImagesPath.size());

        imageView.setImageBitmap(BitmapFactory.decodeFile(allImagesPath.get(i)));
        imageView.invalidate();
    }
}
