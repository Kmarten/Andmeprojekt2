package com.example.kasutaja.andmeprojekt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DataFragment extends Fragment {

    public static int id = 200;
    public DataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        /*FloatingActionButton mFab = (FloatingActionButton)view.findViewById(R.id.dataFloatingActionButton);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView data_name = new TextView(view.getContext());
                TextView data = new TextView(view.getContext());

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                int dp1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,30,getContext().getResources().getDisplayMetrics());
                params.setMargins(0,dp1,0,0);
                data_name.setText("Data_name");
                data_name.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                data_name.setVisibility(View.VISIBLE);
                data_name.setLayoutParams(params);
                data_name.setId(id);
                id++;
                //Id settimisega jama hetkel
                //Landscapesse pööramisel kaovad objektid ära

                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                data.setText("DATA");
                data.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
                data.setVisibility(View.VISIBLE);
                data.setLayoutParams(params);
                data.setBackgroundResource(R.color.data);
                int dp = (int)(getResources().getDisplayMetrics().density * 30 +0.5f);
                data.setPadding(dp,0,0,0);
                data_name.setId(id);
                id++;

                LinearLayout ll = (LinearLayout)getActivity().findViewById(R.id.dataLinearLayout);

                ll.addView(data_name);
                ll.addView(data);
                Snackbar mySnackbar = Snackbar.make(view, "Data object created", Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }
        }

        );*/


        return view;
    }

}
