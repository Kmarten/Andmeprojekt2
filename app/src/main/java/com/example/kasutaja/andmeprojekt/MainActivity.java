package com.example.kasutaja.andmeprojekt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kasutaja.andmeprojekt.customViews.MainListObject;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity { //implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener

    private LinearLayout Prof_Section;
    private Button SignOut;
    private SignInButton SignIn;
    private TextView Name,Email;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;

    int arv;
    boolean isEditable = false;
    ArrayList<DataObject> allObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        /*
        Prof_Section = (LinearLayout)findViewById(R.id.prof_section);
        SignOut = (Button)findViewById(R.id.bn_logout);
        SignIn = (SignInButton)findViewById(R.id.bn_login);
        Name = (TextView)findViewById(R.id.name);
        Email = (TextView)findViewById(R.id.email);
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        Prof_Section.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        */

        retrieveDataFromPhone();

        if(allObjects != null) {
            arv = allObjects.size();
            for (DataObject dO : allObjects) {
                addField(dO);
            }
        }

        //TextView Tarv = findViewById(R.id.textView);

        //Tarv.setText(String.valueOf(arv));


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
    /*
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bn_login:
                signIn();
                break;
            case R.id.bn_logout:
                signOut();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    private void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            Name.setText(name);
            Email.setText(email);
            updateUI(true);
        } else {
            updateUI(false);
        }
    }

    private void updateUI(boolean isLogin){
        if(isLogin) {
            Prof_Section.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);
            setContentView(R.layout.activity_main);
        } else {
            Prof_Section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }*/
}
