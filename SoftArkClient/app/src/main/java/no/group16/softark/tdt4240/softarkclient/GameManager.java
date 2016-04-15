package no.group16.softark.tdt4240.softarkclient;

/**
 * Created by tien on 4/14/2016.
 */
public class GameManager {
    private static GameManager ourInstance = new GameManager();

    private IAssetManager assetManager;
    private IServerHandler serverHandler;
    private IController gameController;

    public static GameManager getInstance() {
        return ourInstance;
    }

    private GameManager() {
        assetManager = new SimpleAssetManager();
        serverHandler = new KyroNetServerHandler();
    }

    public IAssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
    }


    public IServerHandler getServerHandler() {
        return serverHandler;
    }

    public void setServerHandler(IServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    public IController getGameController() {
        return gameController;
    }

    public void setGameController(IController gameController) {
        this.gameController = gameController;
    }
}
