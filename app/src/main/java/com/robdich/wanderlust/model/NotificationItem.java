package com.robdich.wanderlust.model;

import java.util.List;

/**
 * Created by robert on 1/30/2015.
 */
public class NotificationItem {

    public enum NotificationType{
        Comment("commented on"),
        Add("added"),
        Publish("published");

        private String notificationTypeString;

        NotificationType(String notificationTypeString){
            this.notificationTypeString = notificationTypeString;
        }

        @Override
        public String toString() {
            return notificationTypeString;
        }
    }

    public UserProfile user;
    public NotificationType notificationType;
    public int likesCount;
    public String time;

    public String postTitle;
    public String comment;
    public int photoResource = -1;

    public UserProfile addedFriend;

    public List<Integer> publishedPhotos;

    public NotificationItem(){ }

    public NotificationItem(UserProfile user, int likesCount,
                            String time, NotificationType notificationType){
        this.user = user;
        this.notificationType = notificationType;
        this.likesCount = likesCount;
        this.time = time;
    }

    public void setCommentPost(String postTitle, String comment, int photoResource){
        this.postTitle = postTitle;
        this.comment = comment;
        this.photoResource = photoResource;
        this.notificationType = NotificationType.Comment;
    }

    public void setAddedFriend(UserProfile user){
        this.addedFriend = user;
        this.notificationType = NotificationType.Add;
    }

    public void setAlbumPost(String postTitle, List<Integer> publishedPhotos){
        this.postTitle = postTitle;
        this.publishedPhotos = publishedPhotos;
        this.notificationType = NotificationType.Publish;
    }



}
