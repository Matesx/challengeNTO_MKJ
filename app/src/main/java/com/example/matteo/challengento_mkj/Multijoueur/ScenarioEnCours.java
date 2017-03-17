package com.example.matteo.challengento_mkj.Multijoueur;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.matteo.challengento_mkj.Model.Donnees;
import com.example.matteo.challengento_mkj.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ScenarioEnCours extends AppCompatActivity implements SensorEventListener{
    private TextView textViewScenarioEnCours;
    private SensorManager sensorManager;
    private Sensor magnetic;
    private Sensor accelerometer;

    private Timer timerPrelevDonnees;
    private Timer timerJeu;

    // Attribut de la classe pour calculer  l'orientation et l'accelerometre
    private float[] acceleromterVector=new float[3];
    private float[] magneticVector=new float[3];
    private float[] resultMatrix=new float[9];
    private float[] values=new float[3];

    private List<Donnees> listDonneesRecues;
    private List<Donnees> listDonnees;
    private int compteur;
    private final int margeErreurHorizontalite = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_en_cours);

        listDonneesRecues = getIntent().getExtras().getParcelableArrayList("list");

        // Instancier le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Instantiate the magnetic sensor and its max range
        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        // Instantiate the accelerometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textViewScenarioEnCours = (TextView) findViewById(R.id.textViewScenarioEnCours);
        listDonnees = new ArrayList<>();

        compteur=6;
        textViewScenarioEnCours.setText(""+compteur);

        timerPrelevDonnees = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Donnees donnees = new Donnees(acceleromterVector, (float)Math.toDegrees(values[1]), (float)Math.toDegrees(values[2]));
                        listDonnees.add(donnees);

                    }
                });
            }
        };

        timerPrelevDonnees.schedule(timerTask, 0, Long.valueOf(20));


        timerJeu = new Timer();
        timerJeu.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        textViewScenarioEnCours.setText(""+ --compteur);

                        if (compteur ==0) {
                            timerPrelevDonnees.cancel();
                            timerJeu.cancel();

                            Intent intent = new Intent(ScenarioEnCours.this, Score.class);
                            intent.putExtra("score", calculScore());
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        }, 0, 1000);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Mettre à jour la valeur de l'accéléromètre et du champ magnétique
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            acceleromterVector=event.values;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticVector=event.values;
        }

        // Demander au sensorManager la matric de Rotation (resultMatric)
        SensorManager.getRotationMatrix(resultMatrix, null, acceleromterVector, magneticVector);

        // Demander au SensorManager le vecteur d'orientation associé (values)
        SensorManager.getOrientation(resultMatrix, values);
    }

    private int calculScore(){
        int score = 5000;
        for (int i=0; i<listDonneesRecues.size(); i+=10){

            List<Donnees> listEtalon = new ArrayList<>();
            List<Donnees> listAComparer = new ArrayList<>();

            for(int j=i; j<i+10 && j<listDonneesRecues.size(); j++){
                listEtalon.add(listDonneesRecues.get(j));
                listAComparer.add(listDonnees.get(j));
                /*if(listAComparer!= null && listAComparer.get(j)!= null) {
                    score -= compareHorizontalite(listAComparer.get(j));
                }*/
            }


            if (!listEtalon.isEmpty() && !listAComparer.isEmpty()){
                score -= compareAccelerometer(listEtalon, listAComparer);

            }
        }

        for (int i=0; i<listDonnees.size(); i++){
            score -= compareHorizontalite(listDonnees.get(i));
        }

        return score;
    }

    private int compareAccelerometer(List<Donnees> listEtalon, List<Donnees> listAComparer){
        int sommeEtalonX = 0;
        int sommeAComparerX = 0;
        int sommeEtalonY = 0;
        int sommeAComparerY = 0;
        int sommeEtalonZ = 0;
        int sommeAComparerZ = 0;
        int resultat = 0;

        for(int j=0; j<listEtalon.size(); j++){
            for (int i = 0; i<listEtalon.get(j).getAccelerometerVector().length; i++){
                switch(i){
                    case 0 :
                        sommeEtalonX += listEtalon.get(j).getAccelerometerVector()[i];
                        sommeAComparerX += listAComparer.get(j).getAccelerometerVector()[i];
                        break;
                    case 1 :
                        sommeEtalonY += listEtalon.get(j).getAccelerometerVector()[i];
                        sommeAComparerY += listAComparer.get(j).getAccelerometerVector()[i];
                        break;
                    case 2:
                        sommeEtalonZ += listEtalon.get(j).getAccelerometerVector()[i];
                        sommeAComparerZ += listAComparer.get(j).getAccelerometerVector()[i];
                        break;
                }
            }
        }
        resultat += Math.abs(sommeEtalonX-sommeAComparerX);
        resultat += Math.abs(sommeEtalonY-sommeAComparerY);
        resultat += Math.abs(sommeEtalonZ-sommeAComparerZ);

        return resultat;
    }

    private int compareHorizontalite(Donnees d){
        int pointPerdu = Math.abs(Math.round(d.getHorizontalX())/margeErreurHorizontalite);
        pointPerdu += Math.abs(Math.round(d.getHorizontalY())/margeErreurHorizontalite);

        return pointPerdu;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,magnetic, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this,accelerometer);
        sensorManager.unregisterListener(this,magnetic);
        timerPrelevDonnees.cancel();
        timerPrelevDonnees.purge();
        timerJeu.cancel();
        timerJeu.purge();
    }
}
