package com.example.kasutaja.andmeprojekt;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kasutaja.andmeprojekt.customViews.TextDataView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.FileNotFoundException;
import java.io.InputStream;
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
    ImageView btDone, btEdit, ivObjectImage;
    EditText objectName;
    View inflated;
    HashMap<String, String> objectData = new HashMap<>();
    ArrayList<DataObject> allObjects = new ArrayList<>();
    Toolbar toolbar;
    Integer SELECT_FILE = 0;
    Uri imageUri;
    String name;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        inflated = inflater.inflate(R.layout.fragment_data, container, false);
        toolbar = inflated.findViewById(R.id.toolbar);
        btDone = inflated.findViewById(R.id.done);
        btEdit = inflated.findViewById(R.id.edit);
        objectName = inflated.findViewById(R.id.ObjectName);
        btSave = inflated.findViewById(R.id.btSaveObject);
        ivObjectImage = inflated.findViewById(R.id.ObjectImg);

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
                getFields();
            }
        });
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editable = true;
                objectName.setFocusableInTouchMode(true);
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
                objectName.setFocusable(false);
                for (int i = 0; i < objects.size(); i++) {
                    TextDataView cview = (TextDataView) getActivity().findViewById(objects.get(i).getId());
                        if (cview.fieldTitle != null) cview.fieldTitle.setFocusable(editable);
                        if (cview.fieldData != null) cview.fieldData.setFocusable(editable);
                        if (cview.btRemoveField != null) cview.btRemoveField.setVisibility(View.INVISIBLE);
                }
                btDone.setVisibility(View.INVISIBLE);
            }
        });



        ivObjectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editable) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    } else {
                        selectImage();
                    }
                }
            }
        });
        return inflated;
    }


    private void selectImage() {
        final CharSequence[] items = {"Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Gallery")){
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_FILE);
                }
                if(items[i].equals("Cancle")){
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){

            if(requestCode == SELECT_FILE){
                try {
                    imageUri = data.getData();
                    Glide.with(this).load(imageUri).into(ivObjectImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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
        if(dataObject.getImg() != null) {
            Glide.with(this).load(dataObject.getImg()).into(ivObjectImage);
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

        name = objectName.getText().toString();

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
            if(imageUri != null) allObjects.get(index).setImg(imageUri.toString());
            allObjects.get(index).setName(name);
        }else {
            DataObject newObject;
            if(imageUri != null) {
                newObject = new DataObject(name, objectData, imageUri.toString());
            }else{
                newObject = new DataObject(name, objectData, null);
            }
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

    protected void getFields(){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dataobject_enter_fields,null);
        final EditText enterName = dialogView.findViewById(R.id.enter_data_name);
        final EditText enterData = dialogView.findViewById(R.id.enter_data);

        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Fields");
        dialogBuilder.setMessage("Enter data below:");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(enterName.getText().toString().trim().length() != 0 && enterData.getText().toString().trim().length() != 0 ) addField(enterName.getText().toString(),enterData.getText().toString());
                }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();


    }
    protected void addField(final String title, String data){
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
                cview.setDataName("Default name");
                cview.setData("Default data");
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
