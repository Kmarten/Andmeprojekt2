package com.example.kasutaja.andmeprojekt;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int arv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arv = getIntent().getIntExtra("arv", 0);

        TextView Tarv = findViewById(R.id.textView);

        Tarv.setText(String.valueOf(arv));

        FloatingActionButton fb = findViewById(R.id.dataFloatingActionButton);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateData.class).putExtra("arv", arv));
            }
        });
    }
}
