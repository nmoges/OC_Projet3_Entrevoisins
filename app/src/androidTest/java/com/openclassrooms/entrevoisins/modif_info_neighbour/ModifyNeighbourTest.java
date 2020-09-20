package com.openclassrooms.entrevoisins.modif_info_neighbour;

import android.support.test.espresso.action.TypeTextAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.util.List;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ModifyNeighbourTest {

    private List<Neighbour> mNeighbour;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule
            = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mNeighbour = DummyNeighbourGenerator.generateNeighbours();
    }
    /**
     * Check if modifications on Neighbour in AddNeighbourActivity are displayed
     * in ActivityInfoNeighbour
     */
    @Test
    public void checkIfModificationsOnActivityInfoNeighbourAreApplied() {

        // Go to ActivityInfoNeighbour
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

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
                .check(matches(withText(mNeighbour.get(1).getName() + " M.")));

        onView(ViewMatchers.withId(R.id.title_name_neighbour))
                .check(matches(withText(mNeighbour.get(1).getName() + " M.")));
    }
}
