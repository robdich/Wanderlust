package com.robdich.wanderlust.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.model.NotificationItem;
import com.robdich.wanderlust.model.NotificationItem.NotificationType;
import com.robdich.wanderlust.utils.SpannableStringHelper;
import com.robdich.wanderlust.utils.StringUtils;
import com.robdich.wanderlust.view.ShapeImageView;

import java.util.List;

/**
 * Created by robert on 1/29/2015.
 */
public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private Context mContext;
    private List<NotificationItem> mNotificationItemList;

    private LinearLayout.LayoutParams mLinearLayoutParams;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ShapeImageView avatarImage;
        public ShapeImageView photoImage;
        public TextView detailText;
        public ViewGroup containerView;
        public TextView likesText;
        public TextView timeText;

        public ViewHolder(View v) {
            super(v);
            avatarImage = (ShapeImageView) v.findViewById(R.id.notification_avatar);
            photoImage = (ShapeImageView) v.findViewById(R.id.notification_image);
            detailText = (TextView) v.findViewById(R.id.notification_details);
            likesText = (TextView) v.findViewById(R.id.notification_likes);
            timeText = (TextView) v.findViewById(R.id.notification_time);
            containerView = (ViewGroup) v.findViewById(R.id.notification_container);
        }

    }

    public NotificationsAdapter(Context context, List<NotificationItem> notificationItems){
        mContext = context;
        mNotificationItemList = notificationItems;

        setLinearLayoutParams();
    }

    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_notifications_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int visibility = View.GONE;
        NotificationItem notification = mNotificationItemList.get(position);

        holder.avatarImage
                .setImageRes(notification.user.avatarResource)
                .setShape(ShapeImageView.Shape.CIRCLE);;

        SpannableStringHelper helper = new SpannableStringHelper()
                .appendBold(notification.user.username)
                .appendSpace()
                .appendColored(notification.notificationType.toString()
                        , mContext.getResources().getColor(R.color.text_primary))
                .appendSpace();

        if(notification.notificationType == NotificationType.Comment){

            helper.appendBold(notification.postTitle)
                    .appendSpace()
                    .appendColored(notification.comment
                            , mContext.getResources().getColor(R.color.text_light_gray))
                    .setText(holder.detailText);

            holder.photoImage.setImageResource(notification.photoResource);
            holder.photoImage.setVisibility(View.VISIBLE);

        }else if (notification.notificationType == NotificationType.Add){

            helper.appendBold(notification.addedFriend.username)
                    .appendSpace()
                    .append(StringUtils.TO)
                    .appendSpace()
                    .append(StringUtils.getPronoun(notification.user.gender))
                    .appendSpace()
                    .append(StringUtils.FRIEND_LIST)
                    .setText(holder.detailText);

            holder.photoImage
                    .setImageRes(notification.addedFriend.avatarResource)
                    .setShape(ShapeImageView.Shape.CIRCLE);;
            holder.photoImage.setVisibility(View.VISIBLE);

        }else if (notification.notificationType == NotificationType.Publish){

            int count = notification.publishedPhotos.size();
            helper.append(getPhotosString(count))
                    .appendSpace()
                    .append(StringUtils.getPronoun(notification.user.gender))
                    .appendSpace()
                    .appendBold(notification.postTitle)
                    .appendSpace()
                    .append(StringUtils.ALBUM)
                    .setText(holder.detailText);

            holder.photoImage.setVisibility(View.GONE);

            holder.containerView.removeAllViews();
            for(int drawableRes : notification.publishedPhotos){
                View photo = getPhotoView(drawableRes);
                holder.containerView.addView(photo);
            }
            visibility = View.VISIBLE;
        }

        holder.containerView.setVisibility(visibility);

        visibility = View.GONE;
        if(notification.likesCount > 0){
            holder.likesText.setText("" + notification.likesCount);
            visibility = View.VISIBLE;
        }
        holder.likesText.setVisibility(visibility);

        holder.timeText.setText(notification.time);
    }

    @Override
    public int getItemCount() {
        return mNotificationItemList.size();
    }

    private ImageView getPhotoView(int drawableRes){
        ImageView photo = new ImageView(mContext);
        photo.setImageResource(drawableRes);
        photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
        photo.setLayoutParams(mLinearLayoutParams);
        return photo;
    }

    private void setLinearLayoutParams(){
        int size = (int) mContext.getResources().getDimension(R.dimen.notification_photo_size);
        mLinearLayoutParams = new LinearLayout.LayoutParams((int) size, (int) size);
        mLinearLayoutParams.setMargins(0, 0, (int) mContext.getResources()
                .getDimension(R.dimen.notification_photo_right_margin), 0);
    }

    private String getPhotosString(int count){
        return String.format("%d new photo%s on",
                count, count > 1 ? "s" : "");
    }

}
