package com.example.kasutaja.andmeprojekt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.kasutaja.andmeprojekt.customViews.MainListObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int arv;
    boolean isEditable = false;
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

    protected void openObject(View view) {
        startActivity(new Intent(MainActivity.this, CreateData.class).putExtra("ObjectId", view.getId()));
    }
    protected void addField(DataObject dataObject) {
        LinearLayout ll = findViewById(R.id.llMainActivity);
        MainListObject menuObject = new MainListObject(getApplicationContext());
        menuObject.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        menuObject.setId(dataObject.getObjectId());
        menuObject.setObjectName(dataObject.getName());
        try {
            Glide.with(this).load(dataObject.getImg()).into(menuObject.objectImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        menuObject.objectName.setInputType(InputType.TYPE_NULL);
        menuObject.objectName.setClickable(true);

        menuObject.objectCategory.setInputType(InputType.TYPE_NULL);
        menuObject.objectCategory.setClickable(true);

        menuObject.objectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openObject(menuObject);
            }
        });

        menuObject.objectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openObject(menuObject);
            }
        });

        menuObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openObject(view);
            }
        });

        ll.addView(menuObject);
    }

}
