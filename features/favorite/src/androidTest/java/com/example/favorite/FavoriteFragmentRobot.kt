package com.example.favorite


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.favorite.presentation.FavoriteFragment

class FavoriteFragmentRobot {
    fun launchFragment() {
        launchFragmentInContainer<FavoriteFragment>(initialState = Lifecycle.State.STARTED)
    }

    fun checkListVisibility(id: Int) {
        getView(id)
            .check(ViewAssertions.matches(isDisplayed()))
    }

    fun scrollToItem(name: String, idList: Int) {
        getView(idList)
            .perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText(name)))
            )
    }

    private fun getView(id: Int) = onView(withId(id))

}