package no.group16.softark.tdt4240.softarkclient;

/**
 * Created by tien on 4/14/2016.
 */
public class GameManager {
    private static GameManager ourInstance = new GameManager();

    private IAssetManager assetManager;
    private ServerHandler serverHandler;
    private Controller gameController;

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

    public ServerHandler getServerHandler() {
        return serverHandler;
    }

    public void setServerHandler(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    public Controller getGameController() {
        return gameController;
    }

    public void setGameController(Controller gameController) {
        this.gameController = gameController;
    }
}
