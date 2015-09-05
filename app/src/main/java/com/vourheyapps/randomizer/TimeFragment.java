package com.vourheyapps.randomizer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by vourhey on 8/4/15.
 */
public class TimeFragment extends CommonFragment {
    private int hour;
    private int minutes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hour = minutes = -1;

        View r = inflater.inflate(R.layout.time_fragment, container, false);
        Button setAlarmButton = (Button) r.findViewById(R.id.setAlarmButton);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour == -1) {
                    return;     // there's no generated time
                }

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
        hour = MainActivity.random.nextInt(24);
        minutes = MainActivity.random.nextInt(60);
        String time = (hour < 10 ? "0" : "") + hour + ":" + (minutes < 10 ? "0" : "") + minutes;
        return time;
    }
}
