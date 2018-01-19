package com.example.kasutaja.andmeprojekt;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kasutaja.andmeprojekt.customViews.TextDataView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;


public class CreateFragment extends Fragment {
    boolean isOpen = false;
    boolean editable = false;
    int index = -1;
    ArrayList<Integer> idsOfDataFields = new ArrayList<>();
    ArrayList<TextDataView> objects = new ArrayList<>();
    FloatingActionButton mButton, eButton, dButton;
    Button addViews, btDelete;
    View inflated;
    HashMap<String, String> objectData = new HashMap<>();
    ArrayList<DataObject> allObjects = new ArrayList<>();
    //Toolbar toolbar;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        inflated = inflater.inflate(R.layout.fragment_data, container, false);
        //toolbar = inflated.findViewById(this);

        mButton = inflated.findViewById(R.id.bCreate);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addField();

                /*collectData();
                createDataObject();
                saveDataToMobile();
                startActivity(new Intent(getActivity(), MainActivity.class));*/
            }
        });

       /* btDelete = inflated.findViewById(R.id.del_bt);

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editable) {
                    delete(index);
                    saveDataToMobile();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }
        });*/

        addViews = inflated.findViewById(R.id.btAddViews);
        addViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return inflated;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        retrieveDataFromPhone();
        if(getIndexFromTheIntent()) showCreatedObjectData(allObjects.get(index));
        DataObject.setId(allObjects.size());
        Toast.makeText(getContext(), "onActivity", Toast.LENGTH_SHORT).show();
    }

    private void delete(int index){
        allObjects.remove(index);
    }

    private void showCreatedObjectData(DataObject dataObject) {
        for(int i = 0; i<dataObject.getData().size(); i++){
            addField();
        }
    }

    private boolean getIndexFromTheIntent() {
        Intent intent = getActivity().getIntent();
        if(intent.hasExtra("ObjectId")){
            index = intent.getIntExtra("ObjectId", -1);
            return true;
        }
        return false;
    }

    protected void retrieveDataFromPhone() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("TestSavingFile", Context.MODE_PRIVATE);
        String hashString = sharedPreferences.getString("HashString", null);
        if(hashString != null){
            allObjects = gson.fromJson(hashString, new TypeToken<ArrayList<DataObject>>() {}.getType());
        }
    }


    protected void collectData(){

        for(int  i = 0; i<idsOfDataFields.size(); i++){
            TextDataView collectedView = getView().findViewById(idsOfDataFields.get(i));
            String collectedData = collectedView.getD().getText().toString();
            String collectedDataName = collectedView.getDn().getText().toString();
            objectData.put(collectedDataName, collectedData);
        }
    }


    private void createDataObject() {
        if(index != -1){
            allObjects.get(index).setData(objectData);
        }else {
            DataObject newObject = new DataObject("Laud", objectData);
            allObjects.add(newObject);
        }
    }

    protected void saveDataToMobile(){
        Gson gson = new Gson();
        String objectDataString = gson.toJson(allObjects);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TestSavingFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("HashString");
        editor.putString("HashString", objectDataString).apply();
    }

    protected void addField(){
            final Snackbar mySnackbar = Snackbar.make(getView(), "Data object created", Snackbar.LENGTH_SHORT);
            LinearLayout ll = getView().findViewById(R.id.dataLinearLayout);
            final TextDataView cview = new TextDataView(getContext());
            cview.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            /*//Debugging
            ShapeDrawable sd = new ShapeDrawable();

            // Specify the shape of ShapeDrawable
            sd.setShape(new RectShape());

            // Specify the border color of shape
            sd.getPaint().setColor(Color.RED);

            // Set the border width
            sd.getPaint().setStrokeWidth(10f);

            // Specify the style is a Stroke
            sd.getPaint().setStyle(Paint.Style.STROKE);

            // Finally, add the drawable background to TextView
            cview.setBackground(sd);*/

            cview.setDataName("Raadius");
            cview.setData("1000cm");
            cview.setId(View.generateViewId());
            idsOfDataFields.add(cview.getId());
            objects.add(cview);
            cview.d.setFocusable(editable);
            cview.dn.setFocusable(editable);

            cview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mySnackbar.setText("Editable: " + editable);
                   mySnackbar.show();
                }
            });

            cview.d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* mySnackbar.setText("Object data clicked");
                    mySnackbar.show();*/
                }
            });

            cview.dn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*mySnackbar.setText("Object name clicked");
                    mySnackbar.show();*/
                }
            });


            ll.addView(cview);
            /*mySnackbar.setText("Data object created");
            mySnackbar.show();*/

    }
}
