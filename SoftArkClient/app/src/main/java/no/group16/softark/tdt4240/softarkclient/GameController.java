package no.group16.softark.tdt4240.softarkclient;

import android.content.Context;
import android.view.View;

/**
 * Created by tien on 4/14/2016.
 */
public class GameController extends Controller {

    public GameController(Context context){
        super(context);
    }

    @Override
    public void startNewWord(String word){
        gameView.generateKeyboard(word, gameLogic.getMAX_LETTERS_PER_ROW(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.moveLetterBtn(v, gameLogic.getMAX_LETTERS_PER_ROW());
            }
        });
    }

}
