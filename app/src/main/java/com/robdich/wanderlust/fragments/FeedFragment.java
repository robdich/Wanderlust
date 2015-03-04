package com.robdich.wanderlust.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robdich.hideabletoolbar.scrollobserver.IScrollObserver;
import com.robdich.hideabletoolbar.view.ObserveableRecyclerView;
import com.robdich.wanderlust.R;
import com.robdich.wanderlust.adapter.FeedAdapter;
import com.robdich.wanderlust.model.FeedItem;
import com.robdich.wanderlust.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 2/2/2015.
 */
public class FeedFragment extends Fragment{

    private ObserveableRecyclerView mRrecyclerView;
    private GridLayoutManager mLayoutManager;
    private FeedAdapter mFeedAdapter;

    private IScrollObserver mScrollObserver;

    public static final FeedFragment newInstance(){
        return new FeedFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recylerview_feed, container, false);
        mRrecyclerView = (ObserveableRecyclerView) rootView.findViewById(R.id.recylerView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //        recyclerView.setHasFixedSize(true);

        int columns = getActivity().getResources().getInteger(R.integer.feed_grid_columns);

        mLayoutManager = new GridLayoutManager(getActivity(), columns);
        mRrecyclerView.setLayoutManager(mLayoutManager);
        mFeedAdapter = new FeedAdapter(getFeedItemList());
        mRrecyclerView.setAdapter(mFeedAdapter);

        if (mScrollObserver != null && mRrecyclerView != null){
            mScrollObserver.observeScrollable(mRrecyclerView);
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

    private List<FeedItem> getFeedItemList(){
        List<FeedItem> feedItems = new ArrayList<FeedItem>();
        String[] statuses = getResources().getStringArray(R.array.statuses);

        FeedItem feedItem;

        feedItem = new FeedItem();
        feedItem.user = new UserProfile(
                getResources().getString(R.string.username_dave),
                UserProfile.Gender.Male,
                R.drawable.photo_dave);
        feedItem.status = statuses[0];
        feedItem.photoResource = R.drawable.image_sail;
        feedItem.likesCount = 21;
        feedItem.location = "Seattle, WA";
        feedItem.time = "30 min ago";
        feedItems.add(feedItem);

        feedItem = new FeedItem();
        feedItem.user = new UserProfile(
                getResources().getString(R.string.username_sheldon),
                UserProfile.Gender.Male,
                R.drawable.photo_sheldon);
        feedItem.status = statuses[1];
        feedItem.photoResource = R.drawable.image_tokyo;
        feedItem.likesCount = 16;
        feedItem.location = "Austin, TX";
        feedItem.time = "3 hr ago";
        feedItems.add(feedItem);

        feedItem = new FeedItem();
        feedItem.user = new UserProfile(
                getResources().getString(R.string.username_natasha),
                UserProfile.Gender.Female,
                R.drawable.photo_natasha);
        feedItem.status = statuses[2];
        feedItem.photoResource = R.drawable.image_beach;
        feedItem.likesCount = 34;
        feedItem.location = "Los Angeles, CA";
        feedItem.time = "12 hr ago";
        feedItems.add(feedItem);

        feedItem = new FeedItem();
        feedItem.user = new UserProfile(
                getResources().getString(R.string.username_daft),
                UserProfile.Gender.Male,
                R.drawable.photo_daft);
        feedItem.status = statuses[3];
        feedItem.photoResource = R.drawable.image_beetle;
        feedItem.likesCount = 41;
        feedItem.location = "Paris, FR";
        feedItem.time = "19 hr ago";
        feedItems.add(feedItem);

        feedItem = new FeedItem();
        feedItem.user = new UserProfile(
                getResources().getString(R.string.username_gwen),
                UserProfile.Gender.Female,
                R.drawable.photo_gwen);
        feedItem.status = statuses[4];
        feedItem.photoResource = R.drawable.image_greece;
        feedItem.likesCount = 46;
        feedItem.location = "New York, NY";
        feedItem.time = "1 day ago";
        feedItems.add(feedItem);

        return feedItems;
    }

}
