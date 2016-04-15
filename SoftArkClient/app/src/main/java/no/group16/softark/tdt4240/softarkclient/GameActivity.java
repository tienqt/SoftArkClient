package no.group16.softark.tdt4240.softarkclient;

import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TableRow keyRow1;
    private TableRow keyRow2;
    private LinearLayout enteredLettersBar;

    private TextView playerListTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);


        keyRow1 = (TableRow)findViewById(R.id.keyboardRow1);
        keyRow2 = (TableRow)findViewById(R.id.keyboardRow2);
        enteredLettersBar = (LinearLayout)findViewById(R.id.enteredLettersBar);

        playerListTextView = (TextView)findViewById(R.id.playersTextView);

        updatePlayerList();
        generateKeyboard(generateLetters("pannekake"));

    }

    private String generateLetters(String originalWord) {
        String letterSet = originalWord;
        Random r = new Random();
        while(letterSet.length() < 13) {
            char c = (char)(r.nextInt(26) + 'a');
            letterSet += c;
        }
        return shuffle(letterSet);
    }

    private void generateKeyboard(String word) {

        word = word.toLowerCase();
        int count = 0;

        for(int i = 0; i < word.length(); i++) {
            ImageButton imgBtn = new ImageButton(this);
            imgBtn.setBackgroundColor(Color.TRANSPARENT);
            char c = word.charAt(i);
            imgBtn.setImageResource(getResources().getIdentifier(c + "_grey", "drawable", getPackageName()));
            imgBtn.setMinimumHeight(0);
            imgBtn.setMinimumWidth(0);
            imgBtn.setPadding(2,4,2,2);

            if(count < 13)
                keyRow1.addView(imgBtn);
            else
                keyRow2.addView(imgBtn);

            imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((TableRow)v.getParent()).removeView(v);
                    ImageButton btn = (ImageButton)v;
                    enteredLettersBar.addView(btn);
                }
            });

            count++;
        }
    }

    private void updatePlayerList() {
        playerListTextView.setText(Html.fromHtml(
                "Even (2300)<br>" +
                        "Viktor (2200)<br>" +
                        "Tom (2000)<br>" +
                        "Daniel (2000)<br>" +
                        "Tien (1100)<br>"
        ));
    }

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
}
