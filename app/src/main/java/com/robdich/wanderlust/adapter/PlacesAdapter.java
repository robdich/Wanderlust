package com.robdich.wanderlust.adapter;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.model.PlaceItem;

import java.util.List;

/**
 * Created by Robert on 3/16/2015.
 */
public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView photo;
        public TextView title;
        public TextView time;
        public TextView distance;
        public Button button;

        public ViewHolder(View v) {
            super(v);
            photo = (ImageView) v.findViewById(R.id.place_photo);
            title = (TextView) v.findViewById(R.id.place_title);
            time = (TextView) v.findViewById(R.id.place_time);
            distance = (TextView) v.findViewById(R.id.place_distance);
            button = (Button) v.findViewById(R.id.place_go_button);
        }
    }

    private List<PlaceItem> mPlaces;

    private static int BUTTON_COLOR_FILTER = 0xFF43BE42;

    public PlacesAdapter(List<PlaceItem> places){
        mPlaces = places;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_place_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlaceItem place = mPlaces.get(position);

        holder.photo.setImageResource(place.photoResource);
        holder.title.setText(place.title);
        holder.distance.setText(place.distance + " miles");
        holder.time.setText(place.time);
        holder.button.getBackground().setColorFilter(BUTTON_COLOR_FILTER,
                PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

}
