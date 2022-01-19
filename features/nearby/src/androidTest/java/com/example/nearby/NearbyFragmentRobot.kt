package com.example.nearby

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.nearby.presentation.NearbyFragment
import org.koin.test.KoinTest
import org.koin.test.get

fun RecyclerView.waitForRecyclerViewData(block: () -> Unit) {
    var shouldRepeat = false
    do {
        block.invoke()
        if (shouldRepeat) Thread.sleep(100)
        shouldRepeat = this.isVisible && this.adapter != null && this.adapter?.itemCount?: -1 > 0
    } while (!shouldRepeat)
}

fun TextView.waitForTextViewData(block: () -> Unit) {
    var shouldRepeat = false
    do {
        block.invoke()
        if (shouldRepeat) Thread.sleep(100)
        shouldRepeat = this.isVisible
    } while (!shouldRepeat)
}


class NearbyFragmentRobot : KoinTest{

    val fragment = get<NearbyFragment>()

    fun launchFragment() {
        launchFragmentInContainer<NearbyFragment>(initialState = Lifecycle.State.STARTED)
    }

    private fun getView(id: Int) = onView(withId(id))

    fun checkVisibility(id: Int) {
        getView(id)
            .check(matches(isDisplayed()))
    }

    fun checkRecyclerViewVisibility(id: Int) {
        val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.rvStoresList)

        recyclerView?.waitForRecyclerViewData {
            checkVisibility(id)
        }
    }

    fun checkTextViewVisibility(id: Int) {
        val textView = fragment.view?.findViewById<TextView>(id)

        textView?.waitForTextViewData {
            checkVisibility(id)
        }
    }

    fun scrollToRecyclerViewItem(name: String, idList: Int) {
        val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.rvStoresList)

        recyclerView?.waitForRecyclerViewData {
            getView(idList)
                .perform(
                    RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                        hasDescendant(withText(name))
                    )
                )
        }
    }

    fun clickOnButton(id: Int) {
        getView(id).perform(click())
    }
}
