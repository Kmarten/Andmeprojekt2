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
        View view = inflater.inflate(R.layout.fragment_data, container, false);
    /*    // Inflate the layout for this fragment

        mButton = (FloatingActionButton)view.findViewById(R.id.bCreate);
        eButton = (FloatingActionButton)view.findViewById(R.id.bEdit);
        dButton = (FloatingActionButton)view.findViewById(R.id.bDelete);
        FabOpen = AnimationUtils.loadAnimation(getContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        RotateClockwise = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_clockwise);
        RotateAntiClockwise = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_anticlockwise);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar mySnackbar = Snackbar.make(getView(), "mButton", Snackbar.LENGTH_SHORT);
                mySnackbar.show();
                Log.d("mButton","mButton clicked");
                if(isOpen) {
                    dButton.startAnimation(FabClose);
                    eButton.startAnimation(FabClose);
                    mButton.startAnimation(RotateAntiClockwise);
                    dButton.setClickable(false);
                    eButton.setClickable(false);
                    isOpen = false;

                }
                else {
                    dButton.startAnimation(FabOpen);
                    eButton.startAnimation(FabOpen);
                    mButton.startAnimation(RotateClockwise);
                    dButton.setClickable(true);
                    eButton.setClickable(true);
                    isOpen = true;
                }
            }
        }

        );
*/

        return view;
    }

}
