package com.vourheyapps.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by vourhey on 8/4/15.
 */
public class CountryFragment extends CommonFragment {
    private Random random;
    private ArrayList<String> africaArray,
                    asiaArray,
                    centralAmericaArray,
                    easternEuropeArray,
                    europeanUnionArray,
                    middleEastArray,
                    oceaniaArray,
                    northAmericaArray,
                    southAmericaArray,
                    theCaribbeanArray;
    private Spinner countrySpinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        random = new Random();
        africaArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_africa)));
        asiaArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_asia)));
        centralAmericaArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_central_america)));
        easternEuropeArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_eastern_europe)));
        europeanUnionArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_europe_union)));
        middleEastArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_middle_east)));
        oceaniaArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_oceania)));
        northAmericaArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_north_america)));
        southAmericaArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_south_america)));
        theCaribbeanArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.string_countries_the_caribbean)));

        View r = inflater.inflate(R.layout.country_fragment, container, false);
        countrySpinner = (Spinner) r.findViewById(R.id.countrySpinner);
        return r;
    }

    public String generate() {
        int p = countrySpinner.getSelectedItemPosition();
        ArrayList<String> s = new ArrayList<String>();

        switch (p) {
        case 0: // All
            s.addAll(africaArray);
            s.addAll(asiaArray);
            s.addAll(centralAmericaArray);
            s.addAll(easternEuropeArray);
            s.addAll(europeanUnionArray);
            s.addAll(middleEastArray);
            s.addAll(oceaniaArray);
            s.addAll(northAmericaArray);
            s.addAll(southAmericaArray);
            s.addAll(theCaribbeanArray);
            break;
        case 1:
            s.addAll(africaArray);
            break;
        case 2:
            s.addAll(asiaArray);
            break;
        case 3:
            s.addAll(centralAmericaArray);
            break;
        case 4:
            s.addAll(easternEuropeArray);
            break;
        case 5:
            s.addAll(europeanUnionArray);
            break;
        case 6:
            s.addAll(middleEastArray);
            break;
        case 7:
            s.addAll(oceaniaArray);
            break;
        case 8:
            s.addAll(northAmericaArray);
            break;
        case 9:
            s.addAll(southAmericaArray);
            break;
        case 10:
            s.addAll(theCaribbeanArray);
            break;
        }

        return s.get(random.nextInt(s.size()));
    }
}
