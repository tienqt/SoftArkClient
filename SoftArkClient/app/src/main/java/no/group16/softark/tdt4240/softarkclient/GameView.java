package no.group16.softark.tdt4240.softarkclient;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tien on 4/15/2016.
 */
public class GameView extends RelativeLayout implements IGameView {

    IRenderer renderer;

    private TableRow keyRow1;
    private TableRow keyRow2;
    private TableRow enteredLettersRow;

    private TextView playerListTextView;

    public GameView(Context context) {
        super(context);

        inflate(getContext(), R.layout.activity_game, this);

        keyRow1 = (TableRow)findViewById(R.id.keyboardRow1);
        keyRow2 = (TableRow)findViewById(R.id.keyboardRow2);
        enteredLettersRow = (TableRow)findViewById(R.id.enteredLettersRow);

        playerListTextView = (TextView)findViewById(R.id.playersTextView);

        renderer = new CanvasRenderer(this);
    }



    /**
     * Takes a string (should already be shuffled) and adds the containing letters to the keyboard
     * @param string
     */
    @Override
    public void generateKeyboard(String string, final int MAX_CHAR_PER_ROW, OnClickListener handleButtonPress) {
        string = string.toLowerCase();

        for(int i = 0; i < string.length(); i++) {
            ImageButton imgBtn = new ImageButton(getContext());
            imgBtn.setBackgroundColor(Color.TRANSPARENT);
            char c = string.charAt(i);
            imgBtn.setImageResource(getResources().getIdentifier(c + "_grey", "drawable", getContext().getPackageName()));
            imgBtn.setMinimumHeight(0);
            imgBtn.setMinimumWidth(0);
            imgBtn.setPadding(2,4,2,2);

            if(i < MAX_CHAR_PER_ROW)
                keyRow1.addView(imgBtn);
            else
                keyRow2.addView(imgBtn);

            imgBtn.setTag(c);   // The letter is stored in the tag
            imgBtn.setOnClickListener(handleButtonPress);
        }

    }

    @Override
    public void setWhoIsDrawing(String playerName) {

    }

    @Override
    public void moveLetterBtn(android.view.View v, final int MAX_CHAR_PER_ROW){
        ImageButton btn = (ImageButton)v;

        if(btn.getParent() != enteredLettersRow) {
            ((TableRow)v.getParent()).removeView(v);
            enteredLettersRow.addView(btn);
        }
        else{
            ((TableRow)v.getParent()).removeView(v);

            if(keyRow1.getChildCount() < MAX_CHAR_PER_ROW)
                keyRow1.addView(btn);
            else
                keyRow2.addView(btn);
        }
    }

    /**
     * Updates the list showing active players/teams and their scores
     */
    private void updateScoreList(String player) {
        playerListTextView.setText(Html.fromHtml(
                "Even (2300)<br>" +
                        "Viktor (2200)<br>" +
                        "Tom (2000)<br>" +
                        "Daniel (2000)<br>" +
                        "Tien (1100)"
        ));
    }

    /**
     * Shuffles a string randomly
     * @param input String to shuffle
     * @return Shuffled string
     */
    public String shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    /**
     * MAYBE DO THIS ON THE SERVER INSTEAD?
     * Checks if the user have guessed the correct word by looking at the entered characters
     * @param correctWord The correct word
     * @return true if answer matches correctWord
     */
    private boolean checkAnswer(String correctWord) {

        String typedWord = "";
/*
        for (int i = 0; i < enteredLettersRow.getChildCount(); i++) {
            ImageButton btn = (ImageButton)enteredLettersRow.getChildAt(i);
            typedWord += btn.getTag();
        }
*/
        return typedWord.equals(correctWord);
    }

    // TODO: create add actionlistner method for controller


    @Override
    public IRenderer getRenderer() {
        return renderer;
    }

    public void updatePlayerListTextView(String txt){
        this.playerListTextView.setText(txt);
    }

    @Override
    public void setDrawListener(OnTouchListener touchListener) {
        renderer.setDrawerListener(touchListener);
    }


}
