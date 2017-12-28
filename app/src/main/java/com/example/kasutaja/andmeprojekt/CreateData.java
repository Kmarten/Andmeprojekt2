package com.example.kasutaja.andmeprojekt;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

        Button create = (Button) findViewById(R.id.bCreate);

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
