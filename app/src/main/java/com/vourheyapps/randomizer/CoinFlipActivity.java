package com.vourheyapps.randomizer;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by vourhey on 8/16/15.
 */
public class CoinFlipActivity extends Activity {
    private Drawable[] coin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coinflip_activity);

        coin = new Drawable[2];
        coin[0] = getResources().getDrawable(R.drawable.coin_heads);
        coin[1] = getResources().getDrawable(R.drawable.coin_tails);

        final ImageView coinImage = (ImageView) findViewById(R.id.coinflipImage);
        coinImage.setImageDrawable(coin[0]);
        coinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coinImage.setImageDrawable(coin[MainActivity.random.nextInt(2)]);
            }
        });
    }
}
