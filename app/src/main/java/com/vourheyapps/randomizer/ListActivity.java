package com.vourheyapps.randomizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by vourhey on 8/9/15.
 */
public class ListActivity extends Activity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    public final static String CUSTOM_LISTS_ID = "com.vourheyapps.randomizer.CUSTOM_LISTS";
    public final static String FIRST_TIME_ID = "com.vourheyapps.randomizer.FIRST_TIME";

    private EditText nameOfCustomListEdit;
    private ArrayAdapter<Item> adapter;
    private ArrayList<Item> arrayList;
    private SharedPreferences pref;

    private class Item {
        private String item;
        private String hashStr;

        public Item(String i) {
            item = i;
            try {
                MessageDigest digester = MessageDigest.getInstance("MD5");
                digester.update(item.getBytes());
                hashStr = new BigInteger(1, digester.digest()).toString();
                Log.d("Hash", hashStr);
            } catch (Exception e) {
                Log.i("Item", e.toString());
            }
        }

        public String toString() {
            return item;
        }

        public String hash() {
            return hashStr;
        }
    }

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.list_activity);

        Log.i("ListActivity", "onCreate");

        arrayList = new ArrayList<Item>();
        adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, arrayList);

        ListView listView = (ListView) findViewById(R.id.customLists);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setAdapter(adapter);

        checkFirstTime();

        nameOfCustomListEdit = (EditText) findViewById(R.id.nameOfCustomList);
        pref = getSharedPreferences(CUSTOM_LISTS_ID, MODE_PRIVATE);
        Map<String,?> allLists = pref.getAll();
        if(!allLists.isEmpty()) {
            for(Map.Entry<String,?> entry : allLists.entrySet()) {
                arrayList.add(new Item(entry.getKey()));
            }
            adapter.notifyDataSetChanged();
        }
    }

    private void checkFirstTime() {
        pref = getSharedPreferences(FIRST_TIME_ID, MODE_PRIVATE);
        if(pref.getBoolean("first_launch", true)) {
            Log.i("Randomizer", "First launch");

            pref.edit().putBoolean("first_launch", false).apply();
            pref = getSharedPreferences(CUSTOM_LISTS_ID, MODE_PRIVATE);

            String listName = getResources().getString(R.string.string_list_day_of_week);
            Item item = new Item(listName);

            SharedPreferences.Editor editor = pref.edit();
            editor.putString(listName, item.hash());
            editor.apply();

            HashSet<String> items = new HashSet<String>(
                    Arrays.asList(getResources().getStringArray(R.array.string_list_day_of_week_days)));
            pref = getSharedPreferences(CustomListActivity.ITEMS_ID, MODE_PRIVATE);
            editor = pref.edit();
            editor.putStringSet(item.hash(), items);

            editor.apply();
        }
    }

    public void createListClick(View v) {
        String listName = nameOfCustomListEdit.getText().toString();
        if(listName.isEmpty()) {
            return;
        }
        nameOfCustomListEdit.setText("");

        Item item = new Item(listName);
        arrayList.add(item);
        adapter.notifyDataSetChanged();

        SharedPreferences.Editor editor = pref.edit();
        editor.putString(listName, item.hash());
        editor.apply();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, CustomListActivity.class);
        intent.putExtra(CUSTOM_LISTS_ID, adapter.getItem(i).hash());
        startActivityForResult(intent, 1);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("onLongTap", "Position " + i);
        Item item = adapter.getItem(i);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(item.toString());
        editor.apply();
        arrayList.remove(i);
        adapter.notifyDataSetChanged();

        // remove items
        SharedPreferences preferences = getSharedPreferences(CustomListActivity.ITEMS_ID, MODE_PRIVATE);
        editor = preferences.edit();
        editor.remove(item.hash());
        editor.apply();
        return true;
    }
}
