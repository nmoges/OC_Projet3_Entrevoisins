package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user unselect a Neighbour from "Favorites"
 * list.
 */
public class UnselectFavoriteEvent {

    public Neighbour favorite;

    public UnselectFavoriteEvent(Neighbour favorite){
        this.favorite = favorite;
    }
}
