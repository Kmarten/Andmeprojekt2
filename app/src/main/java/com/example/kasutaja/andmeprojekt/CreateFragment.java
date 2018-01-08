package com.example.kasutaja.andmeprojekt;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.kasutaja.andmeprojekt.customViews.TextDataView;

import java.util.ArrayList;


public class CreateFragment extends Fragment {

    int arv;
    ArrayList<Integer> idsOfDataFields;
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

    protected void collectData(){
        for(int  i = 0; i<idsOfDataFields.size(); i++){
            //(asfkdhnjkö
        }
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


            cview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mySnackbar.setText("Object clicked: " + cview.getId());
                   mySnackbar.show();
                }
            });

            cview.d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mySnackbar.setText("Object data clicked");
                    mySnackbar.show();
                }
            });

            cview.dn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mySnackbar.setText("Object name clicked");
                    mySnackbar.show();
                }
            });

            ll.addView(cview);
            /*mySnackbar.setText("Data object created");
            mySnackbar.show();*/

    }
}
