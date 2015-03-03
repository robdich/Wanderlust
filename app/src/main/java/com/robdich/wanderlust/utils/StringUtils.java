package com.robdich.wanderlust.utils;

import com.robdich.wanderlust.model.UserProfile;

/**
 * Created by robert on 2/5/2015.
 */
public class StringUtils {

    public static final String TO = "to";
    public static final String PRONOUN_MALE = "his";
    public static final String PRONOUN_FEMALE = "her";
    public static final String FRIEND_LIST = "friend list";
    public static final String ALBUM = "photo album";

    public static final String INVITE = "invited you to";

    public static String getPronoun(UserProfile.Gender gender){
        if (gender == UserProfile.Gender.Male) {
            return PRONOUN_MALE;
        }
        else {
            return PRONOUN_FEMALE;
        }
    }

}
