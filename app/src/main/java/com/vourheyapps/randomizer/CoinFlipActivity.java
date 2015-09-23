package com.vourheyapps.randomizer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by vourhey on 8/16/15.
 */
public class CoinFlipActivity extends Activity {
    private String[] headsOrTails;
    private TextView headsOrTailsText;
    private ViewFlipper viewFlipper;
    private int count;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coinflip_activity);

        headsOrTails = new String[2];
        headsOrTails = getResources().getStringArray(R.array.string_heads_or_tails);

        headsOrTailsText = (TextView) findViewById(R.id.coinHeadOrTailsText);

        viewFlipper = (ViewFlipper) findViewById(R.id.coinViewFlipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.coinanim));
        viewFlipper.setFlipInterval(400);

        viewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (count == 1) {
                    viewFlipper.stopFlipping();
                    viewFlipper.setDisplayedChild(current);
                    headsOrTailsText.setText(headsOrTails[current]);
                }
                ++count;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = MainActivity.random.nextInt(2);
                count = 0;
                headsOrTailsText.setText("");
                viewFlipper.startFlipping();
            }
        });
    }
}
