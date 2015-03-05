package com.robdich.wanderlust.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.model.InviteItem;
import com.robdich.wanderlust.utils.SpannableStringHelper;
import com.robdich.wanderlust.utils.StringUtils;
import com.robdich.wanderlust.view.ShapeImageView;

import java.util.List;

/**
 * Created by robert on 1/29/2015.
 */
public class InvitesAdapter extends RecyclerView.Adapter<InvitesAdapter.ViewHolder> {

    private Context mContext;
    private List<InviteItem> mInvites;

    private final int mCornerRadius;
    private static final int CORNER_RADIUS = 2;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ShapeImageView photoImage;
        public ShapeImageView avatarImage;
        public TextView detailText;
        public TextView likesText;
        public TextView timeText;

        public ViewHolder(View v) {
            super(v);
            photoImage = (ShapeImageView) v.findViewById(R.id.invite_photo);
            avatarImage = (ShapeImageView) v.findViewById(R.id.invite_avatar);
            detailText = (TextView) v.findViewById(R.id.invite_details);
            likesText = (TextView) v.findViewById(R.id.invite_likes);
            timeText = (TextView) v.findViewById(R.id.invite_time);
        }
    }

    public InvitesAdapter(Context context, List<InviteItem> invites){
        mContext = context;
        mInvites = invites;

        final float density = context.getResources().getDisplayMetrics().density;
        mCornerRadius = (int) (CORNER_RADIUS * density + 0.5f);
    }

    @Override
    public InvitesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_invite_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        InviteItem invite = mInvites.get(position);

        holder.photoImage.setImageRes(invite.photoResource)
                .setShape(ShapeImageView.Shape.ROUNDRECT)
                .setRadius(mCornerRadius, mCornerRadius);

        holder.avatarImage
                .setImageRes(invite.user.avatarResource)
                .setShape(ShapeImageView.Shape.CIRCLE);

        new SpannableStringHelper()
                .appendBold(invite.user.username)
                .appendSpace()
                .append(StringUtils.INVITE)
                .append("\n")
                .appendBold(invite.postTitle)
                .setText(holder.detailText);

        holder.timeText.setText(invite.time);
        holder.likesText.setText("" + invite.likesCount);
    }

    @Override
    public int getItemCount() {
        return mInvites.size();
    }

}
