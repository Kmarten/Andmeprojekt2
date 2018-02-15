package com.example.kasutaja.andmeprojekt.customViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kasutaja.andmeprojekt.R;

/**
 * Created by marten on 6.02.18.
 */

public class MainListObject extends LinearLayout {
    View rootView;
    ImageView objectImg;
    ImageView objectFavourite;
    public EditText  objectName;
    public EditText  objectCategory;

    private int objectImgID = View.generateViewId();
    private int objectNameId = View.generateViewId();
    private int objectFavouriteID = View.generateViewId();
    private int objectCategoryID = View.generateViewId();
    public MainListObject(Context context) {
        super(context);
        init(context);
    }

    public MainListObject(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MainListObject(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        rootView = inflate(context, R.layout.main_list_object,this);
        this.objectImg = rootView.findViewById(R.id.ObjectImg);
        this.objectFavourite = rootView.findViewById(R.id.favourite);
        this.objectName = rootView.findViewById(R.id.ObjectName);
        this.objectCategory = rootView.findViewById(R.id.objectCategory);

        this.objectImg.setId(objectImgID);
        this.objectName.setId(objectNameId);
        this.objectFavourite.setId(objectFavouriteID);
        this.objectCategory.setId(objectCategoryID);

    }

    public View getView(){
        return this;
    }

    public String getObjectName() {
        return this.objectName.getText().toString();
    }

    public void setObjectName(String name) {
        this.objectName.setText(name);
    }

    public void setObjectImg(Bitmap img) {
        this.objectImg.setImageBitmap(img);
    }

    public void setFavourite(Bitmap favourite) {
        this.objectFavourite.setImageBitmap(favourite);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
