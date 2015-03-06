package com.robdich.wanderlust.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.adapter.AlbumsAdapter;
import com.robdich.wanderlust.model.AlbumItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 3/5/2015.
 */
public class AlbumsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    private static final int[] FOUR_COLUMNS_PATTERN = new int[]{
            2, 1, 1,
            1, 2, 1,
            1, 1, 2,
            1, 2, 1
    };

    private static final String[] TITLES = new String[]{
            "Parts Unknown", "Strawberry Fields", "Local Tourist Destinations",
            "Summer Getaway", "Trips Abroad", "Gone Fishing",
            "Greatest Cities", "Around The World", "Surfing Capital"
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recylerview_albums, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recylerView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int columns = getResources().getInteger(R.integer.albums_grid_columns);
        final int length = FOUR_COLUMNS_PATTERN.length;
        mLayoutManager = new GridLayoutManager(getActivity(), columns);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                switch (columns){
                    case 2:
                        //Produces 1,1,2,1,1,2...
                        return (position % 3 == 2 ? 2 : 1);
                    case 3:
                        //Produces 1,2,3,1,2,3...
                        return (position % 3 + 1);
                    case 4:
                        //Produces a zigzag pattern
                        return FOUR_COLUMNS_PATTERN[position % length];
                }

                return 1;
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        AlbumsAdapter adapter = new AlbumsAdapter(getAlbums());
        mRecyclerView.setAdapter(adapter);
    }

    private List<AlbumItem> getAlbums() {
        List<AlbumItem> albums = new ArrayList<AlbumItem>();

        AlbumItem album;

        album = new AlbumItem();
        album.photoResource = R.drawable.image_fall;
        album.title = TITLES[0];
        album.author = getResources().getString(R.string.username_sheldon);
        album.likes = 6;
        album.time = "15 min ago";
        albums.add(album);

        album = new AlbumItem();
        album.photoResource = R.drawable.image_leaf;
        album.title = TITLES[1];
        album.author = getResources().getString(R.string.username_dave);
        album.likes = 14;
        album.time = "1 hr ago";
        albums.add(album);

        album = new AlbumItem();
        album.photoResource = R.drawable.image_boats;
        album.title = TITLES[2];
        album.author = getResources().getString(R.string.username_natasha);
        album.likes = 21;
        album.time = "3 hr ago";
        albums.add(album);

        album = new AlbumItem();
        album.photoResource = R.drawable.image_sail;
        album.title = TITLES[3];
        album.author = getResources().getString(R.string.username_daft);
        album.likes = 16;
        album.time = "8 hr ago";
        albums.add(album);

        album = new AlbumItem();
        album.photoResource = R.drawable.image_greece;
        album.title = TITLES[4];
        album.author = getResources().getString(R.string.username_gwen);
        album.likes = 27;
        album.time = "1 day ago";
        albums.add(album);

        album = new AlbumItem();
        album.photoResource = R.drawable.image_fisher;
        album.title = TITLES[5];
        album.author = getResources().getString(R.string.username_dave);
        album.likes = 12;
        album.time = "2 days ago";
        albums.add(album);

        album = new AlbumItem();
        album.photoResource = R.drawable.image_tokyo;
        album.title = TITLES[6];
        album.author = getResources().getString(R.string.username_sheldon);
        album.likes = 32;
        album.time = "5 days ago";
        albums.add(album);

        album = new AlbumItem();
        album.photoResource = R.drawable.image_canal;
        album.title = TITLES[7];
        album.author = getResources().getString(R.string.username_daft);
        album.likes = 51;
        album.time = "14 days ago";
        albums.add(album);

        album = new AlbumItem();
        album.photoResource = R.drawable.image_beach;
        album.title = TITLES[8];
        album.author = getResources().getString(R.string.username_natasha);
        album.likes = 25;
        album.time = "22 days ago";
        albums.add(album);

        return albums;
    }

}
