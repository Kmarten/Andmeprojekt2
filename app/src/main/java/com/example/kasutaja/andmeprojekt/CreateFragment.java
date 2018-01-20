package com.example.kasutaja.andmeprojekt;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kasutaja.andmeprojekt.customViews.TextDataView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class CreateFragment extends Fragment implements AppCompatCallback {
    boolean editable = false;
    int index = -1;
    ArrayList<Integer> idsOfDataFields = new ArrayList<>();
    ArrayList<TextDataView> objects = new ArrayList<>(); //Workaround-vaja üle minna muule
    FloatingActionButton mButton;
    Button btSave;
    ImageView btDone, btEdit;
    View inflated;
    HashMap<String, String> objectData = new HashMap<>();
    ArrayList<DataObject> allObjects = new ArrayList<>();
    Toolbar toolbar;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        inflated = inflater.inflate(R.layout.fragment_data, container, false);
        toolbar = inflated.findViewById(R.id.toolbar);
        btDone = inflated.findViewById(R.id.done);
        btEdit = inflated.findViewById(R.id.edit);

        btSave = inflated.findViewById(R.id.btSaveObject);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectData();
                createDataObject();
                saveDataToMobile();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        mButton = inflated.findViewById(R.id.bCreate);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addField(null, null);
            }
        });
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editable = true;
                btDone.setVisibility(View.VISIBLE);
                for (int i = 0; i < objects.size(); i++) {
                    TextDataView cview = getActivity().findViewById(objects.get(i).getId());
                    cview.fieldTitle.setFocusableInTouchMode(editable);
                    cview.fieldData.setFocusableInTouchMode(editable);
                    cview.btRemoveField.setVisibility(View.VISIBLE);
                }
            }
        });
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editable = false;
                for (int i = 0; i < objects.size(); i++) {
                    TextDataView cview = (TextDataView) getActivity().findViewById(objects.get(i).getId());
                        if (cview.fieldTitle != null) cview.fieldTitle.setFocusable(editable);
                        if (cview.fieldData != null) cview.fieldData.setFocusable(editable);
                        if (cview.btRemoveField != null) cview.btRemoveField.setVisibility(View.INVISIBLE);
                }
                btDone.setVisibility(View.INVISIBLE);
            }
        });

        //Läheb vaja seda
        /*btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editable) {
                    delete(index);
                    saveDataToMobile();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }
        });
*/

        return inflated;
    }

    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
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
        HashMap<String, String> objectHashMap= dataObject.getData();
        Set<String> keys = objectHashMap.keySet();
        for(String key : keys){
            addField(key, objectHashMap.get(key));
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
            String collectedData = collectedView.getFieldData().getText().toString();
            String collectedDataName = collectedView.getFieldTitle().getText().toString();
            objectData.put(collectedDataName, collectedData);
        }
        Log.e("List", objectData.toString());
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

    protected void addField(String title, String data){
            final Snackbar mySnackbar = Snackbar.make(getView(), "Data object created", Snackbar.LENGTH_SHORT);
            final LinearLayout ll = getView().findViewById(R.id.dataLinearLayout);
            final TextDataView cview = new TextDataView(getContext());
            cview.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            if(title != null){
                cview.setDataName(title);
                cview.setData(data);
            }else {
                cview.setDataName("Raadius");
                cview.setData("1000cm");
            }
            cview.setId(View.generateViewId());
            if(editable) cview.btRemoveField.setVisibility(View.VISIBLE);
            idsOfDataFields.add(cview.getId());
            objects.add(cview);
            cview.fieldData.setFocusable(editable);
            cview.fieldTitle.setFocusable(editable);

            cview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            cview.fieldData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            cview.fieldTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            cview.btRemoveField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("TAG", "oCLIK");
                    idsOfDataFields.remove(idsOfDataFields.get(idsOfDataFields.lastIndexOf(cview.getId())));
                    objects.remove(cview);
                    ll.removeView(cview);

                }
            });
            ll.addView(cview);

    }
}
