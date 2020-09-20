package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedNeighbours.toArray())));
    }

    @Test
    public void getFavoriteNeighboursWithSuccess(){
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        List<Neighbour> expectedFavoriteNeighbour = new ArrayList<>();
        for(int i = 0; i < expectedNeighbours.size(); i++){
            if(expectedNeighbours.get(i).getFavorite()){
                expectedFavoriteNeighbour.add(expectedNeighbours.get(i));
            }
        }

        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> favoriteNeighbours = new ArrayList<>();
        for(int i = 0; i < neighbours.size(); i++){
            if(neighbours.get(i).getFavorite()){
                favoriteNeighbours.add(neighbours.get(i));
            }
        }
        assertThat(favoriteNeighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedFavoriteNeighbour.toArray())));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addNeighbourWithSuccess(){
        Neighbour neighbourToAdd = new Neighbour(
                                        service.getNeighbours().size()+1,
                                        "Nicolas",
                                        "https://i.picsum.photos/id/1005/5760/3840.jpg?hmac=2acSJCOwz9q_dKtDZdSB-OIK1HUcwBeXco_RMMTUgfY",
                "Saint-Pierre-du-Mont ; 10km",
                "+33 6 86 57 00 00",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                false,
                "www.facebook.fr/nicolas"
                );
        service.createNeighbour(neighbourToAdd);
        assertTrue(service.getNeighbours().contains(neighbourToAdd));
    }
}
