package com.ecommerce.app.ui.support

import android.app.Activity
import android.view.View
import androidx.test.espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.ViewAction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.*
import org.hamcrest.CoreMatchers.*
import com.ecommerce.app.R
import org.hamcrest.BaseMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import android.widget.TextView


class Utility {

    fun getElementFromMatchAtPosition(matcher: Matcher<View>, position: Int): Matcher<View> {
        return object : BaseMatcher<View>() {
            internal var counter = 0
            override fun matches(item: Any): Boolean {
                if (matcher.matches(item)) {
                    if (counter == position) {
                        counter++
                        return true
                    }
                    counter++
                }
                return false
            }
            override fun describeTo(description: Description) {
                description.appendText("Element at hierarchy position $position")
            }
        }
    }

    fun getText(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "Text of the view"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }

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

    fun searchText(item: String) {
        onView(isRoot()).perform(waitFor(2000))
        onView(Matchers.allOf(withId(R.id.menu_item_search), isDisplayed())).perform(ViewActions.click())
        onView(withId(R.id.search_src_text)).perform(clearText(), typeText(item))
        onView(isRoot()).perform(waitFor(1000))
        onView(Matchers.allOf(withId(R.id.search_src_text), isDisplayed())).perform(pressImeActionButton())
        onView(isRoot()).perform(waitFor(2000))
    }

    fun click(vararg el: Matcher<View>){
        onView(isRoot()).perform(waitFor(5000))
        onView(allOf(*el))
                .perform(ViewActions.click())
    }

    fun matches(el: Matcher<View>, matches: Matcher<View>){
        onView(isRoot()).perform(waitFor(5000))
        onView(allOf(el, isDisplayed()))
                .check(matches(matches))
    }

    fun type(el: Matcher<View>, text: String){
        onView(isRoot()).perform(waitFor(3000))
        onView(allOf(el))
                .perform(replaceText(text))
    }

    fun scrollTo(el: Matcher<View>){
        onView(isRoot()).perform(waitFor(5000))
        onView(allOf(el))
                .perform(scrollTo())
    }

    fun swipeUp(el: Matcher<View>){
        onView(isRoot()).perform(waitFor(5000))
        onView(allOf(el))
                .perform(swipeUp())
    }

    fun swipeDown(el: Matcher<View>){
        onView(isRoot()).perform(waitFor(5000))
        onView(allOf(el))
                .perform(swipeDown())
    }

    fun swipeLeft(el: Matcher<View>){
        onView(isRoot()).perform(waitFor(5000))
        onView(allOf(el))
                .perform(swipeLeft())
    }

    fun swipeRight(el: Matcher<View>){
        onView(isRoot()).perform(waitFor(5000))
        onView(allOf(el))
                .perform(swipeRight())
    }

    fun pressBack(){
        onView(isRoot()).perform(waitFor(5000))
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    fun getActivity(): Activity? {
        val activityThreadClass = Class.forName("android.app.ActivityThread")
        val activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null)
        val activitiesField = activityThreadClass.getDeclaredField("mActivities")
        activitiesField.isAccessible = true

        val activities = activitiesField.get(activityThread) as Map<Any, Any> ?: return null

        for (activityRecord in activities.values) {
            val activityRecordClass = activityRecord.javaClass
            val pausedField = activityRecordClass.getDeclaredField("paused")
            pausedField.isAccessible = true
            if (!pausedField.getBoolean(activityRecord)) {
                val activityField = activityRecordClass.getDeclaredField("activity")
                activityField.isAccessible = true
                return activityField.get(activityRecord) as Activity
            }
        }
        return null
    }

    fun activityIs(text: String){
        onView(isRoot()).perform(waitFor(2000))
        assertThat(getActivity().toString(), containsString(text))
    }


    fun pressEnter(el: Matcher<View>){
        onView(isRoot()).perform(waitFor(5000))
        onView(el)
                .perform(pressImeActionButton())
    }

}