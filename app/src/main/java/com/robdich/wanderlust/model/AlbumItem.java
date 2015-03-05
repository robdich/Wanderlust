package com.robdich.wanderlust.model;

/**
 * Created by Robert on 3/5/2015.
 */
public class AlbumItem {

    public int photoResource;
    public String title;
    public String author;
    public String time;
    public int likes;

    public AlbumItem(){
    }

    public AlbumItem(int photoResource, String title,
                     String author, int likes, String time){
        this.photoResource = photoResource;
        this.title = title;
        this.author = author;
        this.likes = likes;
        this.time = time;
    }

}
