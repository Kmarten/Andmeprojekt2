package com.example.kasutaja.andmeprojekt;  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.content.Context; //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.content.Intent;  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.content.SharedPreferences;   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.os.Bundle;   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.support.design.widget.FloatingActionButton;  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.support.design.widget.Snackbar;  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.support.v7.app.AppCompatActivity;    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.text.InputType;  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.view.View;   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.widget.Button;   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.widget.LinearLayout; //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import android.widget.TextView; //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import com.example.kasutaja.andmeprojekt.customViews.MainListObject;    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import com.google.gson.Gson;    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import com.google.gson.reflect.TypeToken;   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
import java.util.ArrayList; //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
public class MainActivity extends AppCompatActivity {   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    int arv;    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    boolean isEditabe = false;  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    ArrayList<DataObject> allObjects;   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    @Override   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    protected void onCreate(Bundle savedInstanceState) {    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        super.onCreate(savedInstanceState); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        setContentView(R.layout.activity_main); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        retrieveDataFromPhone();    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        if(allObjects != null) {    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            arv = allObjects.size();    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            for (DataObject dO : allObjects) {  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
                addField(dO);   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        //TextView Tarv = findViewById(R.id.textView);  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        //Tarv.setText(String.valueOf(arv));    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        FloatingActionButton fb = findViewById(R.id.dataFloatingActionButton);  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        fb.setOnClickListener(new View.OnClickListener() {  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            @Override   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            public void onClick(View view) {    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
                startActivity(new Intent(getApplicationContext(), CreateData.class));   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        }); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    protected void retrieveDataFromPhone() {    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        Gson gson = new Gson(); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("TestSavingFile", Context.MODE_PRIVATE); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        String hashString = sharedPreferences.getString("HashString", null);    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        if(hashString == null){ //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            allObjects = null;  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        }else { //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            allObjects = gson.fromJson(hashString, new TypeToken<ArrayList<DataObject>>() {}.getType());    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    protected void openObject(View view) {  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        startActivity(new Intent(MainActivity.this, CreateData.class).putExtra("ObjectId", view.getId()));  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    protected void addField(DataObject dataObject) {    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        LinearLayout ll = findViewById(R.id.llMainActivity);    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        MainListObject menuObject = new MainListObject(getApplicationContext());    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        menuObject.setLayoutParams(new LinearLayout.LayoutParams(   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
                LinearLayout.LayoutParams.WRAP_CONTENT, //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
                LinearLayout.LayoutParams.WRAP_CONTENT  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        )); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        menuObject.setId(dataObject.getObjectId()); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        menuObject.objectName.setInputType(InputType.TYPE_NULL);    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        menuObject.objectName.setClickable(true);   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        menuObject.objectCategory.setInputType(InputType.TYPE_NULL);    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        menuObject.objectCategory.setClickable(true);   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        menuObject.objectName.setOnClickListener(new View.OnClickListener() {   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            @Override   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            public void onClick(View view) {    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
                openObject(menuObject); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        }); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        menuObject.objectCategory.setOnClickListener(new View.OnClickListener() {   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            @Override   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            public void onClick(View view) {    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
                openObject(menuObject); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        }); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        menuObject.setOnClickListener(new View.OnClickListener() {  //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            @Override   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            public void onClick(View view) {    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
               openObject(view);    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
            }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        }); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
        ll.addView(menuObject); //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    }   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
}   //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very
    //get memed kiddo, i own ur toaster now. xD uwu i like ur toast very