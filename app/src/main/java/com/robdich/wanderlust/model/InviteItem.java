package com.robdich.wanderlust.model;

/**
 * Created by robert on 1/30/2015.
 */
public class InviteItem {

    public UserProfile user;
    public int photoResource;
    public String postTitle;
    public int likesCount;
    public String time;

    public InviteItem(){ }

    public InviteItem(UserProfile user, String postTitle, int photoResource,
                      int likesCount, String time){
        this.user = user;
        this.postTitle = postTitle;
        this.photoResource = photoResource;
        this.likesCount = likesCount;
        this.time = time;
    }

}
