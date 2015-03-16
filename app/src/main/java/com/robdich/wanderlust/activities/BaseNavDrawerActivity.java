package com.robdich.wanderlust.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robdich.hideabletoolbar.HideableToolbarActivity;
import com.robdich.wanderlust.R;
import com.robdich.wanderlust.view.ShapeImageView;

import java.util.ArrayList;
import java.util.List;


public class BaseNavDrawerActivity extends HideableToolbarActivity {

	private List<Integer> mDrawerItems = new ArrayList<Integer>();
	private ViewGroup mDrawerItemContainer;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	protected static final int DRAWER_ITEM_ACTIVITY = 0;
	protected static final int DRAWER_ITEM_INVITES = 1;
	protected static final int DRAWER_ITEM_PLACES = 2;
	protected static final int DRAWER_ITEM_ALBUMS = 3;
	protected static final int DRAWER_ITEM_SETTINGS = 4;
	protected static final int DRAWER_SEPARATOR = -1;
	protected static final int DRAWER_ITEM_INVALID = -2;
	
	private static final int NO_ICON = -1;
	
	private static final int[] DRAWER_ITEM_ICON_RES = new int[]{
		R.drawable.ic_activity,
		R.drawable.ic_invite,
		R.drawable.ic_places,
		R.drawable.ic_album,

		R.drawable.ic_settings
	};
	
	private static final String[] DRAWER_ITEM_TITLE = new String[]{
		"Activity", "Invites", "Places", "Albums", "Settings"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar_actionbar;
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
    }
	
	private void setupNavDrawer(){
		
		final CharSequence title = getActivityTitle();
		final CharSequence drawerTitle = getString(R.string.app_name);
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				mToolbar, R.string.drawer_open, R.string.drawer_close){
			
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getSupportActionBar().setTitle(title);
				invalidateOptionsMenu();
                onNavDrawerStateChanged(false, false);
			}
			
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle(drawerTitle);
				invalidateOptionsMenu();
                onNavDrawerStateChanged(true, false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                onNavDrawerStateChanged(isNavDrawerOpen(),
                        newState != DrawerLayout.STATE_IDLE);
            }
			
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		mDrawerItems.add(DRAWER_ITEM_ACTIVITY);
		mDrawerItems.add(DRAWER_ITEM_INVITES);
		mDrawerItems.add(DRAWER_ITEM_PLACES);
		mDrawerItems.add(DRAWER_ITEM_ALBUMS);
		
		mDrawerItems.add(DRAWER_SEPARATOR);

		mDrawerItems.add(DRAWER_ITEM_SETTINGS);
		
		mDrawerItemContainer = (ViewGroup) findViewById(R.id.nav_drawer_list);
		
		((View) findViewById(R.id.nav_user_profile)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDrawerLayout.closeDrawer(Gravity.START);
				startUserProfileActivity();
			}
		});
		
		((ShapeImageView) findViewById(R.id.nav_avatar))
                .setImageRes(R.drawable.photo_rob)
                .setShape(ShapeImageView.Shape.CIRCLE);;
		addNavDrawerViews();
	}
	
	private void addNavDrawerViews(){
		for (int i = 0; i < mDrawerItems.size(); i++) {
			int itemId = mDrawerItems.get(i);
			View view = createNavDrawerView(itemId, mDrawerItemContainer);
			mDrawerItemContainer.addView(view);
		}
	}
	
	private View createNavDrawerView(final int itemId, ViewGroup container){
		
		int layoutId = 0;
		if(itemId == DRAWER_SEPARATOR){
			layoutId = R.layout.navdrawer_separator;
		}else{
			layoutId = R.layout.navdrawer_item;
		}
		
		View view = getLayoutInflater().inflate(layoutId, container, false);
		
		if(itemId == DRAWER_SEPARATOR){
			return view;
		}
		
		final boolean selected = getDrawerItemValue() == itemId;
		
		String title = DRAWER_ITEM_TITLE[itemId];
		TextView textView = (TextView) view.findViewById(R.id.title);
		textView.setText(title);
		
		ImageView iconView = (ImageView) view.findViewById(R.id.icon);
		int iconResId = DRAWER_ITEM_ICON_RES[itemId];
		if(iconResId == NO_ICON){
			iconView.setVisibility(View.GONE);
		}else{
			iconView.setImageResource(iconResId);
		}
		
		if(selected){
			setSelectedDrawerItemColor(iconView, textView);
		}
		
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawerItemSelect(itemId);				
			}
		});
		
		return view;
	}
	
	private void drawerItemSelect(final int itemId){
        mDrawerLayout.closeDrawer(Gravity.START);
		
		if(getDrawerItemValue() == itemId) return;
		
		Intent intent;
		
		switch (itemId) {
			
		case DRAWER_ITEM_ACTIVITY:
			intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			finish();
			break;
			
		case DRAWER_ITEM_INVITES:
			intent = new Intent(this, InvitesActivity.class);
			startActivity(intent);
			finish();
			break;
			
		case DRAWER_ITEM_PLACES:
            intent = new Intent(this, PlacesActivity.class);
            startActivity(intent);
            finish();
			break;

        case DRAWER_ITEM_ALBUMS:
                intent = new Intent(this, AlbumsActivity.class);
                startActivity(intent);
                finish();
                break;

		default:
			break;
		}
	}	
	
	private void setSelectedDrawerItemColor(ImageView icon, TextView text){
		icon.setColorFilter(
				getResources().getColor(R.color.theme_color));
		text.setTextColor(
				getResources().getColor(R.color.theme_color));
	}
	
	private void startUserProfileActivity(){
//		Intent intent = new Intent(this, UserProfileActivity.class);
//		startActivity(intent);
	}
	
	protected int getDrawerItemValue(){
		return DRAWER_ITEM_INVALID;
	}

    protected void onNavDrawerStateChanged(boolean isOpen, boolean isAnimating) {
        showOrHideActionBar(true);
    }
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		setupNavDrawer();
		mDrawerToggle.syncState();
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
        	return true;

        return super.onOptionsItemSelected(item);
    }
	
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(Gravity.START);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
	
	@Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }
	
}

