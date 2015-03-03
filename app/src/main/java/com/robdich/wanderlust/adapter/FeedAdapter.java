package com.robdich.wanderlust.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.model.FeedItem;
import com.robdich.wanderlust.view.ShapeImageView;

import java.util.List;

/**
 * Created by robert on 1/29/2015.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private  List<FeedItem> mFeedItemList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ShapeImageView avatarImage;
        public ImageView photoImage;
        public TextView nameText;
        public TextView statusText;
        public TextView likesText;
        public TextView locationText;
        public TextView timeText;

        public ViewHolder(View v) {
            super(v);
            avatarImage = (ShapeImageView) v.findViewById(R.id.feed_item_avatar);
            photoImage = (ImageView) v.findViewById(R.id.feed_item_photo);
            nameText = (TextView) v.findViewById(R.id.feed_item_username);
            statusText = (TextView) v.findViewById(R.id.feed_item_status);
            likesText = (TextView) v.findViewById(R.id.feed_item_header_text_likes);
            locationText = (TextView) v.findViewById(R.id.feed_item_header_text_location);
            timeText = (TextView) v.findViewById(R.id.feed_item_header_text_time);
        }

    }

    public FeedAdapter(List<FeedItem> feedItemList){
        mFeedItemList = feedItemList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_feed_item_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FeedItem feedItem = mFeedItemList.get(position);
        holder.avatarImage
                .setImageRes(feedItem.user.avatarResource)
                .setShape(ShapeImageView.Shape.CIRCLE);;
        holder.photoImage.setImageResource(feedItem.photoResource);
        holder.nameText.setText(feedItem.user.username);
        holder.statusText.setText(feedItem.status);
        holder.likesText.setText("" + feedItem.likesCount);
        holder.locationText.setText(feedItem.location);
        holder.timeText.setText(feedItem.time);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFeedItemList.size();
    }
}
