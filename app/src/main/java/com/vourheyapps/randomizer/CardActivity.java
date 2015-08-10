package com.vourheyapps.randomizer;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vourhey on 8/9/15.
 */
public class CardActivity extends Activity {
    private CheckBox[] colorsChecks;
    private CheckBox[] suitsChecks;
    private ImageView showCardView;
    private HistoryList historyList;
    private TypedArray cardIcons;
    private Random random;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.card_activity);

        historyList = new HistoryList(this, (ListView)findViewById(R.id.cardHistoryListView));
        random = new Random();
        showCardView = (ImageView) findViewById(R.id.showCardImageView);

        colorsChecks = new CheckBox[4];
        colorsChecks[0] = (CheckBox) findViewById(R.id.cardClubsCheckBox);
        colorsChecks[1] = (CheckBox) findViewById(R.id.cardSpadesCheckBox);
        colorsChecks[2] = (CheckBox) findViewById(R.id.cardHeartsCheckBox);
        colorsChecks[3] = (CheckBox) findViewById(R.id.cardDiamondsCheckBox);

        suitsChecks = new CheckBox[13];
        suitsChecks[0] = (CheckBox) findViewById(R.id.cardACheckBox);
        suitsChecks[1] = (CheckBox) findViewById(R.id.card2CheckBox);
        suitsChecks[2] = (CheckBox) findViewById(R.id.card3CheckBox);
        suitsChecks[3] = (CheckBox) findViewById(R.id.card4CheckBox);
        suitsChecks[4] = (CheckBox) findViewById(R.id.card5CheckBox);
        suitsChecks[5] = (CheckBox) findViewById(R.id.card6CheckBox);
        suitsChecks[6] = (CheckBox) findViewById(R.id.card7CheckBox);
        suitsChecks[7] = (CheckBox) findViewById(R.id.card8CheckBox);
        suitsChecks[8] = (CheckBox) findViewById(R.id.card9CheckBox);
        suitsChecks[9] = (CheckBox) findViewById(R.id.card10CheckBox);
        suitsChecks[10] = (CheckBox) findViewById(R.id.cardJCheckBox);
        suitsChecks[11] = (CheckBox) findViewById(R.id.cardQCheckBox);
        suitsChecks[12] = (CheckBox) findViewById(R.id.cardKCheckBox);

        cardIcons = getResources().obtainTypedArray(R.array.card_array);
    }

    public void shuffleDeck(View v) {
        boolean has_color = hasColor();
        boolean has_suit = hasSuit();

        int i;
        ArrayList<Drawable> customDeck = new ArrayList<Drawable>();

        if(!has_color && !has_suit) {
            // nothing is checked
            // report an error
        } else if(has_color && !has_suit) {
            for(i = 0; i < 52; ++i) {
                if(colorsChecks[i % 4].isChecked()) {
                    customDeck.add(cardIcons.getDrawable(i));
                }
            }
        } else if(!has_color && has_suit) {
            for(i = 0; i < 52; ++i) {
                if(suitsChecks[i % 13].isChecked()) {
                    customDeck.add(cardIcons.getDrawable(i));
                }
            }
        } else {
            for(i = 0; i < 52; ++i) {
                if(colorsChecks[i % 4].isChecked() &&
                        suitsChecks[i % 13].isChecked()) {
                    customDeck.add(cardIcons.getDrawable(i));
                }
            }
        }

        int card = random.nextInt(customDeck.size());
        showCardView.setImageDrawable(customDeck.get(card));
        showCardView.invalidate();
    }

    private boolean hasColor() {
        for(int i = 0; i < 4; ++i) {
            if(colorsChecks[i].isChecked()) {
                return true;
            }
        }

        return false;
    }

    private boolean hasSuit() {
        for(int i = 0; i < 13; ++i) {
            if(suitsChecks[i].isChecked()) {
                return true;
            }
        }

        return false;
    }

    public void clearCardHistory(View v) {
        historyList.clear();
    }
}
