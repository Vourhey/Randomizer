package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vourhey on 8/4/15.
 */
public class NumberFragment extends CommonFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View r = inflater.inflate(R.layout.number_fragment, container, false);
        return r;
    }

    public String generate() {
        return "number";
    }
}
