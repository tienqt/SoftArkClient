package no.group16.softark.tdt4240.softarkclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private Button joinGameBtn;
    private Button createGameBtn;
    private TextView gameCodeTxt;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        joinGameBtn = (Button) findViewById(R.id.joinGameBtn);
        createGameBtn = (Button) findViewById(R.id.createGameBtn);
        gameCodeTxt = (TextView) findViewById(R.id.gameCodeText);



        joinGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameActivity.class);
                startActivity(intent);
            }
        });

    }
}