package com.openclassrooms.entrevoisins.favorite_list;


import android.support.test.espresso.action.PressBackAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.FavoriteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */

@RunWith(AndroidJUnit4.class)
public class FavoriteListTest {

    private ListNeighbourActivity mActivity;

    private List<Neighbour> mNeighbour = new ArrayList<>();
    private List<Neighbour> mFavorites = new ArrayList<>();

    private int ITEM_COUNT;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule
            = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        List<Neighbour> expectedNeighbourList = DUMMY_NEIGHBOURS;

        // Check if activity is not null
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        // Initialize list Neighbour
        mNeighbour = DummyNeighbourGenerator.generateNeighbours();
        assertThat(mNeighbour, containsInAnyOrder(Objects.requireNonNull(expectedNeighbourList.toArray())));

        // Initialize list Favorites
        for(int i = 0; i < mNeighbour.size(); i++){
            if(mNeighbour.get(i).getFavorite()){
                mFavorites.add(mNeighbour.get(i));
            }
        }

        ITEM_COUNT = mFavorites.size();
    }

    /**
     * Check if fragment favorite is displayed by swipe to
     * display "Favorite" tab section
     */
    @Test
    public void displayFragmentFavorite_onScroll()  {

        // Scroll right to show "Favorite" fragment
        onView(ViewMatchers.withId(R.id.container))
                .perform(ViewPagerActions.scrollRight());
    }

    /**
     * Check if all elements displayed in "Favorite" Fragment
     * are Neighbour with favorite == true
     */
    @Test
    public void checkIfAllElementsDisplayedInFavoriteFragmentAreFavoriteNeighbour()  {

        // Scroll right to show "Favorite" fragment
        onView(ViewMatchers.withId(R.id.container))
                .perform(ViewPagerActions.scrollRight());

        for(int i = 0; i < mFavorites.size(); i++){
            // Click on item
            onView(ViewMatchers.withId(R.id.list_favorites))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

            // Check if name == name expected in mFavorite list
            onView(ViewMatchers.withId(R.id.name_on_avatar_neighbour))
                    .check(matches(withText(mFavorites.get(i).getName())));

            // Go back to ListNeighbourActivity
            onView(ViewMatchers.withId(R.id.activity_info_neighbour))
                    .perform(new PressBackAction(true));
        }
    }

    /**
     * When a Neighbour "Favorite" status is switched to false, this
     * Neighbour must be removed from the "Favorite" list.
     */
    @Test
    public void myFavoriteList_uncheckFavoriteAction_shouldRemoveItemFromFavoriteList(){
        // Scroll right to show "Favorite" fragment
        onView(ViewMatchers.withId(R.id.container))
                .perform(ViewPagerActions.scrollRight());

        onView(ViewMatchers.withId(R.id.list_favorites))
                .check(withItemCount(ITEM_COUNT));

        // Perform click on "Favorite" icon
        onView(ViewMatchers.withId(R.id.list_favorites))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new FavoriteViewAction()));

        // Check if size of Favorite list has changed

        onView(ViewMatchers.withId(R.id.list_favorites))
                .check(withItemCount(ITEM_COUNT-1));
    }
}
