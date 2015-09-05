package com.vourheyapps.randomizer;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vourhey on 8/16/15.
 */
public class DiceRollActivity extends Activity {
    private int numberOfDice;
    private Drawable sides[];
    private GridView gridView;
    private ImageAdapter adapter;
    private Random random;

    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.diceroll_activity);

        Spinner numberOfDiceSpinner = (Spinner) findViewById(R.id.numberOfDiceSpinner);
        List<Integer> spinnerArray = new ArrayList<Integer>();
        for(int i = 1; i < 10; ++i) {
            spinnerArray.add(i);
        }
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfDiceSpinner.setAdapter(spinnerAdapter);
        numberOfDiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numberOfDice = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        numberOfDice = 1;

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);

        sides = new Drawable[6];
        sides[0] = getResources().getDrawable(R.drawable.dice1);
        sides[1] = getResources().getDrawable(R.drawable.dice2);
        sides[2] = getResources().getDrawable(R.drawable.dice3);
        sides[3] = getResources().getDrawable(R.drawable.dice4);
        sides[4] = getResources().getDrawable(R.drawable.dice5);
        sides[5] = getResources().getDrawable(R.drawable.dice6);

        random = new Random();
    }

    public void rollDice(View v) {
        ArrayList<Integer> dices = new ArrayList<Integer>();

        for(int i = 0; i < numberOfDice; ++i) {
            dices.add(random.nextInt(6));
        }

        adapter.setDices(dices);
        gridView.invalidateViews();
    }

    private class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<Integer> arrayList;

        public ImageAdapter(Context c) {
            mContext = c;
            arrayList = new ArrayList<Integer>();
        }

        public int getCount() {
            return numberOfDice;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public void setDices(ArrayList<Integer> a) {
            arrayList = a;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                int width = (int)getResources().getDimension(R.dimen.dice_width);
                Log.i("Dice width", "" + width);
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(width,width));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            if(!arrayList.isEmpty())
                imageView.setImageDrawable(sides[arrayList.get(position)]);
            return imageView;
        }
    }

}
