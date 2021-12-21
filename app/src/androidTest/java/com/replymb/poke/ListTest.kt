package com.replymb.poke

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.replymb.poke.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.greaterThan

@RunWith(AndroidJUnit4::class)
@LargeTest
class ListTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadList() {

        onView(withId(R.id.pokeList_recyclerView)).perform(
            waitUntil(hasItemCount(greaterThan(0))),
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2)
        )
        onView(
            withId(R.id.pokeList_recyclerView)
        )
            .check(
                matches(
                    atPosition(
                        0,
                        withChild(withText("Bulbasaur"))
                    )
                )
            )
    }

}