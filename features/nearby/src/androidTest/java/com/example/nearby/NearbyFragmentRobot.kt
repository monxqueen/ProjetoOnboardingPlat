package com.example.nearby

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.nearby.presentation.NearbyFragment

class NearbyFragmentRobot {

    fun launchFragment() {
        launchFragmentInContainer<NearbyFragment>(initialState = Lifecycle.State.STARTED)
    }

    private fun getView(id: Int) = onView(withId(id))

    fun checkVisibility(id: Int) {
        getView(id)
            .check(matches(isDisplayed()))
    }

    fun scrollToItem(name: String, idList: Int) {
        getView(idList)
            .perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText(name)))
            )
    }

    fun clickOnButton(id: Int) {
        getView(id).perform(click())
    }
}
