package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vourhey on 8/4/15.
 */
public class PasswordFragment extends CommonFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.password_fragment, container, false);
    }

    public String generate() {
        return "password";
    }
}
