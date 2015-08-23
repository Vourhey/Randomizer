package com.vourheyapps.randomizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by vourhey on 8/9/15.
 */
public class ListActivity extends Activity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    public final static String CUSTOM_LISTS_ID = "com.vourheyapps.randomizer.CUSTOM_LISTS";

    private EditText nameOfCustomListEdit;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.list_activity);

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        ListView listView = (ListView) findViewById(R.id.customLists);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setAdapter(adapter);

        nameOfCustomListEdit = (EditText) findViewById(R.id.nameOfCustomList);
        pref = getSharedPreferences(CUSTOM_LISTS_ID, MODE_PRIVATE);
    }

    public void createListClick(View v) {
        String listName = nameOfCustomListEdit.getText().toString();
        arrayList.add(listName);
        adapter.notifyDataSetChanged();
        long id = adapter.getItemId(arrayList.size() - 1);
        nameOfCustomListEdit.setText("");

        SharedPreferences.Editor editor = pref.edit();
        editor.putString(listName, String.valueOf(id));
        editor.apply();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, CustomListActivity.class);
        intent.putExtra(CUSTOM_LISTS_ID, l);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(arrayList.get(i));
        editor.apply();
        // TODO remove all associated items
        arrayList.remove(i);
        adapter.notifyDataSetChanged();
        return true;
    }
}
