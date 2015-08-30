package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

/**
 * Created by vourhey on 8/4/15.
 */
public class ColorFragment extends CommonFragment {
    private Random random;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        random = new Random();
        return inflater.inflate(R.layout.empty_fragment, container, false);
    }
    public String generate() {
        int rr = random.nextInt(256);
        int gg = random.nextInt(256);
        int bb = random.nextInt(256);

        int color = (rr << 16) + (gg << 8) + bb;
        View layout = getActivity().findViewById(R.id.commonLayoutId);
        color = 0xFF000000 | color;
        layout.setBackgroundColor(color);
        layout.invalidate();
        return ("#" + Integer.toHexString(color).substring(2).toUpperCase());
    }
}
