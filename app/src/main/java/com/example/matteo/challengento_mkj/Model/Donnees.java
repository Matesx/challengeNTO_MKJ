package com.example.matteo.challengento_mkj.Model;

/**
 * Created by GIRAUDK on 17/03/2017.
 */

public class Donnees {
    private float [] accelerometerVector;
    private float horizontalX;
    private float horizontalY;

    public Donnees (float[] accelerometerVector, float horizontalX, float horizontalY){
        this.accelerometerVector = accelerometerVector;
        this.horizontalX = horizontalX;
        this.horizontalY = horizontalY;
    }

    public float[] getAccelerometerVector() {
        return accelerometerVector;
    }

    public void setAccelerometerVector(float[] accelerometerVector) {
        this.accelerometerVector = accelerometerVector;
    }

    public float getHorizontalX() {
        return horizontalX;
    }

    public void setHorizontalX(float horizontalX) {
        this.horizontalX = horizontalX;
    }

    public float getHorizontalY() {
        return horizontalY;
    }

    public void setHorizontalY(float horizontalY) {
        this.horizontalY = horizontalY;
    }
}
