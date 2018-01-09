package com.example.kasutaja.andmeprojekt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kasutaja.andmeprojekt.customViews.TextDataView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
                addField();
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

    protected void addField() {
        LinearLayout ll = findViewById(R.id.llMainActivity);
        Button nali = new Button(getApplicationContext());
        nali.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        ll.addView(nali);
    }

}
