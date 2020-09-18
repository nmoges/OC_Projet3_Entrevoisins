package com.openclassrooms.entrevoisins.utils;

import android.support.design.widget.TabLayout;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.util.Log;
import android.view.View;

import org.hamcrest.Matcher;

public class TabLayoutAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on tab layout";
    }

    @Override
    public void perform(UiController uiController, View view) {
        Log.i("COUCOU", "COUCOU");
        TabLayout tabLayout = (TabLayout) view;
        TabLayout.Tab tab = tabLayout.getTabAt(1);

        assert tab != null;
        tab.select();
    }
}
