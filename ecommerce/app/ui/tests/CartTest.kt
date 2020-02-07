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
class CartTest {

    @Rule
    @JvmField

    var activityTestRule = ActivityTestRule(SplashActivity::class.java, false, false)
    var userAccount = UserAccount()
    var util = Utility()
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
    fun cartAddProductFromPLPBrand(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.matches(withId(R.id.tv_item_added), withText("Item added to box"))
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.matches(withId(R.id.rl_container), hasDescendant(withText("milk atest - Almond Flour")))
    }

    @Test
    fun cartAddProductFromPLPSearch(){
        util.searchText("atest - Almond Flour")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.matches(withId(R.id.tv_item_added), withText("Item added to box"))
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.matches(withId(R.id.rl_container), hasDescendant(withText("milk atest - Almond Flour")))
    }

    @Test
    fun cartAddProductFromPDP(){
        util.searchText("milk")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.iv_product))
        util.click(withId(R.id.add_to_cart), isDescendantOfA(withId(R.id.add_to_cart)))
        util.click(withId(R.id.coordinator))
        util.activityIs("CartActivity")
        util.matches(withId(R.id.rl_container), hasDescendant(withText("milk atest - Almond Flour")))
    }

    @Test
    fun cartValidateSingleProductForLead(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.matches(withId(R.id.rl_container), hasDescendant(allOf(withId(R.id.tv_product_name), withText("milk atest - Almond Flour"))))
        util.matches(withId(R.id.rl_container), hasDescendant(allOf(withId(R.id.tv_savings), withText("$12.99 $7.99"))))
        util.matches(withId(R.id.rl_container), hasDescendant(allOf(withId(R.id.tv_price), withText("$7.99"))))
        util.matches(withId(R.id.rl_container), hasDescendant(allOf(withId(R.id.quantity), hasDescendant(withText("1")))))
    }

    @Test
    fun cartValidateMultipleProductForLead(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.click(withContentDescription("Navigate up"))
        plp.clickProductCardAdd("milk atest - atest - Organic Coconut Wraps, Original")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.matches(allOf(withId(R.id.rl_container), hasDescendant(withText("milk atest - Almond Flour"))), hasDescendant(allOf(withId(R.id.tv_product_name), withText("milk atest - Almond Flour"))))
        util.matches(allOf(withId(R.id.rl_container), hasDescendant(withText("milk atest - Almond Flour"))), hasDescendant(allOf(withId(R.id.tv_savings), withText("$12.99 $7.99"))))
        util.matches(allOf(withId(R.id.rl_container), hasDescendant(withText("milk atest - Almond Flour"))), hasDescendant(allOf(withId(R.id.tv_price), withText("$7.99"))))
        util.matches(allOf(withId(R.id.rl_container), hasDescendant(withText("milk atest - Almond Flour"))), hasDescendant(allOf(withId(R.id.quantity), hasDescendant(withText("1")))))
        util.matches(allOf(withId(R.id.rl_container), hasDescendant(withText("milk atest - atest - Organic Coconut Wraps, Original"))), hasDescendant(allOf(withId(R.id.tv_product_name), withText("milk atest - atest - Organic Coconut Wraps, Original"))))
        util.matches(allOf(withId(R.id.rl_container), hasDescendant(withText("milk atest - atest - Organic Coconut Wraps, Original"))), hasDescendant(allOf(withId(R.id.tv_savings), withText("$9.99 $7.49"))))
        util.matches(allOf(withId(R.id.rl_container), hasDescendant(withText("milk atest - atest - Organic Coconut Wraps, Original"))), hasDescendant(allOf(withId(R.id.tv_price), withText("$7.49"))))
        util.matches(allOf(withId(R.id.rl_container), hasDescendant(withText("milk atest - atest - Organic Coconut Wraps, Original"))), hasDescendant(allOf(withId(R.id.quantity), hasDescendant(withText("1")))))
    }

    @Test
    fun cartMinusPlusButton(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.matches(withId(R.id.rl_container), hasDescendant(allOf(withId(R.id.quantity), hasDescendant(withText("1")))))
        util.click(withId(R.id.tm_plus_button))
        util.matches(withId(R.id.rl_container), hasDescendant(allOf(withId(R.id.quantity), hasDescendant(withText("2")))))
        util.click(withId(R.id.tm_minus_button))
        util.matches(withId(R.id.rl_container), hasDescendant(allOf(withId(R.id.quantity), hasDescendant(withText("1")))))
        util.click(withId(R.id.tm_minus_button))
        util.matches(withId(R.id.tv_label), withText("Your box is empty"))
    }

    @Test
    fun cartDecrementTilEmpty(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.click(withId(R.id.tm_minus_button))
        util.matches(allOf(withId(R.id.tv_label), withText("Your box is empty")), isDisplayed())
    }

    @Test
    fun cartRemoveTilEmpty(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.click(withId(R.id.iv_delete))
        util.matches(allOf(withId(R.id.tv_label), withText("Your box is empty")), isDisplayed())
    }

    @Test
    fun cartInvalidPromoCode(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.swipeUp(withId(R.id.rv_cart))
        util.type(withId(R.id.et_promo), "auto33disacountmax16")
        cart.clickCartSection(withId(R.id.promo_code_container), withId(R.id.btn_apply_coupon))
        util.matches(withText("Sorry, this coupon is not available anymore."), isDisplayed())
    }

    @Test
    fun cartValidPromoCode(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.swipeUp(withId(R.id.rv_cart))
        util.type(withId(R.id.et_promo), "WELCOME")
        cart.clickCartSection(withId(R.id.promo_code_container), withId(R.id.btn_apply_coupon))
        util.matches(allOf(withId(R.id.et_promo)), isDisplayed())
    }

    @Test
    fun cartDeletePromoCode() {
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.swipeUp(withId(R.id.rv_cart))
        util.type(withId(R.id.et_promo), "WELCOME")
        cart.clickCartSection(withId(R.id.promo_code_container), withId(R.id.btn_apply_coupon))
        util.matches(allOf(withId(R.id.et_promo)), isDisplayed())
        util.click(withId(R.id.btn_delete_coupon))
        util.matches(allOf(withId(R.id.et_promo)), isDisplayed())
    }

    @Test
    fun cartInvalidGiftCard(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.swipeUp(withId(R.id.rv_cart))
        util.type(withId(R.id.et_gift_card), "InvalidCard")
        cart.clickCartSection(withId(R.id.gift_card_container), withId(R.id.btn_apply_gift_card))
        util.matches(withText("Gift card failed to apply"), isDisplayed())
    }

        @Test
    fun cartValidGiftCard(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.swipeUp(withId(R.id.rv_cart))
        util.type(withId(R.id.et_gift_card), "6MM3YIF7")
        cart.clickCartSection(withId(R.id.gift_card_container), withId(R.id.btn_apply_gift_card))
        util.matches(allOf(withId(R.id.et_gift_card)), isDisplayed())
    }

        @Test
    fun cartDeleteValidGiftCard(){
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.activityIs("CartActivity")
        util.swipeUp(withId(R.id.rv_cart))
        util.type(withId(R.id.et_gift_card), "6MM3YIF7")
        cart.clickCartSection(withId(R.id.gift_card_container), withId(R.id.btn_apply_gift_card))
        util.matches(allOf(withId(R.id.et_gift_card)), isDisplayed())
        util.click(withId(R.id.btn_delete_gift_card))
        util.matches(allOf(withId(R.id.et_gift_card)), isDisplayed())
    }
}
