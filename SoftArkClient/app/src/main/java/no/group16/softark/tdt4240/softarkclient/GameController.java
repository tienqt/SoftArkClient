package no.group16.softark.tdt4240.softarkclient;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tien on 4/14/2016.
 */
public class GameController extends Controller implements IReceiver {

    ArrayList<Point> tmpPoints = new ArrayList<Point>();
    DrawingPath tmpPath;
    int tmpPathIndex = -1;

    public GameController(Context context){
        super(context);

        GameManager.getInstance().getServerHandler().registerListener("newWord", this);


        /*gameView.getRenderer().setTouchEventListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int)event.getX();
                int y = (int)event.getY();

                ArrayList<Point> tmpPath = new ArrayList<Point>();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        tmpPath.clear();
                        tmpPath.add(new Point(x, y));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        tmpPath.add(new Point(x, y));
                        break;
                    case MotionEvent.ACTION_UP:
                        DrawingPath dp = new DrawingPath(tmpPath, "", ""); //TODO
                        handleNewPathByUser(dp);

                        tmpPath.clear();
                        break;
                }
                return true;
            }
        });*/

        GameManager.getInstance().getServerHandler().registerListener("newWord", this);
        GameManager.getInstance().getServerHandler().registerListener("checkAnswerResponse", this);
        GameManager.getInstance().getServerHandler().registerListener("gameOverNotification", this);
        GameManager.getInstance().getServerHandler().registerListener("newPathDrawn", this);

        /*
        gameView.getRenderer().setTouchEventListener(new TouchInputEvent() {
            @Override
            public void onTouchInputEvent(Point point, int action) {
                switch (action) {
                    case MotionEvent.ACTION_DOWN:       // TODO: create generic "types"
                        tmpPath.clear();
                        tmpPath.add(point);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        tmpPath.add(point);
                        break;
                    case MotionEvent.ACTION_UP:
                        DrawingPath dp = new DrawingPath(tmpPath, "", ""); //TODO
                        handleNewPathByUser(dp);

                        tmpPath.clear();
                        break;
                }
            }
        });*/
    }


    @Override
    public void onInit() {

        gameView.setDrawListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:       // TODO: create generic "types"
                        tmpPath = new DrawingPath(new ArrayList<Point>(), "", ""); //TODO
                        tmpPath.points.add(new Point((int)event.getX(), (int)event.getY()));
                        tmpPathIndex = gameLogic.getPaths().size()-1;
                        handleTemporaryPathByUser(tmpPath);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        tmpPath.points.add(new Point((int)event.getX(), (int)event.getY()));
                        //tmpPathIndex = gameLogic.getPaths().size() -1;
                        handleTemporaryPathByUser(tmpPath);
                        break;
                    case MotionEvent.ACTION_UP:
                        tmpPath.points.add(new Point((int)event.getX(), (int)event.getY()));
                        handleNewPathByUser(tmpPath);
                        tmpPathIndex = -1;
                        break;
                }
                return true;
            }
        });

        gameView.getRenderer().onRender();  // just to clear it
    }

    @Override
    public void handleStartNewWord(JSONObject json) throws JSONException {
        String newWord = json.getString("word");
        String drawer = json.getString("drawer");
        gameLogic.newWord(newWord, drawer);

        gameView.generateKeyboard(newWord, gameLogic.getMAX_LETTERS_PER_ROW(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.moveLetterBtn(v, gameLogic.getMAX_LETTERS_PER_ROW());
            }
        });
    }

    @Override
    public void handleNewPathByServer(JSONObject json) throws JSONException {

            //String toolId = json.getString("drawingTool");
            //String colorId = json.getString("color");
            JSONArray points = json.getJSONArray("points");
            ArrayList<Point> pointList = new ArrayList<>();

            for(int i = 0; i < points.length(); i+=2) {
                int x = ((Integer) points.get(i));
                int y = ((Integer)points.get(i+1));
                pointList.add(new Point(x, y));
            }

            DrawingPath drawingPath = new DrawingPath(pointList, "", "");
            gameLogic.addPath(drawingPath);
            gameView.getRenderer().onUpdate(gameLogic.getPaths());
            gameView.getRenderer().onRender();

    }

    @Override
    protected void handleNewPathByUser(DrawingPath drawingPath) {
        JSONArray jsonPoints = new JSONArray();
        for(int i = 0; i < drawingPath.points.size(); i++) {
            //JSONObject pt = new JSONObject();
            jsonPoints.put(drawingPath.points.get(i).x);
            jsonPoints.put(drawingPath.points.get(i).y);
        }

        JSONObject msg = new JSONObject();
        try {
            msg.put("type", "newPathDrawn");
            msg.put("drawingTool", drawingPath.drawingToolId);
            msg.put("color", drawingPath.colorId);
            msg.put("points", jsonPoints);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GameManager.getInstance().getServerHandler().queueMessage(msg);

        gameLogic.addPath(drawingPath);              // TODO: Check that it's not alrady added (tmpPath)
        gameView.getRenderer().onUpdate(gameLogic.getPaths());
        gameView.getRenderer().onRender();

    }

    @Override
    protected void handleTemporaryPathByUser(DrawingPath drawingPath) {
        if(tmpPathIndex != -1)
            gameLogic.getPaths().set(tmpPathIndex, drawingPath);


        gameView.getRenderer().onUpdate(gameLogic.getPaths());
        gameView.getRenderer().onRender();
    }

    @Override
    public void onReceive(JSONObject json) throws JSONException {
        String type = json.getString("type");

        if(type.equals("newWord")) {
            handleStartNewWord(json);

        } else if(type.equals("gameOverNotification")) {



        } else if(type.equals("checkAnswerResponse")) {
            String result = json.getString("result");

            if(result.equals("right")) {
                String correctWord = json.getString("correctWord");
                String guesser = json.getString("guesser");
                JSONArray newScores = json.getJSONArray("newPlayerScores");
            } else {

            }
        } else if(type.equals("newPathDrawn")) {
            handleNewPathByServer(json);
        }
    }



}
