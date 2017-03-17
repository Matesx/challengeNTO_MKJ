package com.example.matteo.challengento_mkj.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GIRAUDK on 17/03/2017.
 */

public class Donnees implements Parcelable {
    private float [] accelerometerVector;
    private float horizontalX;
    private float horizontalY;

    public Donnees (float[] accelerometerVector, float horizontalX, float horizontalY){
        this.accelerometerVector = accelerometerVector;
        this.horizontalX = horizontalX;
        this.horizontalY = horizontalY;
    }

    protected Donnees(Parcel in) {
        accelerometerVector = in.createFloatArray();
        horizontalX = in.readFloat();
        horizontalY = in.readFloat();
    }

    public static final Creator<Donnees> CREATOR = new Creator<Donnees>() {
        @Override
        public Donnees createFromParcel(Parcel in) {
            return new Donnees(in);
        }

        @Override
        public Donnees[] newArray(int size) {
            return new Donnees[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloatArray(accelerometerVector);
        dest.writeFloat(horizontalX);
        dest.writeFloat(horizontalY);
    }
}
