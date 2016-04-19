package no.group16.softark.tdt4240.softarkclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity implements IReceiver {

    private Button joinGameBtn;
    private Button createGameBtn;
    private TextView gameCodeTxt;
    private final Context context = this;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        joinGameBtn = (Button) findViewById(R.id.joinGameBtn);
        createGameBtn = (Button) findViewById(R.id.createGameBtn);
        gameCodeTxt = (TextView) findViewById(R.id.gameCodeText);

        if(GameManager.getInstance().getServerHandler().isConnected() == false) {
            GameManager.getInstance().getServerHandler().connect("10.0.0.8", 54555);
            progress = new ProgressDialog(this);
            progress.setTitle(getResources().getString(R.string.pleasewait));
            progress.setMessage(getResources().getString(R.string.connecting));
            progress.show();
        }

        joinGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: TMP
                //Intent intent = new Intent(context, LobbyActivity.class);
                //startActivity(intent);
                if(GameManager.getInstance().getServerHandler().isConnected()) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("type", "joinLobbyRequest");
                        obj.put("gamePin", gameCodeTxt.getText().toString());
                        GameManager.getInstance().getServerHandler().queueMessage(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Error: Not connected to server", Toast.LENGTH_SHORT).show();
                }
            }
        });
        createGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameManager.getInstance().getServerHandler().isConnected()) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("type", "getWordListRequest");
                        GameManager.getInstance().getServerHandler().queueMessage(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, getResources().getString(R.string.notconnected), Toast.LENGTH_SHORT).show();
                }
            }
        });

        GameManager.getInstance().getServerHandler().registerListener("joinLobbyResponse", this);
        GameManager.getInstance().getServerHandler().registerListener("getWordListResponse", this);
        GameManager.getInstance().getServerHandler().registerListener("connectionEvent", this);
    }

    @Override
    protected void onDestroy() {
        if(progress != null)
            progress.dismiss();
        super.onDestroy();
    }

    @Override
    public void onReceive(final JSONObject json) throws JSONException {
        String type = json.getString("type");

        if(type.equals("getWordListResponse")) {

            JSONArray result = json.getJSONArray("wordListNames");

            String[] list = new String[result.length()];
            for(int i = 0; i < result.length(); i++){
                list[i] = result.get(i).toString();
            }

            Intent intent = new Intent(context, CreateGameActivity.class);
            intent.putExtra("wordCategories", list);
            startActivity(intent);


        } else if (type.equals("joinLobbyResponse")) {

            String result = json.getString("result");

            if(result.equals("accepted")) {
                Intent intent = new Intent(context, LobbyActivity.class);
                intent.putExtra("gamePin", gameCodeTxt.getText().toString());
                intent.putExtra("gameOwner", json.getString("gameOwner"));
                startActivity(intent);
            } else if (result.equals("full")) {
                Toast toast = Toast.makeText(context, getResources().getString(R.string.roomisfull), Toast.LENGTH_SHORT);
                toast.show();
            } else if (result.equals("game not found")) {
                Toast toast = Toast.makeText(context, getResources().getString(R.string.roomnotfound), Toast.LENGTH_SHORT);
                toast.show();
            }
        } else if(type.equals("connectionEvent")) {
            String event = json.getString("event");

            if(event.equals("error")) {
                String message = json.getString("message");
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            } else if(event.equals("connected")) {
                System.out.println("------ connected ------");
            }

            if(progress != null)
                progress.dismiss();

        }

    }


}