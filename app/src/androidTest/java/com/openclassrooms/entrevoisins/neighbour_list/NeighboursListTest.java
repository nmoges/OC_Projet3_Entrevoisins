
package com.openclassrooms.entrevoisins.neighbour_list;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.PressBackAction;
import android.support.test.espresso.action.ScrollToAction;
import android.support.test.espresso.action.TypeTextAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.InfoNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Objects;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
import static org.junit.Assert.assertEquals;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    private List<Neighbour> mNeighbour;

    private FloatingActionButton fab;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule
            = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        List<Neighbour> expectedNeighbourList = DUMMY_NEIGHBOURS;

        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        mNeighbour = DummyNeighbourGenerator.generateNeighbours();
        assertThat(mNeighbour, containsInAnyOrder(Objects.requireNonNull(expectedNeighbourList.toArray())));
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }


    /**
     * When an item is clicked, the activity ActivityInfoNeighbour is launched
     */
    @Test
    public void activityInfoNeighbour_isLaunched_onClickItem(){
        // Click on first item of the list
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Check if ActivityInfoNeighbour layout is displayed (activity launched)
        onView(ViewMatchers.withId(R.id.activity_info_neighbour))
                .check(matches(isDisplayed()));
    }

    /**
     * For every items in the list :
     * - Launch ActivityInfoNeighbour
     * - Check if information displayed is correct
     */
    @Test
    public void checkInfoOnActivityInfoNeighbour() throws InterruptedException {

        for(int i = 0; i < ITEMS_COUNT; i++){
            // Go to ActivityInfoNeighbour
            onView(ViewMatchers.withId(R.id.list_neighbours))
                    .perform(RecyclerViewActions.scrollToPosition(i))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

            // Check each Neighbour field displayed
            // Name
            onView(ViewMatchers.withId(R.id.name_on_avatar_neighbour))
                    .check(matches(withText(mNeighbour.get(i).getName())));
            onView(ViewMatchers.withId(R.id.title_name_neighbour))
                    .check(matches(withText(mNeighbour.get(i).getName())));

            // Address
            onView(ViewMatchers.withId(R.id.location_neighbour))
                    .check(matches(withText(mNeighbour.get(i).getAddress())));

            // Phone Number
            onView(ViewMatchers.withId(R.id.phone_neighbour))
                    .check(matches(withText(mNeighbour.get(i).getPhoneNumber())));

            // "About Me" section
            onView(ViewMatchers.withId(R.id.text_about_me_neighbour))
                    .check(matches(withText(mNeighbour.get(i).getAboutMe())));

            // "Favorite" status
            // TODO() : to define

            // Go back to ListNeighbourActivity
            onView(ViewMatchers.withId(R.id.activity_info_neighbour))
                    .perform(new PressBackAction(true));

            Thread.sleep(1000);
        }
    }

    /**
     * Check if clicking on "Edit" button in ActivityInfoNeighbour activity
     * launches AddNeighbourActivity activity to modify current Neighbour
     */
    @Test
    public void checkIfCurrentNeighbourInfoIsDisplayed_onClickItemMenu(){
        // Go to ActivityInfoNeighbour
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Click on "Edit" icon menu
        onView(ViewMatchers.withId(R.id.edit_item_menu))
                .perform(click());

        // Check if ActivityAddNeighbour is launched
        onView(ViewMatchers.withId(R.id.activity_add_neighbour))
                .check(matches(isDisplayed()));

        // Check information in edit fields
        // Name
        onView(ViewMatchers.withId(R.id.name))
                .check(matches(withText(mNeighbour.get(0).getName())));

        // Phone number
        onView(ViewMatchers.withId(R.id.phoneNumber))
                .check(matches(withText(mNeighbour.get(0).getPhoneNumber())));

        // Website
        onView(ViewMatchers.withId(R.id.webSite))
                .check(matches(withText(mNeighbour.get(0).getWebSite())));

        // Address
        onView(ViewMatchers.withId(R.id.address))
                .check(matches(withText(mNeighbour.get(0).getAddress())));

        // "About Me" section
        onView(ViewMatchers.withId(R.id.aboutMe))
                .check(matches(withText(mNeighbour.get(0).getAboutMe())));

        // Avatar
        // TODO() : to define
    }

    @Test
    public void checkIfModificationsOnActivityInfoNeighbourAreSaved() throws InterruptedException {
        // TODO() : to implement
        // Go to ActivityInfoNeighbour
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Click on "Edit" icon menu
        onView(ViewMatchers.withId(R.id.edit_item_menu))
                .perform(click());

        // Modify Name
        onView(ViewMatchers.withId(R.id.name))
                .perform(new TypeTextAction(" M."));

        Thread.sleep(2);

        // Scroll to access the button
        //onView(ViewMatchers.withId(R.id.scrollViewAdd))
        //        .perform(new ScrollToAction());

        // Click on save button
       onView(ViewMatchers.withId(R.id.create))
                .perform(click());

        Thread.sleep(4);
        // Check if new name is displayed
        /*onView(ViewMatchers.withId(R.id.name_on_avatar_neighbour))
                .check(matches(withText(mNeighbour.get(0).getName() + " M.")));

        onView(ViewMatchers.withId(R.id.title_name_neighbour))
                .check(matches(withText(mNeighbour.get(0).getName() + " M.")));*/

    }
}