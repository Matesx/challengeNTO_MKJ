package com.example.matteo.challengento_mkj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.matteo.challengento_mkj.Multijoueur.CreationScenario;

public class Jouer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer);

        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);

        button1.setText("1 JOUEUR");
        button2.setText("2 JOUEURS");

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(Jouer.this, Jouer.class);
                startActivity(intent1);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(Jouer.this, CreationScenario.class);
                startActivity(intent2);

            }
        });

    }
}
