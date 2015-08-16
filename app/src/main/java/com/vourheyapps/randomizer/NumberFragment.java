package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Random;

/**
 * Created by vourhey on 8/4/15.
 */
public class NumberFragment extends CommonFragment {
    private EditText minNumberText;
    private EditText maxNumberText;
    private Spinner numberSpinner;
    private Random random;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View r = inflater.inflate(R.layout.number_fragment, container, false);

        minNumberText = (EditText) r.findViewById(R.id.minimumNumber);
        maxNumberText = (EditText) r.findViewById(R.id.maximumNumber);
        numberSpinner = (Spinner) r.findViewById(R.id.numberSpinner);
        random = new Random();

        return r;
    }

    public String generate() {
        int min = Integer.parseInt(minNumberText.getText().toString());
        int max = Integer.parseInt(maxNumberText.getText().toString());

        // TODO use numberSpinner

        int rand = random.nextInt(max - min) + min;
        return String.valueOf(rand);
    }
}
