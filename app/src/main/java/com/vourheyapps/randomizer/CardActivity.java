package com.vourheyapps.randomizer;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vourhey on 8/9/15.
 */
// TODO checkBox 'K' is not visible on some screens
// TODO generate customDeck only if there were changes on checkBoxes
public class CardActivity extends Activity {
    private CheckBox[] colorsChecks;
    private CheckBox[] suitsChecks;
    private ImageView showCardView;
    private HistoryList historyList;
    private TypedArray cardIcons;
    private final String colorsString = "\u2663\u2660\u2665\u2666"; // ♣♠♥♦
    private final String suitsString = "A234567890JQK";

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.card_activity);

        historyList = new HistoryList(this, (ListView)findViewById(R.id.cardHistoryListView));
        showCardView = (ImageView) findViewById(R.id.showCardImageView);

        initCards();

        // Hearts and Diamonds are red
        final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(255, 0, 0));
        SpannableStringBuilder ssb = new SpannableStringBuilder(colorsChecks[2].getText());

        ssb.setSpan(fcs, 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        colorsChecks[2].setText(ssb);
        ssb = new SpannableStringBuilder(colorsChecks[3].getText());
        ssb.setSpan(fcs, 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        colorsChecks[3].setText(ssb);
    }

    private void initCards() {
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

    // split this method
    public void shuffleDeck(View v) {
        boolean has_color = hasColor();
        boolean has_suit = hasSuit();
        Log.d("shuffleDeck", String.valueOf(has_color) + " " + String.valueOf(has_suit));

        HashMap<Drawable, String> customDeck = new HashMap<Drawable, String>();

        if(!has_color && !has_suit) {
            Toast.makeText(this, "Choose something first", Toast.LENGTH_SHORT).show();
            return;
        } else if(has_color && !has_suit) {
            customDeck = selectColors(customDeck);
        } else if(!has_color) {
            customDeck = selectSuits(customDeck);
        } else {
            customDeck = selectBoth(customDeck);
        }

        // maybe I'll die for this code
        int card = MainActivity.random.nextInt(customDeck.size());
        ArrayList<Map.Entry<Drawable,String>> entries =
                new ArrayList<Map.Entry<Drawable, String>>(customDeck.entrySet());

        showCardView.setImageDrawable(entries.get(card).getKey());
        showCardView.invalidate();
        historyList.addItem(entries.get(card).getValue());
    }

    private HashMap<Drawable,String> selectColors(HashMap<Drawable,String> customDeck) {
        String s;
        for(int i = 0; i < 52; ++i) {
            if(colorsChecks[i % 4].isChecked()) {
                s = "" + colorsString.charAt(i%4) + (i % 13 == 9 ? "10" : suitsString.charAt(i % 13));
                customDeck.put(cardIcons.getDrawable(i), s);
            }
        }
        return customDeck;
    }

    private HashMap<Drawable,String> selectSuits(HashMap<Drawable,String> customDeck) {
        String s;
        for(int i = 0; i < 13; ++i) {
            if(suitsChecks[i].isChecked()) {
                String as = "" + (i % 13 == 9 ? "10" : suitsString.charAt(i % 13));
                s = "" + colorsString.charAt(0) + as;
                customDeck.put(cardIcons.getDrawable(i * 4), s);
                s = "" + colorsString.charAt(1) + as;
                customDeck.put(cardIcons.getDrawable(i * 4 + 1), s);
                s = "" + colorsString.charAt(2) + as;
                customDeck.put(cardIcons.getDrawable(i * 4 + 2), s);
                s = "" + colorsString.charAt(3) + as;
                customDeck.put(cardIcons.getDrawable(i * 4 + 3), s);
            }
        }
        return customDeck;
    }

    private HashMap<Drawable,String> selectBoth(HashMap<Drawable,String> customDeck) {
        String s;
        for(int i = 0; i < 13; ++i) {
            if(suitsChecks[i].isChecked()) {
                String as = "" + (i % 13 == 9 ? "10" : suitsString.charAt(i % 13));
                if(colorsChecks[0].isChecked()) {
                    s = colorsString.charAt(0) + as;
                    customDeck.put(cardIcons.getDrawable(i * 4), s);
                }
                if(colorsChecks[1].isChecked()) {
                    s = colorsString.charAt(1) + as;
                    customDeck.put(cardIcons.getDrawable(i * 4 + 1), s);
                }
                if(colorsChecks[2].isChecked()) {
                    s = colorsString.charAt(2) + as;
                    customDeck.put(cardIcons.getDrawable(i * 4 + 2), s);
                }
                if(colorsChecks[3].isChecked()) {
                    s = colorsString.charAt(3) + as;
                    customDeck.put(cardIcons.getDrawable(i * 4 + 3), s);
                }
            }
        }
        return customDeck;
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
