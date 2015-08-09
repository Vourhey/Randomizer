package com.vourheyapps.randomizer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by vourhey on 8/4/15.
 */
public class CommonActivity extends Activity {
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

    public void generate(View v) {
        String item = currentFragment.generate();
        randomTextView.setText(item);
        historyList.addItem(item);
    }

    public void clearHistory(View v) {
        historyList.clear();
    }
}
