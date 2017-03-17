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

public class EnregistrementScenario extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor magnetic;
    private Sensor accelerometer;

    // Attribut de la classe pour calculer  l'orientation et l'accelerometre
    private float[] acceleromterVector=new float[3];
    private float[] magneticVector=new float[3];
    private float[] resultMatrix=new float[9];
    private float[] values=new float[3];

    private List<Donnees> listDonnees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_scenario);

        listDonnees = new ArrayList<Donnees>();



        final Timer timerPrelevDonnees = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Donnees donnees = new Donnees(acceleromterVector, values[1], values[2]);
                        listDonnees.add(donnees);
                    }
                });
            }
        };

        timerPrelevDonnees.scheduleAtFixedRate(timerTask, 0, Long.valueOf(200));

        final Timer timerJeu = new Timer();
        timerJeu.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        timerPrelevDonnees.cancel();
                        timerJeu.cancel();

                        Intent intent = new Intent(EnregistrementScenario.this, LancementScenario.class);
                        startActivity(intent);
                    }
                });

            }
        }, 5000, 5000);
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
    }
}
