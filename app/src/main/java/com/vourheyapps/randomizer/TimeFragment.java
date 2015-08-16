package com.vourheyapps.randomizer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

/**
 * Created by vourhey on 8/4/15.
 */
public class TimeFragment extends CommonFragment {
    private Random random;
    private int hour;
    private int minutes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        random = new Random();
        hour = minutes = -1;

        View r = inflater.inflate(R.layout.time_fragment, container, false);
        Button setAlarmButton = (Button) r.findViewById(R.id.setAlarmButton);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour == -1) {
                    return;     // there's no generated time
                }

                // TODO it doesn't work
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        return r;
    }

    public String generate() {
        hour = random.nextInt(24);
        minutes = random.nextInt(60);
        return (hour + ":" + minutes);
    }
}
