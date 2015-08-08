package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

/**
 * Created by vourhey on 8/4/15.
 */
public class TimeFragment extends CommonFragment {
    private Random random;

    // TODO set alarm
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        random = new Random();
        return inflater.inflate(R.layout.time_fragment, container, false);
    }

    public String generate() {
        int hours = random.nextInt(24);
        int minutes = random.nextInt(60);
        return (hours + ":" + minutes);
    }
}
