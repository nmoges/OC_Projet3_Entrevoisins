package com.openclassrooms.entrevoisins.info_neighbour;

import android.support.test.espresso.action.PressBackAction;
import android.support.test.espresso.action.TypeTextAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import java.util.Objects;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class InfoNeighbourTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    private List<Neighbour> mNeighbour;

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
    public void checkInfoOnActivityInfoNeighbour()  {

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

            // Go back to ListNeighbourActivity
            onView(ViewMatchers.withId(R.id.activity_info_neighbour))
                    .perform(new PressBackAction(true));
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

    }

    /**
     * Check if modifications on Neighbour in AddNeighbourActivity are displayed
     * in ActivityInfoNeighbour
     */
    @Test
    public void checkIfModificationsOnActivityInfoNeighbourAreApplied() {
        // Go to ActivityInfoNeighbour
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Click on "Edit" icon menu
        onView(ViewMatchers.withId(R.id.edit_item_menu))
                .perform(click());

        // Modify Name
        onView(ViewMatchers.withId(R.id.name))
                .perform(new TypeTextAction(" M."));

        // Close soft keyboard
        onView(ViewMatchers.isRoot())
                .perform(ViewActions.closeSoftKeyboard());

        // Click on save button
        onView(ViewMatchers.withId(R.id.create))
                .perform(click());

        // Check if new name is displayed
        onView(ViewMatchers.withId(R.id.name_on_avatar_neighbour))
                .check(matches(withText(mNeighbour.get(0).getName() + " M.")));

        onView(ViewMatchers.withId(R.id.title_name_neighbour))
                .check(matches(withText(mNeighbour.get(0).getName() + " M.")));
    }

}
