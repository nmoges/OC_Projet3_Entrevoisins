package com.openclassrooms.entrevoisins.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.openclassrooms.entrevoisins.ui.favorite_list.FavoriteFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

/**
 * Class for FragmentPagerAdapter implementation in parent activity ListNeighbourActivity
 */
public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private NeighbourFragment mNeighbourFragment = NeighbourFragment.newInstance();
    private FavoriteFragment mFavoriteFragment = FavoriteFragment.newInstance();

    /**
     * Constructor
     * @param fm : FragmentManager
     */
    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position : Fragment position in TabLayout
     * @return : Fragment
     */
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return mNeighbourFragment;
        }
        else{
            return mFavoriteFragment;
        }
    }

    /**
     * Get the number of pages
     * @return : int
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Get mFavoriteFragment instance
     * @return : FavoriteFragment
     */
    public FavoriteFragment getFavoriteFragment(){
        return this.mFavoriteFragment;
    }

}