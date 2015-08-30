package com.vourheyapps.randomizer;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by vourhey on 8/3/15.
 */
/*
TODO: copy to clipboard
 */
public class HistoryList {
    private ArrayList<String> history;
    private ArrayAdapter<String> arrayAdapter;
    private ListView lv;

    public HistoryList(final Context context, ListView listView) {
        lv = listView;
        history = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, history);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String textToClip = parent.getAdapter().getItem(position).toString();

                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("com.vourheyapps.randomizer", textToClip);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addItem(String item) {
        history.add(item);
        arrayAdapter.notifyDataSetChanged();
        lv.setSelection(arrayAdapter.getCount() - 1);
    }

    public int size() {
        return history.size();
    }

    public String getItem(int i) {
        return history.get(i);
    }

    public ArrayList<String> getItems() {
        return history;
    }

    public void remove(int i) {
        history.remove(i);
        arrayAdapter.notifyDataSetChanged();
    }

    public void clear() {
        history.clear();
        arrayAdapter.notifyDataSetChanged();
    }

}
