package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

/**
 * Created by vourhey on 8/4/15.
 */
public class YmnFragment extends CommonFragment {
    private Random random;  // TODO random should be only one for every activity
    private String[] ymnArray;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        random = new Random();
        ymnArray = getResources().getStringArray(R.array.string_ymn_answers);
        return inflater.inflate(R.layout.ymn_fragment, container, false);
    }

    public String generate() {
        int r = random.nextInt(ymnArray.length);
        return ymnArray[r];
    }
}
