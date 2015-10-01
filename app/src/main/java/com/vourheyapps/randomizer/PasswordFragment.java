package com.vourheyapps.randomizer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by vourhey on 8/4/15.
 */
public class PasswordFragment extends CommonFragment {
    private Spinner spinner;
    private EditText lengthText;
    private EditText customText;
    private int length;
    private String numbers = "0123456789";
    private String letters = "abcdefghijklmnoprstuvwxyzABCDEFGHIJKLMNOPRSTUVWXYZ";
    private String specialChars = "@%+\\/\'!#$^?:.(){}[]~-_";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View r = inflater.inflate(R.layout.password_fragment, container, false);

        spinner = (Spinner) r.findViewById(R.id.passwordSpinner);
        customText = (EditText) r.findViewById(R.id.passwordCustomEdit);
        lengthText = (EditText) r.findViewById(R.id.passwordLengthEdit);
        length = 8;
        lengthText.setText(String.valueOf(length));

        return r;
    }

    public String generate() throws RuntimeException {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        String lengthStr = lengthText.getText().toString();
        if(lengthStr.isEmpty()) {
            Toast.makeText(getActivity(), "You should set the length of password", Toast.LENGTH_SHORT).show();
            throw new RuntimeException();
        }
        length = Integer.parseInt(lengthStr);
        String password = "";
        String alphabet = "";

        if(customText.getText().toString().isEmpty()) {
            int pos = spinner.getSelectedItemPosition();
            switch (pos) {
            case 0:     // Alphanumeric
                alphabet = numbers + letters;
                break;
            case 1:     // Alphanumeric with Characters
                alphabet = numbers + letters + specialChars;
                break;
            case 2:     // Numbers only
                alphabet = numbers;
                break;
            case 3:
                alphabet = letters;
                break;
            }
        } else {
            alphabet = customText.getText().toString();
        }

        for(int i = 0; i < length; ++i) {
            char c = alphabet.charAt(MainActivity.random.nextInt(alphabet.length()));
            password += c;
        }

        return password;
    }
}
