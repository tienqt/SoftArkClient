package no.group16.softark.tdt4240.softarkclient;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TableRow keyRow1;
    private TableRow keyRow2;
    private TableRow enteredLettersRow;

    private TextView playerListTextView;

    private final int TOT_AVAIL_CHAR = 13;
    private final int MAX_CHAR_PER_ROW = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Hides system status/action bar -
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);
        // --------------------------------

        keyRow1 = (TableRow)findViewById(R.id.keyboardRow1);
        keyRow2 = (TableRow)findViewById(R.id.keyboardRow2);
        enteredLettersRow = (TableRow)findViewById(R.id.enteredLettersRow);

        playerListTextView = (TextView)findViewById(R.id.playersTextView);

        updatePlayerList(/*TODO: pass JSON or something?*/);    // TODO: server should trigger this
        setNewWord("pannekake");                                // TODO: server should trigger this
    }

    /**
     * MAYBE THIS SHOULD BE DONE ON THE SERVER INSTEAD, SO ALL USERS GET THE SAME?
     * Take the original word, add some random letters and shuffle all
     * @param originalWord
     * @return Shuffled string containing the original word + random letters
     */
    /*private String generateLetters(String originalWord) {
        String letterSet = originalWord;
        Random r = new Random();
        while(letterSet.length() < TOT_AVAIL_CHAR) {
            char c = (char)(r.nextInt(26) + 'a');
            letterSet += c;
        }
        return shuffle(letterSet);
    }*/

    /**
     * Takes a string (should already be shuffled) and adds the containing letters to the keyboard
     * @param string
     */
    private void generateKeyboard(String string) {
        string = string.toLowerCase();
        int count = 0;

        for(int i = 0; i < string.length(); i++) {
            ImageButton imgBtn = new ImageButton(this);
            imgBtn.setBackgroundColor(Color.TRANSPARENT);
            char c = string.charAt(i);
            imgBtn.setImageResource(getResources().getIdentifier(c + "_grey", "drawable", getPackageName()));
            imgBtn.setMinimumHeight(0);
            imgBtn.setMinimumWidth(0);
            imgBtn.setPadding(2,4,2,2);

            if(count < MAX_CHAR_PER_ROW)
                keyRow1.addView(imgBtn);
            else
                keyRow2.addView(imgBtn);

            imgBtn.setTag(c);   // The letter is stored in the tag

            imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
            });

            count++;
        }
    }

    /**
     * Updates the list showing active players/teams and their scores
     */
    private void updatePlayerList(/*TODO: pass JSON or something?*/) {
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
    /*private boolean checkAnswer(String correctWord) {

        String typedWord = "";

        for (int i = 0; i < enteredLettersRow.getChildCount(); i++) {
            ImageButton btn = (ImageButton)enteredLettersRow.getChildAt(i);
            typedWord += btn.getTag();
        }

        return typedWord.equals(correctWord);
    }*/

    private void setNewWord(String letters) {
        generateKeyboard(letters);
    }
}
