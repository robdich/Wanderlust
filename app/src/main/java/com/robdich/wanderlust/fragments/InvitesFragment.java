package com.robdich.wanderlust.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.adapter.InvitesAdapter;
import com.robdich.wanderlust.itemdecoration.DividerItemDecoration;
import com.robdich.wanderlust.model.InviteItem;
import com.robdich.wanderlust.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 2/11/2015.
 */
public class InvitesFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public static final InvitesFragment newInstance(){
        return  new InvitesFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recylerview_invites, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recylerView);

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
        InvitesAdapter adapter = new InvitesAdapter(getActivity(), getInvites());
        mRecyclerView.setAdapter(adapter);
    }

    private List<InviteItem> getInvites(){
        List<InviteItem> invites = new ArrayList<InviteItem>();

        InviteItem invite;

        invite = new InviteItem();
        invite.user = new UserProfile(getResources()
                .getString(R.string.username_sheldon),
                R.drawable.photo_sheldon);
        invite.photoResource = R.drawable.image_fall;
        invite.postTitle = "Island Hopping";
        invite.time = "30 min ago";
        invite.likesCount = 13;
        invites.add(invite);

        invite = new InviteItem();
        invite.user = new UserProfile(getResources()
                .getString(R.string.username_gwen),
                R.drawable.photo_gwen);
        invite.photoResource = R.drawable.image_leaf;
        invite.postTitle = "Grand Theft Autumn";
        invite.time = "16 hr ago";
        invite.likesCount = 25;
        invites.add(invite);

        invite = new InviteItem();
        invite.user = new UserProfile(getResources()
                .getString(R.string.username_daft),
                R.drawable.photo_daft);
        invite.photoResource = R.drawable.image_sail;
        invite.postTitle = "Sailing Trip";
        invite.time = "21 hr ago";
        invite.likesCount = 16;
        invites.add(invite);

        invite = new InviteItem();
        invite.user = new UserProfile(getResources()
                .getString(R.string.username_natasha),
                R.drawable.photo_natasha);
        invite.photoResource = R.drawable.image_canal;
        invite.postTitle = "Just Go With It";
        invite.time = "1 day ago";
        invite.likesCount = 27;
        invites.add(invite);

        invite = new InviteItem();
        invite.user = new UserProfile(getResources()
                .getString(R.string.username_dave),
                R.drawable.photo_dave);
        invite.photoResource = R.drawable.image_beach;
        invite.postTitle = "The Beach";
        invite.time = "2 days ago";
        invite.likesCount = 31;
        invites.add(invite);

        invite = new InviteItem();
        invite.user = new UserProfile(getResources()
                .getString(R.string.username_gwen),
                R.drawable.photo_gwen);
        invite.photoResource = R.drawable.image_beetle;
        invite.postTitle = "Drive";
        invite.time = "10 days ago";
        invite.likesCount = 54;
        invites.add(invite);

        invite = new InviteItem();
        invite.user = new UserProfile(getResources()
                .getString(R.string.username_daft),
                R.drawable.photo_daft);
        invite.photoResource = R.drawable.image_fisher;
        invite.postTitle = "Go Fishing";
        invite.time = "16 days ago";
        invite.likesCount = 42;
        invites.add(invite);

        invite = new InviteItem();
        invite.user = new UserProfile(getResources()
                .getString(R.string.username_sheldon),
                R.drawable.photo_sheldon);
        invite.photoResource = R.drawable.image_tokyo;
        invite.postTitle = "Visit Tokyo";
        invite.time = "28 days ago";
        invite.likesCount = 36;
        invites.add(invite);

        return invites;
    }

}
