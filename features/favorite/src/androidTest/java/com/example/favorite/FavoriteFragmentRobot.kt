package com.example.favorite

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.favorite.remote.StubGetFavoriteListUseCaseErrorScenario
import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.presentation.FavoriteFragment
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class FavoriteFragmentRobot {
    fun launchFragment() {
        launchFragmentInContainer<FavoriteFragment>(initialState = Lifecycle.State.STARTED)
    }

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

    private fun getView(id: Int) = onView(withId(id))

    fun loadModulesOfErrorScenario() {
        loadKoinModules(
            module(override = true) {
                factory<GetFavoriteListUseCase> { StubGetFavoriteListUseCaseErrorScenario }
            }
        )
    }

    fun clickOnButton(id: Int) {
        getView(id).perform(click())
    }
}