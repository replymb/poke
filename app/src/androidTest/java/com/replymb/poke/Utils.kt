package com.replymb.poke

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

fun waitUntil(matcher: Matcher<View>): ViewAction = object : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return CoreMatchers.any(View::class.java)
    }

    override fun getDescription(): String {
        return StringDescription().let {
            matcher.describeTo(it)
            "wait until: $it"
        }
    }

    override fun perform(uiController: UiController, view: View) {
        if (!matcher.matches(view)) {
            ViewPropertyChangeCallback(matcher, view).run {
                try {
                    IdlingRegistry.getInstance().register(this)
                    view.viewTreeObserver.addOnDrawListener(this)
                    uiController.loopMainThreadUntilIdle()
                } finally {
                    view.viewTreeObserver.removeOnDrawListener(this)
                    IdlingRegistry.getInstance().unregister(this)
                }
            }
        }
    }
}

fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position)
                ?: // has no item on such position
                return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}

fun hasItemCount(itemCount: Matcher<Int>): Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item count: ")
            itemCount.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            return view.adapter?.let {
                itemCount.matches(it.itemCount)
            } ?: false
        }
    }
}