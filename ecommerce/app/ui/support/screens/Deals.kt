package com.ecommerce.app.ui.support.screens

import android.view.View
import androidx.test.espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.ViewAction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.*
import org.hamcrest.CoreMatchers.*
import com.ecommerce.app.R
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.contrib.RecyclerViewActions

class Deals {

    fun waitFor(millis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }
            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }
            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }

    fun clickDealsCardExtraSavings(item: String, el: Matcher<View>) {
        onView(isRoot()).perform(waitFor(5000))
        onView(allOf(withId(R.id.rv_deals), hasDescendant(allOf(withId(R.id.tv_header), withText("Extra savings")))))
                .check(matches(hasDescendant(allOf(withId(R.id.iv_deals_discount_item), withContentDescription(item)))))

        onView(allOf(withId(R.id.rv_deals), hasDescendant(allOf(withId(R.id.tv_header), withText("Extra savings")))))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(allOf(withId(R.id.tm_view_holder_root), hasDescendant(allOf(withId(R.id.iv_deals_discount_item), withContentDescription(item)))),
                        scrollTo()))

        onView(allOf(
                el,
                isDescendantOfA(allOf(withId(R.id.tm_view_holder_root), hasDescendant(allOf(withId(R.id.iv_deals_discount_item), withContentDescription(item)))))
        )).perform(click())
        onView(isRoot()).perform(waitFor(5000))
    }

    fun clickDealsCardEcommerceCash(item: String, el: Matcher<View>) {
        onView(isRoot()).perform(waitFor(5000))
        onView(allOf(withId(R.id.rv_deals), hasDescendant(allOf(withId(R.id.tv_header), withText("Ecommerce Cash")))))
                .check(matches(hasDescendant(allOf(withId(R.id.iv_deals_discount_item), withContentDescription(item)))))

        onView(allOf(withId(R.id.rv_deals), hasDescendant(allOf(withId(R.id.tv_header), withText("Ecommerce Cash")))))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(allOf(withId(R.id.tm_view_holder_root), hasDescendant(allOf(withId(R.id.iv_deals_discount_item), withContentDescription(item)))),
                        scrollTo()))

        onView(allOf(
                el,
                isDescendantOfA(allOf(withId(R.id.tm_view_holder_root), hasDescendant(allOf(withId(R.id.iv_deals_discount_item), withContentDescription(item)))))
        )).perform(click())
        onView(isRoot()).perform(waitFor(5000))
    }

}