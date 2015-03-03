package com.robdich.wanderlust.model;

/**
 * Created by robert on 1/30/2015.
 */
public class FeedItem {

    public UserProfile user;
    public String status;
    public int photoResource;
    public int likesCount;
    public String location;
    public String time;

    public FeedItem(){ }

    public FeedItem(UserProfile user, String status, int photoResource,
                    int likesCount, String location, String time){
        this.user = user;
        this.status = status;
        this.photoResource = photoResource;
        this.likesCount = likesCount;
        this.location = location;
        this.time = time;
    }

}
