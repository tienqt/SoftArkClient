package no.group16.softark.tdt4240.softarkclient;

import android.content.Context;
import android.graphics.Point;
import android.view.SurfaceView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tien on 4/14/2016.
 */
public abstract class Controller {
    protected Logic gameLogic;
    protected IGameView gameView;
    protected ServerHandler serverHandler;

    protected Controller(Context context){
        gameView =  new GameView(context);
        gameLogic = new GameLogic();
        serverHandler = GameManager.getInstance().getServerHandler();
        onInit();

    }

    abstract protected void handleNewKeyboard(JSONObject json) throws JSONException;

    abstract protected void handleNewPathByServer(JSONObject json) throws JSONException;

    abstract protected void handleNewPathByUser(DrawingPath drawingPath);

    abstract protected void handleTemporaryPathByUser(DrawingPath drawingPath);

    public Logic getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(Logic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public IGameView getGameView() {
        return gameView;
    }

    public void setGameView(IGameView gameView) {
        this.gameView = gameView;
    }

    public ServerHandler getServerHandler() {
        return serverHandler;
    }

    public void setServerHandler(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    abstract public void onInit();


}
