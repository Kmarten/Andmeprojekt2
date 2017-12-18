package com.example.kasutaja.andmeprojekt;

import android.media.Image;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Kasutaja on 18.12.2017.
 */

public class DataObjects {
    private static int id = 0; //Objects counter
    private String name;
    private HashMap data;
    private Image img;

    private Date creationDate;

    DataObjects(String name, HashMap data, Image img){

    }

}
