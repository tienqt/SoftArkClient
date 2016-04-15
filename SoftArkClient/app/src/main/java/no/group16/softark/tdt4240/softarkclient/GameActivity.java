package no.group16.softark.tdt4240.softarkclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        GameView gameView = new GameView(this);
        setContentView(gameView);

    }
}
