package com.ecommerce.app.ui.tests

import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.*
import org.junit.runner.RunWith
import com.ecommerce.app.activities.SplashActivity
import com.ecommerce.app.account.AccountManager
import com.ecommerce.app.ui.support.*
import com.ecommerce.app.R
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.hamcrest.CoreMatchers.*


@LargeTest
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PaymentsTest {

    @Rule
    @JvmField

    var activityTestRule = ActivityTestRule(SplashActivity::class.java, false, false)
    var userAccount = UserAccount()
    var util = Utility()

    @Before
    fun setUp() {
        AccountManager.clearData()
        userAccount.lead()
        Thread.sleep(6000)
        activityTestRule.launchActivity(null)
    }

    @Test
    fun VerifyPaymentMetodLayout() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.swipeUp(withId(R.id.navigation_drawer))
        util.click(util.getElementFromMatchAtPosition(withId(R.id.rl_container), 0))
        util.matches(withText("Payments"), isDisplayed())
        util.click(withId(R.id.fab_add))
        util.matches(withText("Recent"), isDisplayed())
        util.matches(allOf(withId(R.id.bt_supported_payment_methods_header), withText("Other")), isDisplayed())
        util.matches(allOf(withId(R.id.bt_payment_method_type), withText("PayPal")), isDisplayed())
        util.matches(allOf(withId(R.id.bt_payment_method_type), withText("Credit or Debit Card")), isDisplayed())
    }

    @Test
    fun VerifyAddNewPaymentMethod() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.swipeUp(withId(R.id.navigation_drawer))
        util.click(util.getElementFromMatchAtPosition(withId(R.id.rl_container), 0))
        util.click(withId(R.id.fab_add))
        util.click(allOf(withId(R.id.bt_payment_method_type), withText("Credit or Debit Card")), isDisplayed())
        util.type(allOf(withId(R.id.bt_card_form_card_number), isDisplayed()), "4111111111111111")
        util.click(withParent(withId(R.id.bt_expiration_month_grid_view)), withText("12"))
        util.click(withParent(withId(R.id.bt_expiration_year_grid_view)), withText("2022"))
        util.type(allOf(withId(R.id.bt_card_form_cvv), isDisplayed()), "123")
        util.click(util.getElementFromMatchAtPosition(withId(R.id.bt_button), 1))
        util.matches(withText("Payments"), isDisplayed())
        util.click(util.getElementFromMatchAtPosition(withId(R.id.tv_credit_card), 0))
        util.click(util.getElementFromMatchAtPosition(withId(R.id.tv_credit_card), 1))
    }

    @Test
    fun VerifyAddInvalidPaymentMethod() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.swipeUp(withId(R.id.navigation_drawer))
        util.click(util.getElementFromMatchAtPosition(withId(R.id.rl_container), 0))
        util.click(withId(R.id.fab_add))
        util.click(allOf(withId(R.id.bt_payment_method_type), withText("Credit or Debit Card")), isDisplayed())
        util.type(allOf(withId(R.id.bt_card_form_card_number), isDisplayed()), "1234567890123456789")
        util.pressEnter(allOf(withId(R.id.bt_card_form_card_number), isDisplayed()))
        util.matches(withText("Card number is invalid"), isDisplayed())
    }

    @Test
    fun VerifyAddEmptyPaymentMethod() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.swipeUp(withId(R.id.navigation_drawer))
        util.click(util.getElementFromMatchAtPosition(withId(R.id.rl_container), 0))
        util.click(withId(R.id.fab_add))
        util.click(allOf(withId(R.id.bt_payment_method_type), withText("Credit or Debit Card")), isDisplayed())
        util.type(allOf(withId(R.id.bt_card_form_card_number), isDisplayed()), "")
        util.pressEnter(allOf(withId(R.id.bt_card_form_card_number), isDisplayed()))
        util.matches(withText("Card number is required"), isDisplayed())
    }
}
