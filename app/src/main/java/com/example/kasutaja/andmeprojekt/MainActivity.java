package com.example.kasutaja.andmeprojekt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int arv;
    ArrayList<DataObject> allObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveDataFromPhone();

        if(allObjects != null) {
            arv = allObjects.size();
            for (DataObject dO : allObjects) {
                addField(dO);
            }
        }

        TextView Tarv = findViewById(R.id.textView);

        Tarv.setText(String.valueOf(arv));

        FloatingActionButton fb = findViewById(R.id.dataFloatingActionButton);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateData.class));
            }
        });
    }

    protected void retrieveDataFromPhone() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("TestSavingFile", Context.MODE_PRIVATE);
        String hashString = sharedPreferences.getString("HashString", null);
        if(hashString == null){
            allObjects = null;
        }else {
            allObjects = gson.fromJson(hashString, new TypeToken<ArrayList<DataObject>>() {}.getType());
        }
    }

    protected void addField(DataObject dataObject) {
        LinearLayout ll = findViewById(R.id.llMainActivity);
        Button menuObjectBt = new Button(getApplicationContext());
        menuObjectBt .setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        menuObjectBt.setId(dataObject.getObjectId());
        menuObjectBt.setText(dataObject.getName() + String.valueOf(dataObject.getObjectId()));
        menuObjectBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateData.class).putExtra("ObjectId", view.getId()));
            }
        });

        ll.addView(menuObjectBt);
    }

}
