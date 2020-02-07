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
class CheckoutTest {

    @Rule
    @JvmField

    var activityTestRule = ActivityTestRule(SplashActivity::class.java, false, false)
    var userAccount = UserAccount()
    var util = Utility()
    var plp = Plp()

    @Before
    fun setUp() {
        AccountManager.clearData()
        userAccount.lead()
        Thread.sleep(6000)
        activityTestRule.launchActivity(null)
    }


    @Test
    fun checkoutFirstOrder(){
        util.searchText("atest - Almond Flour")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.click(withId(R.id.btn_checkout))
        util.activityIs("CheckOutActivity")
        util.type(withId(R.id.et_firstname), "tester_first")
        util.type(withId(R.id.et_lastname), "tester_last")
        util.type(withId(R.id.et_address1), "123 fake street")
        util.click(withId(R.id.et_zipcode))
        util.type(withId(R.id.et_zipcode), "90245")
        util.scrollTo((withId(R.id.et_phone)))
        util.type(withId(R.id.et_phone), "5555555555")
        util.click(withId(R.id.btn_save))
        util.matches(allOf(withId(R.id.tv_order_detail_header), withText("Shipping")), isDisplayed())
        util.matches(allOf(withId(R.id.tv_order_detail_header), withText("Billing")), isDisplayed())
        util.matches(allOf(withId(R.id.tv_order_detail_header), withText("Summary")), isDisplayed())
        util.swipeUp(withId(R.id.tm_fragment_container))
        util.matches(allOf(withId(R.id.tv_order_detail_header), withText("Order (1) Item")), isDisplayed())
        util.click(withId(R.id.btn_place_order))
    }

    @Test
    fun checkoutZipCodeAutofill(){
        util.searchText("atest - Almond Flour")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.click(withId(R.id.btn_checkout))
        util.activityIs("CheckOutActivity")
        util.click(withId(R.id.et_zipcode))
        util.type(withId(R.id.et_zipcode), "90245")
        util.pressBack()
        util.matches(withId(R.id.et_city), withText("El Segundo"))
        util.matches(withId(R.id.et_state), withText("CA"))
    }

    @Test
    fun checkoutPhoneValidation(){
        util.searchText("atest - Almond Flour")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.click(withId(R.id.btn_checkout))
        util.activityIs("CheckOutActivity")
        util.scrollTo((withId(R.id.et_phone)))
        util.click(withId(R.id.et_phone))
        util.type(withId(R.id.et_phone), "5555555555")
        util.matches(withId(R.id.et_phone), withText("(555) 555-5555"))
    }

    @Test
    fun checkoutAddBilling(){
        util.searchText("atest - Almond Flour")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.click(withId(R.id.btn_checkout))
        util.activityIs("CheckOutActivity")
        util.type(withId(R.id.et_firstname), "tester_first")
        util.type(withId(R.id.et_lastname), "tester_last")
        util.type(withId(R.id.et_address1), "123 fake street")
        util.click(withId(R.id.et_zipcode))
        util.type(withId(R.id.et_zipcode), "90245")
        util.scrollTo((withId(R.id.et_phone)))
        util.type(withId(R.id.et_phone), "5555555555")
        util.click(withId(R.id.btn_save))
        util.click(hasSibling(allOf(withId(R.id.tv_order_detail_header), withText("Billing"))), withId(R.id.tv_change))
        util.activityIs("PaymentActivity")
        //util.matches(withId(R.id.tv_credit_card), withText("Visa - 1881 "))
        util.click(withId(R.id.fab_add))
        util.activityIs("DropInActivity")
    }

    @Test
    fun checkoutChangeShipping(){
        util.searchText("atest - Almond Flour")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.click(withId(R.id.btn_checkout))
        util.activityIs("CheckOutActivity")
        util.type(withId(R.id.et_firstname), "tester_first")
        util.type(withId(R.id.et_lastname), "tester_last")
        util.type(withId(R.id.et_address1), "123 fake street")
        util.click(withId(R.id.et_zipcode))
        util.type(withId(R.id.et_zipcode), "90245")
        util.scrollTo((withId(R.id.et_phone)))
        util.type(withId(R.id.et_phone), "5555555555")
        util.click(withId(R.id.btn_save))
        util.click(hasSibling(allOf(withId(R.id.tv_order_detail_header), withText("Billing"))), withId(R.id.tv_change))
        util.activityIs("AddressActivity")
    }

}
