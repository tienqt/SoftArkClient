package no.group16.softark.tdt4240.softarkclient;

import android.content.Context;

/**
 * Created by tien on 4/14/2016.
 */
public class GameController implements IController {

    ILogic logic;
    IView view;
    IServerHandler serverHandler;

    public GameController(Context context){
        logic = new GameLogic();
        view = new GameView(context);
        serverHandler = GameManager.getInstance().getServerHandler();

        init();
    }

    public void init(){
        // TODO: init all UI actionlisteners
        // TODO: init all Server actionlistener
    }
}
