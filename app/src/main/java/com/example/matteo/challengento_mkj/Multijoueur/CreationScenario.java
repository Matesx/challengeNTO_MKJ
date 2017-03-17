package com.example.matteo.challengento_mkj.Multijoueur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.matteo.challengento_mkj.R;

public class CreationScenario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_scenario);

        final Button commencer = (Button) findViewById(R.id.buttonCommencer);

        commencer.setText("COMMENCER LA CREATION DE LA CHOREGRAPHIE !");

        commencer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(CreationScenario.this, EnregistrementScenario.class);
                startActivity(intent1);
            }
        });
    }
}
