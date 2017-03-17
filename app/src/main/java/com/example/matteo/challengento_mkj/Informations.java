package com.example.matteo.challengento_mkj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class Informations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        final TextView TextViewInfo = (TextView) findViewById(R.id.textViewInfo);

        TextViewInfo.setText("Bienvenue sur l'application Chore App ! \n L'application qui va vous faire bouger ! :)");

    }
}
