package com.vourheyapps.randomizer;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vourhey on 8/9/15.
 */
public class RpsActivity extends Activity {
    private ImageView playersImage;
    private ImageView computersImage;
    private TextView playersScore;
    private TextView computersScore;
    private TextView headText;
    private Button changeModeButton;
    private Drawable[] images;
    private String[] beats;
    private int currentIcon;
    private int pScore, cScore;
    private int mode;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.rps_activity);

        playersImage = (ImageView) findViewById(R.id.playersImageRPS);
        computersImage = (ImageView) findViewById(R.id.computersImageRPS);
        playersScore = (TextView) findViewById(R.id.playersScoreRPS);
        computersScore = (TextView) findViewById(R.id.computersScoreRPS);
        headText = (TextView) findViewById(R.id.winOrLoseTextRPS);
        changeModeButton = (Button) findViewById(R.id.changeModeButton);

        images = new Drawable[5];
        beats = new String[5];
        images[0] = getResources().getDrawable(R.drawable.scissors);
        beats[0] = "1, 3";
        images[1] = getResources().getDrawable(R.drawable.paper);
        beats[1] = "2, 4";
        images[2] = getResources().getDrawable(R.drawable.rock);
        beats[2] = "3, 0";
        images[3] = getResources().getDrawable(R.drawable.lizard);
        beats[3] = "4, 1";
        images[4] = getResources().getDrawable(R.drawable.spock);
        beats[4] = "0, 2";
        currentIcon = 1;
        pScore = cScore = 0;
        mode = 3;
    }

    public void changeRPSIcon(View v) {
        currentIcon = ++currentIcon % mode;
        playersImage.setImageDrawable(images[currentIcon]);
    }

    public void changeModeRPS(View v) {
        if(mode == 3) {
            mode = 5;
            changeModeButton.setText("To RPS");
        } else {
            mode = 3;
            changeModeButton.setText("To RPSLS");
        }
    }

    public void generateRPS(View v) {
        int computersIcon = MainActivity.random.nextInt(mode);

        computersImage.setImageDrawable(images[computersIcon]);

        if(beats[currentIcon].contains(String.valueOf(computersIcon))) {
            pScore++;
            playersScore.setText(String.valueOf(pScore));
            headText.setText(R.string.string_rps_win);
        } else if(currentIcon == computersIcon) {
            headText.setText(R.string.string_rps_draw);
        } else {
            cScore++;
            computersScore.setText(String.valueOf(cScore));
            headText.setText(R.string.string_rps_lose);
        }
    }

    public void clearScore(View v) {
        computersScore.setText("0");
        playersScore.setText("0");
        pScore = cScore = 0;
    }
}
