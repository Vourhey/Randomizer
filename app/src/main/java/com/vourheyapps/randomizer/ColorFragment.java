package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vourhey on 8/4/15.
 */
public class ColorFragment extends CommonFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_fragment, container, false);
    }
    public String generate() {
        int rr = MainActivity.random.nextInt(256);
        int gg = MainActivity.random.nextInt(256);
        int bb = MainActivity.random.nextInt(256);

        int color = (rr << 16) + (gg << 8) + bb;
        View layout = getActivity().findViewById(R.id.commonLayoutId);
        color = 0xFF000000 | color;
        layout.setBackgroundColor(color);
        layout.invalidate();
        return ("#" + Integer.toHexString(color).substring(2).toUpperCase());
    }
}
