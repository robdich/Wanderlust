package com.robdich.wanderlust.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.model.AlbumItem;

import java.util.List;

/**
 * Created by Robert on 3/5/2015.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView photo;
        public TextView title;
        public TextView author;
        public TextView likes;
        public TextView time;

        public ViewHolder(View v) {
            super(v);
            photo = (ImageView) v.findViewById(R.id.album_photo);
            title = (TextView) v.findViewById(R.id.album_title);
            author = (TextView) v.findViewById(R.id.album_author);
            likes = (TextView) v.findViewById(R.id.album_likes);
            time = (TextView) v.findViewById(R.id.album_time);
        }
    }

    private List<AlbumItem> mAlbums;

    public AlbumsAdapter(List<AlbumItem> albums){
        mAlbums = albums;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_album_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        AlbumItem album = mAlbums.get(position);

        holder.photo.setImageResource(album.photoResource);
        holder.title.setText(album.title);
        holder.author.setText("by " + album.author);
        holder.likes.setText(" " + album.likes);
        holder.time.setText(album.time);

    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

}