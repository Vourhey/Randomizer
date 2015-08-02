package com.vourheyapps.randomizer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by vourhey on 8/2/15.
 */
public class LetterActivity extends Activity {
    private TextView outputLetter;
    private Spinner letterSpinner;
    private ListView historyList;

    private String vowels;
    private String consonants;
    private Random random;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.letter_layout);

        outputLetter = (TextView) findViewById(R.id.outputLetter);
        letterSpinner = (Spinner) findViewById(R.id.letterSpinner);

        vowels = getString(R.string.string_vowels);
        consonants = getString(R.string.string_consonants);
        random = new Random();
    }

    public void generateLetter(View v) {
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
        char letter = string.charAt(letterPosition);

        outputLetter.setText(String.valueOf(letter));
        // add to history
    }

}
