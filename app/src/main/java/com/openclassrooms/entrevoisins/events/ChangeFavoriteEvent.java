package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user unselect a Neighbour from "Favorites"
 * list.
 */
public class ChangeFavoriteEvent {

    /**
     * Neighbour with "Favorite" status has changed
     */
    public Neighbour favorite;

    /**
     * Constructor.
     * @param favorite : Neighbour
     */
    public ChangeFavoriteEvent(Neighbour favorite){
        this.favorite = favorite;
    }
}
