package com.robdich.wanderlust.model;

/**
 * Created by robert on 2/3/2015.
 */
public class UserProfile {

    public enum Gender{
        Male, Female
    }

    public String username;
    public Gender gender;
    public int avatarResource;

    public UserProfile() { }

    public UserProfile(String username, int avatarResource){
        this(username, null, avatarResource);
    }

    public UserProfile(String username, Gender gender, int avatarResource){
        this.username = username;
        this.gender = gender;
        this.avatarResource = avatarResource;
    }

}
