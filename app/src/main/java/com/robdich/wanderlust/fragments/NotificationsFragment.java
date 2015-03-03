package com.robdich.wanderlust.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robdich.hideabletoolbar.scrollobserver.IScrollObserver;
import com.robdich.hideabletoolbar.view.ObserveableRecyclerView;
import com.robdich.wanderlust.R;
import com.robdich.wanderlust.adapter.NotificationsAdapter;
import com.robdich.wanderlust.itemdecoration.DividerItemDecoration;
import com.robdich.wanderlust.model.NotificationItem;
import com.robdich.wanderlust.model.UserProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by robert on 2/2/2015.
 */
public class NotificationsFragment extends Fragment{

    private ObserveableRecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NotificationsAdapter mNotificationsAdapter;

    private IScrollObserver mScrollObserver;

    public static final NotificationsFragment newInstance(){
        return  new NotificationsFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater
                .inflate(R.layout.recylerview_notifications, container, false);
        mRecyclerView = (ObserveableRecyclerView) rootView.findViewById(R.id.recylerView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        mNotificationsAdapter = new NotificationsAdapter(getActivity(), getNotificationList());
        mRecyclerView.setAdapter(mNotificationsAdapter);

        if (mScrollObserver != null && mRecyclerView != null){
            mScrollObserver.observeScrollable(mRecyclerView);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mScrollObserver = (IScrollObserver) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement IScrollObserver");
        }
    }

    private List<NotificationItem> getNotificationList(){

        List<NotificationItem> notificationItems = new ArrayList<NotificationItem>();
        NotificationItem notification;

        notification = new NotificationItem();
        notification.user = new UserProfile(getResources().getString(R.string.username_dave),
                UserProfile.Gender.Male,
                R.drawable.photo_dave);
        notification.likesCount = 4;
        notification.time = "4 min ago";
        notification.setCommentPost("The Greatest Escape. Ever.",
                "That is indeed the greatest. Ever.",
                R.drawable.image_boats);
        notificationItems.add(notification);

        notification = new NotificationItem();
        notification.user = new UserProfile(getResources().getString(R.string.username_gwen),
                UserProfile.Gender.Female,
                R.drawable.photo_gwen);
        notification.likesCount = 10;
        notification.time = "15 min ago";
        notification.setCommentPost("The Best Season",
                "I love the color of Autumn",
                R.drawable.image_leaf);
        notificationItems.add(notification);

        notification = new NotificationItem();
        notification.user = new UserProfile(getResources().getString(R.string.username_gwen),
                UserProfile.Gender.Female,
                R.drawable.photo_gwen);
        notification.likesCount = 0;
        notification.time = "15 min ago";
        notification.setAddedFriend(
                new UserProfile(
                        getResources().getString(R.string.username_natasha),
                        UserProfile.Gender.Female,
                        R.drawable.photo_natasha));
        notificationItems.add(notification);

        notification = new NotificationItem();
        notification.user = new UserProfile(getResources().getString(R.string.username_sheldon),
                UserProfile.Gender.Male,
                R.drawable.photo_sheldon);
        notification.likesCount = 16;
        notification.time = "2 hr ago";
        notification.setCommentPost("Getaway",
                "Good times... For a change...",
                R.drawable.image_fall);
        notificationItems.add(notification);

        notification = new NotificationItem();
        notification.user = new UserProfile(getResources().getString(R.string.username_gwen),
                UserProfile.Gender.Female,
                R.drawable.photo_gwen);
        notification.likesCount = 31;
        notification.time = "6 hr ago";
        notification.setAlbumPost("Travel",
                Arrays.asList(R.drawable.image_canal,
                        R.drawable.image_hermit,
                        R.drawable.image_leaf,
                        R.drawable.image_fisher,
                        R.drawable.image_boats));
        notificationItems.add(notification);

        notification = new NotificationItem();
        notification.user = new UserProfile(getResources().getString(R.string.username_sheldon),
                UserProfile.Gender.Male,
                R.drawable.photo_sheldon);
        notification.likesCount = 0;
        notification.time = "12 hr ago";
        notification.setAddedFriend(
                new UserProfile(
                        getResources().getString(R.string.username_gwen),
                        UserProfile.Gender.Female,
                        R.drawable.photo_gwen));
        notificationItems.add(notification);

        return notificationItems;
    }

}
