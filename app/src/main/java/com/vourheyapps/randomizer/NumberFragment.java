package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by vourhey on 8/4/15.
 */
public class NumberFragment extends CommonFragment {
    private EditText minNumberText;
    private EditText maxNumberText;
    private Spinner numberSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View r = inflater.inflate(R.layout.number_fragment, container, false);

        minNumberText = (EditText) r.findViewById(R.id.minimumNumber);
        maxNumberText = (EditText) r.findViewById(R.id.maximumNumber);
        numberSpinner = (Spinner) r.findViewById(R.id.numberSpinner);

        return r;
    }

    public String generate() throws RuntimeException{
        String minStr = minNumberText.getText().toString();
        String maxStr = maxNumberText.getText().toString();
        if(minStr.isEmpty() || maxStr.isEmpty()) {
            Toast.makeText(getActivity(),
                    "You must define both min and max number", Toast.LENGTH_SHORT).show();
            throw new RuntimeException();
        }

        int min = Integer.parseInt(minStr);
        int max = Integer.parseInt(maxStr);

        if(min > max) {
            Toast.makeText(getActivity(), "Min and max are switched", Toast.LENGTH_SHORT).show();
            // switch min and max
            minNumberText.setText(String.valueOf(max));
            maxNumberText.setText(String.valueOf(min));

            int tmp = min;
            min = max;
            max = tmp;
        }

        int rand = MainActivity.random.nextInt(max - min) + min;
        int pos = numberSpinner.getSelectedItemPosition();
        switch (pos) {
        case 1:
            rand = MainActivity.random.nextInt((max - min)/2) * 2 + min;
            break;
        case 2:
            rand = MainActivity.random.nextInt((max - min)/2) * 2 + min + 1;
            break;
        }
        return String.valueOf(rand);
    }
}
