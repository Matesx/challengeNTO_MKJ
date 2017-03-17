package com.example.matteo.challengento_mkj;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        final Button buttonInfo = (Button) findViewById(R.id.buttonInfo);


        buttonPlay.setText("JOUER");
        buttonInfo.setText("INFORMATIONS");

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Jouer.class);
                startActivity(intent1);

            }
        });

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Informations.class);
                startActivity(intent2);

            }
        });

    }
}
