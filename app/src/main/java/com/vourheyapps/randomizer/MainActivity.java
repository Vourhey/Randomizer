package com.vourheyapps.randomizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by vourhey on 8/1/15.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.main_layout);
    }

    public void openActivity(View v) {
        switch (v.getId()) {
        case R.id.listButton:
            break;
        case R.id.letterButton:
            Intent intent = new Intent(this, LetterActivity.class);
            startActivity(intent);
            break;
        case R.id.numberButton:
            break;
        case R.id.dicerollButton:
            break;
        case R.id.colorButton:
            break;
        case R.id.coinflipButton:
            break;
        case R.id.imageButton:
            break;
        case R.id.passwordButton:
            break;
        case R.id.cardButton:
            break;
        case R.id.timeButton:
            break;
        case R.id.monthButton:
            break;
        case R.id.countryButton:
            break;
        case R.id.rpsButton:
            break;
        case R.id.ymnButton:
            break;
        }
    }

}
