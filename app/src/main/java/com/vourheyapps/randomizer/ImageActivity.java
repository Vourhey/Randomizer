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
                MediaStore.MediaColumns.DATA/*,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME */
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
            int bucketColumn = cur.getColumnIndex(MediaStore.MediaColumns.DATA);
            //int displayColumn = cur.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

            do {
                // Get the field values
                path = cur.getString(bucketColumn);

                //String display = cur.getString(displayColumn);  // folder name

                paths.add(path);
            } while (cur.moveToNext());
        }

        return paths;
    }

    public void nextImage(View v) {
        Log.i("Image", "" + allImagesPath.size());
        int i = MainActivity.random.nextInt(allImagesPath.size());

        int imageHeight = imageView.getHeight();
        int imageWidth = imageView.getWidth();
        imageView.setImageBitmap(decodeSampledBitmap(allImagesPath.get(i), imageWidth, imageHeight));
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
