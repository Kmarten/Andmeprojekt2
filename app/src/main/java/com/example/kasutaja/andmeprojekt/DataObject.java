package com.example.kasutaja.andmeprojekt;

import android.media.Image;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Ahto on 18.12.2017.
 */

//Aeg, millal objeti muudeti
public class DataObject {
    private static int id = 0; //Objects counter
    private int objectId;
    private String name;
    private HashMap data;
    private Image img;

    private Date creationDate;

    DataObject(String name, HashMap data){
        this.name = name;
        this.data = data;
        objectId = id;
        id++;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
