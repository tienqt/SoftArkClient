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
        });
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

            String toolId = json.getString("drawingTool");
            String colorId = json.getString("color");
            JSONArray points = json.getJSONArray("points");
            ArrayList<Point> pointList = new ArrayList<>();

            for(int i = 0; i < points.length(); i++) {
                int x = ((JSONObject)points.get(i)).getInt("x");
                int y = ((JSONObject)points.get(i)).getInt("y");
                pointList.add(new Point(x, y));
            }

            DrawingPath drawingPath = new DrawingPath(pointList, toolId, colorId);

            gameLogic.addPath(drawingPath);
            gameView.getRenderer().onUpdate();
            gameView.getRenderer().onRender();

    }

    @Override
    protected void handleNewPathByUser(DrawingPath drawingPath) {
        gameLogic.addPath(drawingPath);
        gameView.getRenderer().onUpdate();
        gameView.getRenderer().onRender();

        JSONArray jsonPoints = new JSONArray(drawingPath.points);
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

    ArrayList<Point> tmpPath = new ArrayList<Point>();

}
