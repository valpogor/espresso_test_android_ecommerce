package com.ecommerce.app.ui.support.screens

import android.view.View
import androidx.test.espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.ViewAction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import org.hamcrest.*
import com.ecommerce.app.R
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.contrib.RecyclerViewActions

class Cart {

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

    fun clickCartSection(item: Matcher<View>, el: Matcher<View>) {
        onView(isRoot()).perform(waitFor(5000))
        onView(withId(R.id.rv_cart))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(Matchers.allOf(withId(R.id.tm_view_holder_root), hasDescendant(item)),
                        scrollTo()))

        onView(Matchers.allOf(
                el,
                isDescendantOfA(Matchers.allOf(withId(R.id.tm_view_holder_root), hasDescendant(item)))))
                .perform(click())
    }

}