package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void updateFavoriteStatus(Neighbour neighbour){
        int index = neighbours.indexOf(neighbour);
        neighbours.get(index).setFavorite(!neighbours.get(index).getFavorite());
    }
}
