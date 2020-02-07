package com.ecommerce.app.ui.tests

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.*
import org.junit.runner.RunWith
import com.ecommerce.app.activities.SplashActivity
import com.ecommerce.app.account.AccountManager
import com.ecommerce.app.R
import com.ecommerce.app.ui.support.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import com.ecommerce.app.ui.support.screens.*

@LargeTest
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DealsTest {

    @Rule
    @JvmField

    var activityTestRule = ActivityTestRule(SplashActivity::class.java, false, false)
    var userAccount = UserAccount()
    var util = Utility()
    var plp = Plp()
    var deals = Deals()

    @Before
    fun setUp() {
        AccountManager.clearData()
        userAccount.lead()
        Thread.sleep(6000)
        activityTestRule.launchActivity(null)
    }

    @After
    fun tearDown() {
        AccountManager.clearData()
    }

    @Test
    fun DealsLayout() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(withId(R.id.category_text), withText("Deals"))
        util.matches(withText("We've partnered with these amazing brands to bring you more of what you love."), isDisplayed())
        util.matches(withText("FREE GIFTS"), isDisplayed())
        util.matches(withText("EXTRA SAVINGS"), isDisplayed())
        util.matches(withText("Ecommerce CASH"), isDisplayed())
        util.matches(withId(R.id.tv_header), withText("Extra savings"))
        util.matches(withId(R.id.tv_sub_header), withText("Shop select products and earn Ecommerce Cash, which will be applied to your next order."))
    }

    @Test
    fun VerifySale() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(withId(R.id.category_text), withText("Deals"))
        util.click( util.getElementFromMatchAtPosition(withId(R.id.btn_view_deals), 1))
        util.getElementFromMatchAtPosition(withText("Sale"), 1)
    }

    @Test
    fun VerifyEcommerceCash() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(withId(R.id.category_text), withText("Deals"))
        util.click(withText("Ecommerce CASH"))
        util.matches(withId(R.id.tv_sub_header), withText("Shop select products and earn Ecommerce Cash, which will be applied to your next order."))
        deals.clickDealsCardEcommerceCash("Earn 10% Ecommerce Cash Back on Charlotte's Web", withId(R.id.btn_view_deals))
        util.getElementFromMatchAtPosition(withText("10% Ecommerce Cash"), 1)
    }
    @Test
    fun VerifyFeeGifts() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(withId(R.id.category_text), withText("Deals"))
        util.click(withText("FREE GIFTS"))
        util.matches(withId(R.id.tv_header), withText("Free gift with purchase"))
    }
}
