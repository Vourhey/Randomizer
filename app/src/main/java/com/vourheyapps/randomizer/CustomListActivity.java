package com.vourheyapps.randomizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by vourhey on 8/9/15.
 */
public class CustomListActivity extends Activity {
    private final String ITEMS_ID = "com.vourheyapps.randomizer.ITEMS_ID";
    private SharedPreferences pref;
    private Random random;
    private TextView showCustomElementText;
    private EditText elementEdit;
    private HistoryList historyList;
    private String id;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.custom_list_activity);

        ListView customListView = (ListView) findViewById(R.id.customItemsList);
        customListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                historyList.getArray().remove(i);
                return true;
            }
        });

        showCustomElementText = (TextView) findViewById(R.id.showCustomItemText);
        elementEdit = (EditText) findViewById(R.id.itemToAddEdit);

        random = new Random();
        historyList = new HistoryList(this, customListView);

        Intent intent = getIntent();
        id = intent.getStringExtra(ListActivity.CUSTOM_LISTS_ID);

        pref = getSharedPreferences(ITEMS_ID, MODE_PRIVATE);
        Set<String> items = pref.getStringSet(id, new HashSet<String>());
        if(!items.isEmpty()) {
            for (String item : items) {
                historyList.addItem(item);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // write items to preference
        SharedPreferences.Editor editor = pref.edit();

        HashSet<String> hashSet = new HashSet<String>(historyList.getArray());
        editor.putStringSet(id, hashSet);

        editor.apply();
    }

    public void addElement(View v) {
        String item = elementEdit.getText().toString();
        historyList.addItem(item);
    }

    public void chooseElement(View v) {
        int i = random.nextInt(historyList.getArray().size());
        showCustomElementText.setText(historyList.getArray().get(i));
    }
}
