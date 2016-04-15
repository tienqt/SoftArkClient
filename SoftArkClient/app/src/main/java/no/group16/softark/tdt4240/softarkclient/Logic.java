package no.group16.softark.tdt4240.softarkclient;

import android.util.Log;

/**
 * Created by tien on 4/15/2016.
 */
public abstract class Logic {
    private final int MAX_LETTERS_PER_ROW;

    public Logic(int maxLetterPerRow){
        this.MAX_LETTERS_PER_ROW = maxLetterPerRow;
    }

    public int getMAX_LETTERS_PER_ROW() {
        return MAX_LETTERS_PER_ROW;
    }
}
