package no.group16.softark.tdt4240.softarkclient;

import android.content.Context;

/**
 * Created by tien on 4/14/2016.
 */
public abstract class Controller {
    protected Logic gameLogic;
    protected IView gameView;
    protected ServerHandler serverHandler;

    protected Controller(Context context){
        gameView = new GameView(context);
        gameLogic = new GameLogic();
        serverHandler = GameManager.getInstance().getServerHandler();
    }

    abstract public void startNewWord(String word);

    public Logic getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(Logic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public IView getGameView() {
        return gameView;
    }

    public void setGameView(IView gameView) {
        this.gameView = gameView;
    }

    public ServerHandler getServerHandler() {
        return serverHandler;
    }

    public void setServerHandler(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }
}
