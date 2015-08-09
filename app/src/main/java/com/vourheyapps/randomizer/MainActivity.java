package com.vourheyapps.randomizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by vourhey on 8/1/15.
 */
public class MainActivity extends Activity {
    public final static String VIEWID_MESSAGE = "com.vourheyapps.randomizer.VIEWID";

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.main_layout);
    }

    public void openActivity(View v) {
        Intent intent = new Intent(this, CommonActivity.class);
        intent.putExtra(VIEWID_MESSAGE, v.getId());
        startActivity(intent);
    }

    public void openListActivity(View v) {
        Intent intent = new Intent(this, RpsActivity.class);
        startActivity(intent);
    }

    public void openDiceRollActivity(View v) {
        Intent intent = new Intent(this, RpsActivity.class);
        startActivity(intent);
    }

    public void openCoinFlipActivity(View v) {
        Intent intent = new Intent(this, RpsActivity.class);
        startActivity(intent);
    }

    public void openImageActivity(View v) {
        Intent intent = new Intent(this, RpsActivity.class);
        startActivity(intent);
    }

    public void openCardActivity(View v) {
        Intent intent = new Intent(this, CardActivity.class);
        startActivity(intent);
    }

    public void openRpsActivity(View v) {
        Intent intent = new Intent(this, RpsActivity.class);
        startActivity(intent);
    }

}
