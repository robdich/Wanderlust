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

    public InviteItem setUserProfile(UserProfile user){
        this.user = user;
        return this;
    }

    public InviteItem setPostTitle(String postTitle){
        this.postTitle = postTitle;
        return this;
    }

    public InviteItem setPhoto(int photoResource){
        this.photoResource = photoResource;
        return this;
    }

    public InviteItem setLikesCount(int likes){
        this.likesCount = likes;
        return this;
    }

    public InviteItem setTime(String time){
        this.time = time;
        return this;
    }

}
