package no.group16.softark.tdt4240.softarkclient;

import android.view.View;

/**
 * Created by tien on 4/15/2016.
 */
public interface IGameView {
    public void generateKeyboard(String string, final int MAX_CHAR_PER_ROW, android.view.View.OnClickListener handleButtonPress);
    public void setWhoIsDrawing(String playerName);
    public void moveLetterBtn(android.view.View v, final int MAX_CHAR_PER_ROW);
    public IRenderer getRenderer();
    public void updatePlayerListTextView(String txt);
    public void setOnTouchListener(View.OnTouchListener touchListener);
}
