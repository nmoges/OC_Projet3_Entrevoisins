package com.openclassrooms.entrevoisins.favorite_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.TabLayoutAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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
    }

    /**
     * Check if fragment favorite is displayed after clicking in
     * "Favorite" item of tabLayout
     */

    @Test
    public void displayFragmentFavorite_onScroll()  {

        // Scroll right to show "Favorite" fragment
        onView(ViewMatchers.withId(R.id.container))
                .perform(ViewPagerActions.scrollRight());

    }
}
