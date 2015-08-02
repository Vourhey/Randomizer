package com.vourheyapps.randomizer;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by vourhey on 8/1/15.
 */
public class MainActivity extends Activity {
    private EditText fromNumber;
    private EditText toNumber;
    private TextView showNumber;
    private Random random;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.main_layout);

        fromNumber = (EditText) findViewById(R.id.fromNumber);
        fromNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateNumber();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        toNumber = (EditText) findViewById(R.id.toNumber);
        toNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateNumber();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        showNumber = (TextView) findViewById(R.id.randomNumberText);

        random = new Random();

        updateNumber();
    }

    private void updateNumber() {
        int first = Integer.parseInt(fromNumber.getText().toString());
        int second = Integer.parseInt(toNumber.getText().toString());
        int value = random.nextInt(second) + first;

        showNumber.setText(String.valueOf(value));
    }
}
