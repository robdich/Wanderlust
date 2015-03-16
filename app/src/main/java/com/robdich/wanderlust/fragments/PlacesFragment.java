package com.robdich.wanderlust.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.adapter.PlacesAdapter;
import com.robdich.wanderlust.model.PlaceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 3/16/2015.
 */
public class PlacesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    private static final String[] TITLES = new String[]{
            "Hermit Crab Race",
            "Vintage Car Show",
            "Manila Bay",
            "Little Tokyo",
            "El Nido Beach"
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

        final int columns = getResources().getInteger(R.integer.places_grid_columns);
        mLayoutManager = new GridLayoutManager(getActivity(), columns);
        mRecyclerView.setLayoutManager(mLayoutManager);
        PlacesAdapter adapter = new PlacesAdapter(getPlaces());
        mRecyclerView.setAdapter(adapter);
    }

    private List<PlaceItem> getPlaces(){
        List<PlaceItem> places = new ArrayList<>();
        PlaceItem place;

        place = new PlaceItem();
        place.photoResource = R.drawable.image_hermit;
        place.title = TITLES[0];
        place.time = "16 minutes";
        place.distance = 3.5f;
        places.add(place);

        place = new PlaceItem();
        place.photoResource = R.drawable.image_beetle;
        place.title = TITLES[1];
        place.time = "35 minutes";
        place.distance = 5.3f;
        places.add(place);

        place = new PlaceItem();
        place.photoResource = R.drawable.image_boats;
        place.title = TITLES[2];
        place.time = "43 minutes";
        place.distance = 7.9f;
        places.add(place);

        place = new PlaceItem();
        place.photoResource = R.drawable.image_tokyo;
        place.title = TITLES[3];
        place.time = "68 minutes";
        place.distance = 10.0f;
        places.add(place);

        place = new PlaceItem();
        place.photoResource = R.drawable.image_beach;
        place.title = TITLES[4];
        place.time = "92 minutes";
        place.distance = 15.7f;
        places.add(place);

        return places;
    }



}
