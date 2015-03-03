package com.robdich.wanderlust.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.fragments.FeedFragment;
import com.robdich.wanderlust.fragments.NotificationsFragment;
import com.robdich.wanderlust.view.SlidingTabLayout;

public class HomeActivity extends BaseNavDrawerActivity{

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    private FeedFragment mFeedFragment;
    private NotificationsFragment mNotificationsFragment;

    private static final int FRAGMENTS_COUNT = 2;
    private static final String PAGE_FEED = "FEED";
    private static final String PAGE_NOTIFICATIONS = "NOTIFICATIONS";
    private static final String TAG_FEED = "fragment_feed";
    private static final String TAG_NOTIFICATIONS = "fragment_notifications";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.tab_selected_strip));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                showOrHideActionBar(true);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
	}

    @Override
	protected CharSequence getActivityTitle() {
		return getResources().getString(R.string.activity_home);
	}

    @Override
    protected View getHideableView() {
        return (findViewById(R.id.header_bar));
    }

    @Override
	protected int getDrawerItemValue() {
		return DRAWER_ITEM_ACTIVITY;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // orientation changes.
        if (mFeedFragment != null) {
            getSupportFragmentManager().putFragment(outState, TAG_FEED,
                    mFeedFragment);
        }

        if (mNotificationsFragment != null) {
            getSupportFragmentManager().putFragment(outState, TAG_NOTIFICATIONS,
                    mNotificationsFragment);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mFeedFragment == null) {
            mFeedFragment = (FeedFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, TAG_FEED);
        }

        if (mNotificationsFragment == null) {
            mNotificationsFragment = (NotificationsFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, TAG_NOTIFICATIONS);
        }
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return (mFeedFragment = new FeedFragment());
                case 1:
                    return (mNotificationsFragment = new NotificationsFragment());
            }

            return null;
        }

        @Override
        public int getCount() {
            return FRAGMENTS_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0: return PAGE_FEED;
                case 1: return PAGE_NOTIFICATIONS;
            }

            return null;
        }
    }

}
