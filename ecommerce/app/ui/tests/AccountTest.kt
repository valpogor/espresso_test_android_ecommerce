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
import com.ecommerce.app.ui.support.screens.*
import org.hamcrest.CoreMatchers.*
import android.util.Log
import androidx.test.espresso.Espresso.onView


@LargeTest
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AccountTest {

    @Rule
    @JvmField

    var activityTestRule = ActivityTestRule(SplashActivity::class.java, false, false)
    var userAccount = UserAccount()
    var util = Utility()
    var account = Account()
    var plp = Plp()
    var cart = Cart()

    @Before
    fun setUp() {
        AccountManager.clearData()
        userAccount.lead()
        Thread.sleep(6000)
        activityTestRule.launchActivity(null)
    }

    @Test
    fun accountChangePassword(){
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.swipeUp(withId(R.id.rv_account))
        account.clickAccountSection(withText("Name & password"), withId(R.id.tv_label))
        util.matches(withId(R.id.et_firstname), isDisplayed())
        util.matches(withId(R.id.et_lastname), isDisplayed())
        util.matches(withId(R.id.email_container), isDisplayed())
        util.matches(withId(R.id.btn_reset_password), isDisplayed())
    }

    @Test
    fun verifyAccountPageHeader() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.matches(withText("My Account"), isDisplayed())
        util.matches(withId(R.id.menu_item_cart), isDisplayed())
        util.matches(withId(R.id.menu_item_search), isDisplayed())
        util.matches(withId(R.id.menu_item_scan), isDisplayed())
    }
    @Test
    fun verifyAccountPageLayoutForLead() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.matches(withText("My Account"), isDisplayed())
        util.matches(withId(R.id.tv_initials), isDisplayed())
        util.matches(withId(R.id.tv_name), isDisplayed())
        util.matches(withId(R.id.tv_member_since), isDisplayed())
        util.matches(withId(R.id.tv_savings), isDisplayed())
        util.matches(withId(R.id.tm_invite_friends_root), isDisplayed())
        util.matches(allOf(withId(R.id.header), withText("Invite friends")), isDisplayed())
        util.matches(withId(R.id.tv_message), withText("Invite more friends to earn more cash!"))
        util.matches(allOf(withId(R.id.header), withText("Redeem gift card")), isDisplayed())
        util.matches(allOf(withId(R.id.tv_label), withText("Enter your code to redeem a gift card")), isDisplayed())
        util.matches(allOf(withId(R.id.header), withText("Favorites")), isDisplayed())
        util.matches(allOf(withId(R.id.tv_label), withText("Nothing saved.")), isDisplayed())
        util.matches(allOf(withId(R.id.header), withText("Manage Autoship")), isDisplayed())
        util.swipeUp(withId(R.id.rv_account))
//        util.matches(allOf(withId(R.id.tv_my_autoship), withText("My Autoship")), isDisplayed())
//        util.matches(withId(R.id.tv_manage_autoship), withText("Manage your monthly boxes"))
//        util.matches(allOf(withId(R.id.header), withText("Orders")), isDisplayed())
//        util.matches(allOf(withId(R.id.tv_no_orders), withText("No orders yet.")), isDisplayed())
//        util.matches(allOf(withId(R.id.header), withText("Default shipping address")), isDisplayed())
//        util.matches(allOf(withId(R.id.tv_label), withText("None saved.")), isDisplayed())
//        util.matches(allOf(withId(R.id.btn_action), withText("Add New")), isDisplayed())
//        util.matches(allOf(withId(R.id.header), withText("Default payment method")), isDisplayed())
        util.matches(allOf(withId(R.id.header), withText("My reviews")), isDisplayed())
        util.matches(allOf(withId(R.id.tv_label), withText("Shop to unlock Ecommerce Cash credits!")), isDisplayed())
        util.matches(allOf(withId(R.id.header), withText("Name & password")), isDisplayed())
        util.matches(allOf(withId(R.id.header), withText("Membership")), isDisplayed())
        util.matches(allOf(withId(R.id.textView2), withText("Need Help?")), isDisplayed())
        util.matches(allOf(withId(R.id.tv_open_chat), withText("Chat")), isDisplayed())
        util.matches(allOf(withId(R.id.btn_logout), withText("Log Out")), isDisplayed())
    }

    @Test
    fun verifyAccountPageInviteFriends() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.matches(withText("My Account"), isDisplayed())
        util.click(allOf(withId(R.id.header), withText("Invite friends")))
        util.matches(allOf(withId(R.id.tv_Ecommerce_cash_balance), withText("Ecommerce Cash Balance: $0.00")), isDisplayed())
        util.matches(allOf(withId(R.id.tv_title), withText("Give and Get $25")), isDisplayed())
        util.matches(allOf(withText("Invite Friends")), isDisplayed())
        util.matches(allOf(withText("Share")), isDisplayed())
        util.matches(withText("*Maximum discount of $25 applies."), isDisplayed())
    }

    @Test
    fun verifyAccountPageGiftCard() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.click(allOf(withId(R.id.tv_label), withText("Enter your code to redeem a gift card")))
        util.matches(allOf(withId(R.id.tv_header), withText("Redeem your gift!")), isDisplayed())
//        util.type(withId(R.id.et_recipient_email), "6MM3YIF7")
//        util.scrollTo(withId(R.id.btn_redeem))
//        util.click(withId(R.id.btn_redeem))
//        util.matches(withText("Your shopping credit will be displayed at checkout and applied to your next purchase."), isDisplayed())
    }

    @Test
    fun verifyAccountAutoshipLead() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
//        util.scrollTo(withId(R.id.tv_manage_autoship))
//        util.click(allOf(withId(R.id.tv_manage_autoship), withText("Manage your monthly boxes")), isDisplayed())
//        util.matches(withText("Manage Autoship"), isDisplayed())
//        util.matches(withText("Total $0.00"), isDisplayed())
    }

    @Test
    fun verifyAccountMyProfile() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.swipeUp(withId(R.id.rv_account))
        util.click(allOf(withId(R.id.tv_label), withText(containsString("Ecommerce User"))))
        util.matches(withText("My Profile"), isDisplayed())
        util.matches(withId(R.id.et_firstname), isDisplayed())
        util.matches(withId(R.id.et_lastname), isDisplayed())
        util.matches(withId(R.id.et_email), isDisplayed())
        util.click(allOf(withId(R.id.btn_reset_password), withText("Reset Password")), isDisplayed())
        util.matches(withId(R.id.message), withText(containsString("Password reset succesfully")))
    }

    @Test
    fun verifyAccountPageMembershipTrial() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.swipeUp(withId(R.id.rv_account))
        util.click(allOf(withId(R.id.tv_label), withText(containsString("Your trial will expire on:"))))
        util.matches(withText("Membership"), isDisplayed())
        util.matches(allOf(withId(R.id.status), withText("Status Trial")), isDisplayed())
    }

    @Test
    fun verifyAccountPageChat() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.swipeUp(withId(R.id.rv_account))
        util.click(allOf(withId(R.id.tv_open_chat), withText("Chat")))
        util.matches(allOf(withId(R.id.tv_title), withText("Want to keep chatting?")), isDisplayed())
        util.matches(allOf(withId(R.id.btn_activate_item), withText("Activate")), isDisplayed())
        util.matches(allOf(withId(R.id.btn_cancel), withText("Cancel")), isDisplayed())
    }

    @Test
    fun verifyAccountPageLogOut() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.swipeUp(withId(R.id.rv_account))
        util.click(allOf(withId(R.id.btn_logout), withText("Log Out")))
//        util.matches(withId(R.id.message), withText(containsString("Are you sure you want to logout?")))
//        util.click(allOf(withId(R.id.button2), withText("CANCEL")))
//        util.swipeUp(withId(R.id.rv_account))
//        util.click(allOf(withId(R.id.btn_logout), withText("Log Out")))
//        util.click(allOf(withId(R.id.button1), withText("OK")))
//        util.click(allOf(withId(R.id.btn_logout), withText("Log Out")))
    }
}
