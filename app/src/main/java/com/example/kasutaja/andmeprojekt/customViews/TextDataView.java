package com.example.kasutaja.andmeprojekt.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kasutaja.andmeprojekt.R;

/**
 * Created by Märten on 05.01.2018.
 * Custom view fragmenti datavälja ja selle nime hoidmiseks
 */

public class TextDataView extends LinearLayout {
    private View rootView;
    private TextView dn; //data_name
    private TextView d; //data

    LinearLayout ll = findViewById(R.id.dataLinearLayout);;
    //private String data_name = "Data_name";
    //private String data = "Data";

    private int data_name_id = View.generateViewId();
    private int data__id = View.generateViewId();

    public TextDataView(Context context) {
        super(context);
        init(context);
    }

    public TextDataView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextDataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        rootView = inflate(context,R.layout.view_text_data,this);
        dn = (TextView)rootView.findViewById(R.id.data_name);
        d = (TextView)rootView.findViewById(R.id.data);

        dn.setId(data_name_id);
        d.setId(data__id);

        /*dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //work in progress
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //work in progress
            }
        });*/
    }

    public void setDataName(String name) {
        dn.setText(name);
        invalidate();
        requestLayout();
    }
    public void setData(String data) {
        d.setText(data);
        invalidate();
        requestLayout();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*ll.layout(0,0,canvas.getWidth(),canvas.getHeight());
        ll.addView(dn);
        ll.addView(d);*/
    }



}
