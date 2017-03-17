package com.example.matteo.challengento_mkj.Multijoueur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.matteo.challengento_mkj.R;

public class Score extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textView = (TextView) findViewById(R.id.textViewScore);

        textView.setText(""+getIntent().getExtras().get("score"));
    }
}
