package com.example.matteo.challengento_mkj.Multijoueur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.matteo.challengento_mkj.Model.Donnees;
import com.example.matteo.challengento_mkj.R;

import java.util.ArrayList;
import java.util.List;

public class LancementScenario extends AppCompatActivity {
    private Button button;
    private List<Donnees> listDonnees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancement_scenario);
        button = (Button) findViewById(R.id.buttonLancerScenario);
        listDonnees = getIntent().getExtras().getParcelableArrayList("list");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LancementScenario.this, ScenarioEnCours.class);
                intent.putParcelableArrayListExtra("list", (ArrayList<Donnees>) listDonnees);
                startActivity(intent);
            }
        });
    }
}
