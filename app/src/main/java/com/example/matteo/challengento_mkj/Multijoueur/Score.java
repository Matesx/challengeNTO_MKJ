package com.example.matteo.challengento_mkj.Multijoueur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.matteo.challengento_mkj.Jouer;
import com.example.matteo.challengento_mkj.MainActivity;
import com.example.matteo.challengento_mkj.R;

public class Score extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textView = (TextView) findViewById(R.id.textViewScore);
        final Button buttonReplay = (Button) findViewById(R.id.buttonReplay);
        final Button buttonMenu = (Button) findViewById(R.id.buttonMenu);

        textView.setText("Votre Score : "+getIntent().getExtras().get("score"));

        buttonReplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(Score.this, CreationScenario.class);
                startActivity(intent1);
                finish();

            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(Score.this, MainActivity.class);
                startActivity(intent1);
                finish();

            }
        });
    }
}
