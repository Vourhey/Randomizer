package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vourhey on 8/4/15.
 */
public class YmnFragment extends CommonFragment {
    private String[] ymnArray;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ymnArray = getResources().getStringArray(R.array.string_ymn_answers);
        return inflater.inflate(R.layout.ymn_fragment, container, false);
    }

    public String generate() {
        int r = MainActivity.random.nextInt(ymnArray.length);
        return ymnArray[r];
    }
}
