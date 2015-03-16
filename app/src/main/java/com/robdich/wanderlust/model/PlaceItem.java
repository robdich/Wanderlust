package com.robdich.wanderlust.model;

/**
 * Created by Robert on 3/16/2015.
 */
public class PlaceItem {

    public int photoResource;
    public String title;
    public String time;
    public float distance;

    public PlaceItem(){
    }

    public PlaceItem(int photoResource, String title,
                     String time, float distance){
        this.photoResource = photoResource;
        this.title = title;
        this.time = time;
        this.distance = distance;
    }

}
