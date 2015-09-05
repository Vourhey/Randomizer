package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vourhey on 8/4/15.
 */
public class MonthFragment extends CommonFragment {
    private String[] months;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        months = getResources().getStringArray(R.array.string_months);
        return inflater.inflate(R.layout.empty_fragment, container, false);
    }

    public String generate() {
        int m = MainActivity.random.nextInt(12);
        return months[m];
    }
}
