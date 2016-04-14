package no.group16.softark.tdt4240.softarkclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        getSupportActionBar().hide();
    }
}
