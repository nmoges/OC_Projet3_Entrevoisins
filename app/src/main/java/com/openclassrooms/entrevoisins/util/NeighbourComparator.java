package com.openclassrooms.entrevoisins.util;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.Comparator;

public class NeighbourComparator implements Comparator<Neighbour> {

    /**
     * This class is a custom comparator used to determine a
     * lexicographic order between two Neighbour according to their name
     *
     * @param firstNeighbour : Neighbour
     * @param secondNeighbour : Neighbour
     * @return : int
     */
    @Override
    public int compare(Neighbour firstNeighbour, Neighbour secondNeighbour){

        // To prevent the case where a lowercase letter might be positioned at
        // the beginning of the String (and imply wrong lexicographical order results,
        // all letters are modified by using toUpperCase() method
        String nameFirstNeighbour = firstNeighbour.getName().toUpperCase();
        String nameSecondNeighbour = secondNeighbour.getName().toUpperCase();

        return nameFirstNeighbour.compareTo(nameSecondNeighbour);
    }
}
