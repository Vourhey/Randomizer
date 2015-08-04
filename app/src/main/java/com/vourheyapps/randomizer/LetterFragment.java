package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.Random;

/**
 * Created by vourhey on 8/4/15.
 */
public class LetterFragment extends CommonFragment {
    private Spinner letterSpinner;
    private String vowels;
    private String consonants;
    private Random random;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View r = inflater.inflate(R.layout.letter_fragment, container, false);

        letterSpinner = (Spinner) r.findViewById(R.id.letterSpinner);
        vowels = getString(R.string.string_vowels);
        consonants = getString(R.string.string_consonants);
        random = new Random();

        return r;
    }

    public String generate() {
        int p = letterSpinner.getSelectedItemPosition();
        String string = vowels + consonants;

        switch (p) {
        case 1:     // Vowels
            string = vowels;
            break;
        case 2:     // Consonants
            string = consonants;
            break;
        }

        int letterPosition = random.nextInt(string.length());
        return String.valueOf(string.charAt(letterPosition));
    }
}
