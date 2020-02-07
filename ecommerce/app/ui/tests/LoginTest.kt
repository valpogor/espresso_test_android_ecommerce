package com.ecommerce.app.ui.tests.login

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import com.ecommerce.app.activities.SplashActivity

import org.junit.Before;

import com.ecommerce.app.account.AccountManager
import com.ecommerce.app.AppSessionStore
import com.ecommerce.app.R

import com.ecommerce.app.ui.support.UserAccount
import com.ecommerce.app.ui.support.Utility
import org.hamcrest.Matchers
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.*


@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(SplashActivity::class.java, false, false)

    var userAccount = UserAccount()
    var util = Utility()

    @Before
    fun setUp() {
        AccountManager.clearData()
        activityTestRule.launchActivity(null)
    }

//    @Test
//    fun loginGuidedShoppingTest() {
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
//        val formatted = current.format(formatter)
//        Thread.sleep(5000)
//        util.type(withId(R.id.et_email), "testerqa+$formatted@mailinator.com")
//        util.click(withId(R.id.btn_continue), withText("Continue"))
//        util.type(withId(R.id.et_password), "tester")
//        util.click(withId(R.id.btn_continue), withText("Create Account"))
//        util.click(withId(R.id.btn_choose_payment), withText("Choose payment method"))
//        util.click(withId(R.id.bt_payment_method_type), withText("Credit or Debit Card"))
//        util.type(allOf(withId(R.id.bt_card_form_card_number), isDisplayed()), "4111111111111111")
//        util.click(withParent(withId(R.id.bt_expiration_month_grid_view)), withText("12"))
//        util.click(withParent(withId(R.id.bt_expiration_year_grid_view)), withText("2022"))
//        util.type(allOf(withId(R.id.bt_card_form_cvv), isDisplayed()), "123")
//        util.click(withId(R.id.bt_button))
//        util.click(withId(R.id.btn_start_trial))
//        util.matches(withId(R.id.textView1), withText("Let's get started!"))
//        util.click(withId(R.id.btn_next))
//    }

    @Test
    fun registerLeadTest() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
        val formatted = current.format(formatter)
        Thread.sleep(5000)
        util.type(withId(R.id.et_email), "testerqa+$formatted@mailinator.com")
        util.click(withId(R.id.btn_continue), withText("Continue"))
        util.type(withId(R.id.et_password), "tester")
        util.click(withId(R.id.btn_continue), withText("Create Account"))
        util.click(withId(R.id.btn_choose_payment), withText("Choose payment method"))
        util.click(withId(R.id.bt_payment_method_type), withText("Credit or Debit Card"))
        util.type(allOf(withId(R.id.bt_card_form_card_number), isDisplayed()), "4111111111111111")
        util.click(withParent(withId(R.id.bt_expiration_month_grid_view)), withText("12"))
        util.click(withParent(withId(R.id.bt_expiration_year_grid_view)), withText("2022"))
        util.type(allOf(withId(R.id.bt_card_form_cvv), isDisplayed()), "123")
        util.click(withId(R.id.bt_button), withText("Add card"))
        util.click(withId(R.id.btn_start_trial))
    }

    @Test
    fun registerVisitorTest() {
        util.click(withId(R.id.tv_skip))
        util.matches(withId(R.id.tm_tool_bar), hasDescendant(withText("Ecommerce Market")))
    }

    @Test
    fun loginIvalidID() {
        util.type(withId(R.id.et_email), "")
        util.click(withId(R.id.btn_continue), withText("Continue"))
        util.matches(allOf(withId(R.id.icon), withContentDescription("Notification Icon")), hasSibling(withText("Please enter email address")))
        util.type(withId(R.id.et_email), "invalid@email")
        util.click(withId(R.id.btn_continue), withText("Continue"))
        util.matches(allOf(withId(R.id.icon), withContentDescription("Notification Icon")), hasSibling(withText("Please enter a valid email address")))
    }

    @Test
    fun registerInvalidPassword() {
        util.type(withId(R.id.et_email), "unregisteredtestingaccount@ecommercetesting.com")
        util.click(withId(R.id.btn_continue), withText("Continue"))
        util.type(withId(R.id.et_password), "")
        util.click(withId(R.id.btn_continue), withText("Create Account"))
        util.matches(allOf(withId(R.id.icon), withContentDescription("Notification Icon")), hasSibling(withText("Please enter password")))
        util.type(withId(R.id.et_password), "12345")
        util.scrollTo(allOf(withId(R.id.btn_continue), withText("Create Account")))
        util.click(withId(R.id.btn_continue), withText("Create Account"))
        util.matches(allOf(withId(R.id.icon), withContentDescription("Notification Icon")), hasSibling(withText("The password must have at least 6 characters")))
    }

    @Test
    fun registerExistingAccount() {
        util.type(withId(R.id.et_email), "basic_customer@ecommerce.com")
        util.click(withId(R.id.btn_continue), withText("Continue"))
        util.type(withId(R.id.et_password), "tester")
        util.click(withId(R.id.btn_continue), withText("Create Account"))
        util.matches(withId(R.id.btn_login), withText("Sign in"))
        util.matches(withId(R.id.tv_forgot_password), withText("Forgot password?"))
        util.matches(withId(R.id.et_email), withText("basic_customer@ecommerce.com"))
    }

    @Test
    fun loginInvalidID(){
        util.click(withId(R.id.tv_login))
        util.type(withId(R.id.et_email), "")
        util.click(withId(R.id.btn_login))
        util.matches(withId(R.id.textinput_error), withText("Please enter email address"))
        util.type(withId(R.id.et_email), "invalid@email")
        util.click(withId(R.id.btn_login))
        util.matches(withId(R.id.textinput_error), withText("Please enter a valid email address"))
    }

    @Test
    fun loginInvalidPassword(){
        util.click(withId(R.id.tv_login))
        util.type(withId(R.id.et_email), "basic_customer@ecommerce.com")
        util.type(withId(R.id.et_password), "")
        util.click(withId(R.id.btn_login))
        util.matches(withId(R.id.textinput_error), withText("Please enter password"))
        util.type(withId(R.id.et_password), "12345")
        util.click(withId(R.id.btn_login))
        util.matches(allOf(withId(R.id.text)), withText("This email and password don't match an account."))
    }

    @Test
    fun loginValidInfo(){
        util.click(withId(R.id.tv_login))
        util.type(withId(R.id.et_email), "basic_customer@ecommerce.com")
        util.type(withId(R.id.et_password), "tester")
        util.click(withId(R.id.btn_login))
        util.matches(withId(R.id.tm_tool_bar), hasDescendant(withText("Ecommerce Market")))
    }

}

// package com.ecommerce.app.ui.tests

// import android.os.SystemClock
// import android.widget.EditText
// import androidx.test.espresso.Espresso.onData
// import androidx.test.espresso.Espresso.onView
// import androidx.test.espresso.IdlingRegistry
// import androidx.test.espresso.action.ViewActions.*
// import androidx.test.espresso.matcher.ViewMatchers.*
// import androidx.test.ext.junit.runners.AndroidJUnit4
// import androidx.test.filters.LargeTest
// import androidx.test.platform.app.InstrumentationRegistry
// import androidx.test.rule.ActivityTestRule
// import androidx.test.uiautomator.UiDevice
// import androidx.test.uiautomator.UiSelector
// import com.ecommerce.app.R
// import com.ecommerce.app.activities.SplashActivity
// import com.ecommerce.app.automation.instrumentation.fixture.rules.EnsureLogoutRule
// import com.ecommerce.app.automation.instrumentation.fixture.rules.VolleyIdlingResourceRule
// import com.ecommerce.app.ui.support.Utility
// import com.ecommerce.app.ui.support.pages.LoginPage
// import org.hamcrest.Matchers
// import org.junit.After
// import org.junit.Before
// import org.junit.Rule
// import org.junit.Test
// import org.junit.rules.RuleChain
// import org.junit.rules.RuleChain.emptyRuleChain
// import org.junit.rules.TestRule
// import org.junit.runner.RunWith

// @RunWith(AndroidJUnit4::class)
// @LargeTest
// class LoginTest {
//     private var apiIdlingResource = VolleyIdlingResourceRule()
//     @get: Rule
//     val ruleChain: TestRule = RuleChain
//             .outerRule(emptyRuleChain())
//             .around(apiIdlingResource)
//             .around(EnsureLogoutRule())

//     private lateinit var uiDevice: UiDevice

//     @get: Rule
//     var activityTestRule: ActivityTestRule<SplashActivity> = ActivityTestRule(SplashActivity::class.java, true, true)

//     @Before
//     fun setUp() {
//         IdlingRegistry.getInstance().register(activityTestRule.activity.countingIdlingResource)
//         uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
//     }

//     //TODO find out if there's a QA login account to test login and update Utility with login info
//     //@Test
//     fun verifyUserCanLogin() {
//         LoginPage.verifyUserCanLogin()
//     }


//     @Test
//     fun verifyUserCanCreateNewAccount() {

//         val userName = Utility.getUserName()
//         onView(Matchers.allOf(withId(R.id.et_email)))
//                 .perform(replaceText(userName), closeSoftKeyboard())

//         onView(Matchers.allOf(withId(R.id.btn_continue), withText(R.string.tm_string_continue)))
//                 .perform(scrollTo(), click())

//         onView(Matchers.allOf(withId(R.id.et_password)))
//                 .perform(replaceText("tester"), closeSoftKeyboard())

//         onView(Matchers.allOf(withId(R.id.btn_continue), withText(R.string.tm_string_create_account)))
//                 .perform(scrollTo(), click())

//         onView(Matchers.allOf(withId(R.id.btn_choose_payment), withText(R.string.tm_string_d2t_choose_payment)))
//                 .perform(click())

//         //Select 'Credit or Debit Card' from the list
//         onData(Matchers.anything()).inAdapterView(Matchers.allOf(withId(R.id.bt_supported_payment_methods)))
//                 .atPosition(1)
//                 .perform(click())

//         uiDevice.findObject(UiSelector().className(EditText::class.java).instance(0))
//                 .text = Utility.getCreditCardNumber()


//         onData(Matchers.anything()).inAdapterView(Matchers.allOf(withId(R.id.bt_expiration_month_grid_view)))
//                 .atPosition(11)
//                 .perform(click())

//         onData(Matchers.anything()).inAdapterView(Matchers.allOf(withId(R.id.bt_expiration_year_grid_view)))
//                 .atPosition(3)
//                 .perform(click())

//         uiDevice.findObject(UiSelector().className(EditText::class.java).instance(2)).text = Utility.getCreditCardCVV()


//         onView(Matchers.allOf(withId(R.id.bt_button), withText(R.string.bt_add_card)))
//                 .perform(click())

//         SystemClock.sleep(1000) //wait for BrainTree activity above to complete before checking next line

//         onView(Matchers.allOf(withId(R.id.btn_start_trial), withText(R.string.tm_string_d2t_start_trial), isDisplayed()))

//     }

//     @After
//     fun tearDown() {
//         IdlingRegistry.getInstance().unregister(activityTestRule.activity.countingIdlingResource)
//     }
// }
