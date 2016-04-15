package no.group16.softark.tdt4240.softarkclient;

import android.graphics.Path;
import android.text.BoringLayout;
import android.widget.ImageButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tien on 4/15/2016.
 */
public class GameLogic extends Logic {

    public class DrawingPath extends Path {
        String drawingToolId;

        public DrawingPath(String drawingToolId) {
            this.drawingToolId = drawingToolId;
        }

        public String getDrawingToolId() {
            return drawingToolId;
        }

    }

    ArrayList<DrawingPath> paths;
    HashMap<String, Integer> players;
    HashMap<String, DrawingTool> drawingTools;

    String selectedCharacters;
    String availableCharacters;

    DrawingTool curentActiveTool;
    Integer secondsLeft;
    Boolean isGameOwner;

    Boolean myTurnToDraw;
    String whoIsDrawing;



    public GameLogic(){
        super(14);
        paths = new ArrayList<>();
        players = new HashMap<String, Integer>() ;
        drawingTools = new HashMap<String, DrawingTool>();
        drawingTools.put(Pencil.id, new Pencil());
        drawingTools.put(Eraser.id, new Eraser());
    }

    public void updatePlayerList(JSONObject data) {

    }

    public void setCurentActiveTool(String id) {
        this.curentActiveTool = drawingTools.get(id);
    }

    public HashMap<String, Integer> getPlayers() {
        return players;
    }

    public HashMap<String, DrawingTool> getDrawingTools() {
        return drawingTools;
    }

    public DrawingTool getCurentActiveTool() {
        return curentActiveTool;
    }

    public String getWhoIsDrawing() {
        return whoIsDrawing;
    }

    public void setWhoIsDrawing(String whoIsDrawing) {
        this.whoIsDrawing = whoIsDrawing;
    }

    public Boolean getMyTurnToDraw() {
        return myTurnToDraw;
    }

    public void setMyTurnToDraw(Boolean myTurnToDraw) {
        this.myTurnToDraw = myTurnToDraw;
    }

    public Boolean getGameOwner() {
        return isGameOwner;
    }

    public void setGameOwner(Boolean gameOwner) {
        isGameOwner = gameOwner;
    }

    public Integer getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(Integer secondsLeft) {
        this.secondsLeft = secondsLeft;
    }

    public void addPath(JSONObject data) {
        //paths.add(new DrawingPath())
    }

    public String getSelectedCharacters() {
        return selectedCharacters;
    }

    public void setSelectedCharacters(String characters) {
        this.selectedCharacters = characters;
    }

    public String getAvailableCharacters() {
        return availableCharacters;
    }

    public void setAvailableCharacters(String characters) {
        this.availableCharacters = characters;
    }
}
