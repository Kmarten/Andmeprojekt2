package com.example.kasutaja.andmeprojekt;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class CreateFragment extends Fragment {

    int arv;
    ArrayList<Long> ids;
    FloatingActionButton create;
    View inflated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        inflated = inflater.inflate(R.layout.fragment_data, container, false);

        create = inflated.findViewById(R.id.bCreate);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //collectData(); not existing yet
                //saveData(); not existing yet
                addField();
                //startActivity(new Intent(getActivity(), MainActivity.class).putExtra("arv", arv+1));
            }
        });

        return inflated;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        arv = getActivity().getIntent().getIntExtra("arv", 0);


    }

    protected void addField(){
        EditText et = new EditText(getActivity());
        LinearLayout ll = getView().findViewById(R.id.dataLinearLayout);
        et.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        et.setText("Mina olen uus");
        et.setBackgroundColor(Color.RED);
        ll.addView(et);
    }
}
