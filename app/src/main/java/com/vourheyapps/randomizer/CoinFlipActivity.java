package com.vourheyapps.randomizer;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vourhey on 8/16/15.
 */
public class CoinFlipActivity extends Activity {
    private Drawable[] coin;
    private String[] headsOrTails;
    private TextView headsOrTailsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coinflip_activity);

        coin = new Drawable[2];
        coin[0] = getResources().getDrawable(R.drawable.coin_heads);
        coin[1] = getResources().getDrawable(R.drawable.coin_tails);

        headsOrTails = new String[2];
        headsOrTails = getResources().getStringArray(R.array.string_heads_or_tails);

        headsOrTailsText = (TextView) findViewById(R.id.coinHeadOrTailsText);
        final ImageView coinImage = (ImageView) findViewById(R.id.coinflipImage);
        coinImage.setImageDrawable(coin[0]);
        coinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = MainActivity.random.nextInt(2);
                coinImage.setImageDrawable(coin[c]);
                headsOrTailsText.setText(headsOrTails[c]);
            }
        });
    }
}
