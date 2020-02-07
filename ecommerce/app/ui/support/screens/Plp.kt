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
import com.ecommerce.app.R
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.contrib.RecyclerViewActions

class Plp {

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

    fun clickProductCardAdd(item: String) {
        onView(withId(R.id.rv_product_list))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(Matchers.allOf(withId(R.id.tm_view_holder_root), hasDescendant(withText(item))),
                        scrollTo()))
        onView(Matchers.allOf(
                withId(R.id.btn_add_to_cart),
                hasSibling(withText(item))))
                .perform(click())
        onView(isRoot()).perform(waitFor(5000))
    }

    fun clickProductCard(item: String, el: Matcher<View>) {
        onView(withId(R.id.rv_product_list))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(Matchers.allOf(withId(R.id.tm_view_holder_root), hasDescendant(withText(item))),
                        scrollTo()))

        onView(Matchers.allOf(
                el,
                isDescendantOfA(Matchers.allOf(withId(R.id.tm_view_holder_root), hasDescendant(withText(item))))))
                .perform(click())

        onView(isRoot()).perform(waitFor(5000))
    }

    fun verifyProductCart(item: String, el: Matcher<View>, text: String = ""){
        onView(withId(R.id.rv_product_list))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(Matchers.allOf(withId(R.id.tm_view_holder_root), hasDescendant(withText(item))),
                        scrollTo()))
        onView(isRoot()).perform(waitFor(3000))

        if (text.isNotEmpty()){
            onView(Matchers.allOf(
                    el,
                    withText(text),
                    isDescendantOfA(Matchers.allOf(withId(R.id.tm_view_holder_root), hasDescendant(withText(item))))))
                    .check(matches(isDisplayed()))
        }
        else{
            onView(Matchers.allOf(
                    el,
                    isDescendantOfA(Matchers.allOf(withId(R.id.tm_view_holder_root), hasDescendant(withText(item))))))
                    .check(matches(isDisplayed()))
        }
    }

}