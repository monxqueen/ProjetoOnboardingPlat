package com.example.storeapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.storeapp.presentation.MainActivity
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.storeapp", appContext.packageName)
    }

    @Test
    fun whenMainActivityIsLaunched_shouldDisplayBottomNavigation() {
        onView(withId(R.id.bottomNav)).check(matches(isDisplayed()))
    }

    @Test
    fun whenMainActivityIsLaunched_shouldDisplayAppTitle() {
        onView(withId(R.id.txtViewTitle)).check(matches(isDisplayed()))
    }
}