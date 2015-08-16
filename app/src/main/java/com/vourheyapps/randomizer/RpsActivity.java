package com.vourheyapps.randomizer;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by vourhey on 8/9/15.
 */
public class RpsActivity extends Activity {
    private ImageView playersImage;
    private ImageView computersImage;
    private TextView playersScore;
    private TextView computersScore;
    private TextView headText;
    private Drawable[] images;
    private int currentIcon;
    private int pScore, cScore;
    private Random random;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.rps_activity);

        playersImage = (ImageView) findViewById(R.id.playersImageRPS);
        computersImage = (ImageView) findViewById(R.id.computersImageRPS);
        playersScore = (TextView) findViewById(R.id.playersScoreRPS);
        computersScore = (TextView) findViewById(R.id.computersScoreRPS);
        headText = (TextView) findViewById(R.id.winOrLoseTextRPS);

        images = new Drawable[3];
        images[0] = getResources().getDrawable(R.drawable.rock);
        images[1] = getResources().getDrawable(R.drawable.paper);
        images[2] = getResources().getDrawable(R.drawable.scissors);
        currentIcon = 1;
        pScore = cScore = 0;

        random = new Random();
    }

    public void changeRPSIcon(View v) {
        currentIcon = ++currentIcon % 3;
        playersImage.setImageDrawable(images[currentIcon]);
    }

    public void generateRPS(View v) {
        int computersIcon = random.nextInt(3);

        computersImage.setImageDrawable(images[computersIcon]);
        if((computersIcon == 0 && currentIcon == 2) ||
                (computersIcon > currentIcon)) {
            cScore++;
            computersScore.setText(String.valueOf(cScore));
            headText.setText(R.string.string_rps_lose);
        } else if(computersIcon == currentIcon) {
            headText.setText(R.string.string_rps_draw);
        } else {
            pScore++;
            playersScore.setText(String.valueOf(pScore));
            headText.setText(R.string.string_rps_win);
        }
    }

    public void clearScore(View v) {
        computersScore.setText("0");
        playersScore.setText("0");
        pScore = cScore = 0;
    }
}
