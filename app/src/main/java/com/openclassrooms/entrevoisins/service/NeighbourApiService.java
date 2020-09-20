package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import java.util.List;

/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour : Neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour: Neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Update "Favorite status" of an existing Neighbour"
     * @param neighbour: Neighbour
     */
    void updateFavoriteStatus(Neighbour neighbour);

    /**
     * Update modified fields of an existing Neighbour"
     * @param neighbour: Neighbour
     */
    void updateDataNeighbour(Neighbour neighbour);
}
