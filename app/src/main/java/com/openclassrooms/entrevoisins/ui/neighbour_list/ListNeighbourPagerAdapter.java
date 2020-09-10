package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.Observable;
import java.util.Observer;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter implements Observer {

    private NeighbourFragment neighbourFragment = NeighbourFragment.newInstance();
    private FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return neighbourFragment;
        }
        else{
            return favoriteFragment;
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

    public FavoriteFragment getFavoriteFragment(){
        return this.favoriteFragment;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}