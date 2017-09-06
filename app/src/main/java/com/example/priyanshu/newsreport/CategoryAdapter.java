package com.example.priyanshu.newsreport;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by Priyanshu on 19-Jan-17.
 */

public class CategoryAdapter extends FragmentStatePagerAdapter {
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PoliticsFragment();
        } else if (position == 1) {
            return new CricketFragment();
        } else if (position == 2) {
            return new BadmintonFragment();
        } else {
            return new MoviesFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Politics";
        } else if (position == 1) {
            return "Cricket";
        } else if (position == 2) {
            return "Badminton";
        } else {
            return "Movies";
        }
    }
}

