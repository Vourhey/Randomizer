package com.vourheyapps.randomizer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

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
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        
        int r = MainActivity.random.nextInt(ymnArray.length);
        return ymnArray[r];
    }
}
