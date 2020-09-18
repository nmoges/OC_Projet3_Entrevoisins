package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.util.NeighbourComparator;

import java.util.Collections;
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
        NeighbourComparator comparator = new NeighbourComparator();

        int indice = 0;
        boolean found = false;

        // Find position in list
        while( indice < neighbours.size() && !found){
            if ( comparator.compare(neighbours.get(indice), neighbour) > 0
                    || comparator.compare(neighbours.get(indice), neighbour) == 0){
                neighbours.add(indice, neighbour);
                found = true;
            }
            else{
                indice++;
            }
        }

        // If not found, item must be positioned at the end
        if(!found){ neighbours.add(neighbour); }

    }

    @Override
    public void updateFavoriteStatus(Neighbour neighbour){
        int index = neighbours.indexOf(neighbour);
        neighbours.get(index).setFavorite(!neighbours.get(index).getFavorite());
    }

    @Override
    public void updateDataNeighbour(Neighbour neighbour){
        int index = neighbours.indexOf(neighbour);
        // Remove old version of Neighbour
        neighbours.remove(index);
        // Repositioning new version of Neighbour in list
        createNeighbour(neighbour);
    }
}
