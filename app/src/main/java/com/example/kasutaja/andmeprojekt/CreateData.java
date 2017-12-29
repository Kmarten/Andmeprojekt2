package com.example.kasutaja.andmeprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Ahto on 28.12.2017.
 */

public class CreateData extends AppCompatActivity {

    int arv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data);

        arv = getIntent().getIntExtra("arv", 0);

        FloatingActionButton create = (FloatingActionButton) findViewById(R.id.bCreate);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //collectData();
                //saveData();
                startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("arv", arv+1));
            }
        });
    }
}
