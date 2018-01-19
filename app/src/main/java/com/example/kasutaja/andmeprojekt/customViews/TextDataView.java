package com.example.kasutaja.andmeprojekt.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kasutaja.andmeprojekt.R;

/**
 * Created by Märten on 05.01.2018.
 * Custom view fragmenti datavälja ja selle nime hoidmiseks
 */

public class TextDataView extends LinearLayout {
    View rootView;
    public EditText fieldTitle; //data_name
    public EditText fieldData; //data
    public Button btRemoveField;

    private int fieldTitleID = View.generateViewId();
    private int fieldDataID = View.generateViewId();
    private int btRemoveFieldID = View.generateViewId();

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

    public View getView(){
        return this;
    }

    private void init(Context context) {

        rootView = inflate(context,R.layout.view_text_data,this);
        fieldTitle = rootView.findViewById(R.id.data_name);
        fieldData = rootView.findViewById(R.id.data);
        btRemoveField = rootView.findViewById(R.id.del_bt);

        fieldTitle.setId(fieldTitleID);
        fieldData.setId(fieldDataID);
        btRemoveField.setId(btRemoveFieldID);
    }

    public void setDataName(String name) {
        fieldTitle.setText(name);
        invalidate();
        requestLayout();
    }

    public void setData(String data) {
        fieldData.setText(data);
        invalidate();
        requestLayout();

    }

    public TextView getFieldTitle() {
        return fieldTitle;
    }

    public TextView getFieldData() {
        return fieldData;
    }



    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }



}
