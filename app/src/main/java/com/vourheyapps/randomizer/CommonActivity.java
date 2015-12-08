package com.vourheyapps.randomizer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vourhey on 8/4/15.
 */
public class CommonActivity extends Activity implements OnHistoryItemClickListener {
    private TextView randomTextView;
    private CommonFragment currentFragment;
    private HistoryList historyList;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.common_layout);

        historyList = new HistoryList(this, (ListView) findViewById(R.id.historyListView));
        randomTextView = (TextView) findViewById(R.id.randomTextView);

        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.VIEWID_MESSAGE, 0);

        currentFragment = new CommonFragment();
        switch (id) {
        case R.id.letterButton:
            currentFragment = new LetterFragment();
            break;
        case R.id.numberButton:
            currentFragment = new NumberFragment();
            break;
        case R.id.colorButton:
            currentFragment = new ColorFragment();
            break;
        case R.id.passwordButton:
            currentFragment = new PasswordFragment();
            break;
        case R.id.timeButton:
            currentFragment = new TimeFragment();
            break;
        case R.id.monthButton:
            currentFragment = new MonthFragment();
            break;
        case R.id.countryButton:
            currentFragment = new CountryFragment();
            break;
        case R.id.ymnButton:
            currentFragment = new YmnFragment();
            break;
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentForReplace, currentFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("HISTORY_ITEMS", historyList.getItems());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ArrayList<String> items = savedInstanceState.getStringArrayList("HISTORY_ITEMS");
        if(items != null) {
            for(String item : items) {
                historyList.addItem(item);
            }
        }

    }

    @Override
    public void onHistoryItemClickListener(String text) {
        randomTextView.setText(text);
    }

    @Override
    protected void onPause() {
        super.onPause();
        historyList.removeListener(currentFragment);
        historyList.removeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        historyList.addListener(currentFragment);
        historyList.addListener(this);
    }

    public void generate(View v) {
        try {
            String item = currentFragment.generate();
            randomTextView.setText(item);
            historyList.addItem(item);
        } catch (Exception e) {
            Log.i("Exception", "undefined exception", e);
        }
    }

    public void clearHistory(View v) {
        historyList.clear();
    }
}
