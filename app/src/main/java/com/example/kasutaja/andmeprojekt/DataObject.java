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
    private String name;
    private HashMap data;
    private Image img;

    private Date creationDate;

    DataObject(String name, HashMap data){
        this.name = name;
        this.data = data;
    }

}
