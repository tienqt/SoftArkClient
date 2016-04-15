package no.group16.softark.tdt4240.softarkclient;

import android.view.View;

/**
 * Created by tien on 4/15/2016.
 */
public interface IView {
    public void generateKeyboard(String string, final int MAX_CHAR_PER_ROW, android.view.View.OnClickListener handleButtonPress);
    public void moveLetterBtn(android.view.View v, final int MAX_CHAR_PER_ROW);
}
