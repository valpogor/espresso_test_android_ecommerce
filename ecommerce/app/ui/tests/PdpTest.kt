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
class PdpTest {

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
    fun pdpValidateView() {
        util.searchText("atest - Almond Flour")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.iv_product))
        util.activityIs("ProductDetailActivity")
        util.matches(withId(R.id.tv_title), withText("milk atest - Almond Flour"))
        util.matches(withId(R.id.tv_short_description), withText("16 oz bag"))
        util.matches(withId(R.id.rl_favorite_container), isDisplayed())
        util.matches(withId(R.id.rb_product), isDisplayed())
        util.matches(withId(R.id.tv_review_count), isDisplayed())
        util.matches(withId(R.id.tv_price), withText("$7.99"))
        util.matches(withId(R.id.tv_msrp), withText("$12.99 38% Off"))
        util.scrollTo(withId(R.id.tab_product))
        util.matches(withContentDescription("Details"), isDescendantOfA(withId(R.id.tab_product)))
        util.matches(withContentDescription("Reviews"), isDescendantOfA(withId(R.id.tab_product)))
        util.matches(withContentDescription("Nutrition"), isDescendantOfA(withId(R.id.tab_product)))
        util.matches(withContentDescription("Related"), isDescendantOfA(withId(R.id.tab_product)))
        util.matches(withId(R.id.add_to_cart), isDisplayed())
    }

    @Test
    fun pdpValidateDetailsTab(){
        util.searchText("atest - Almond Flour")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.iv_product))
        util.scrollTo(withId(R.id.tab_product))
        util.click(isDescendantOfA(withId(R.id.tab_product)), withContentDescription("Details"))
        util.scrollTo(withId(R.id.tv_ingredients))
        util.matches(withId(R.id.tv_ingredients), withText("Blanched Almond Flour."))
        util.scrollTo(withId(R.id.rl_manufacturer))
        util.matches(withId(R.id.tv_description_label), withText("Why You'll Love It"))
        util.matches(withId(R.id.tv_manufacturer), withText("milk"))
    }

    @Test
    fun pdpAddEmptyReview() {
        util.searchText("atest - Almond Flour")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.iv_product))
        util.scrollTo(withId(R.id.tab_product))
        util.click(isDescendantOfA(withId(R.id.tab_product)), withContentDescription("Reviews"))
        util.scrollTo(withId(R.id.review_indicator_link))
        util.click(withId(R.id.review_indicator_link))
        util.click(withId(R.id.btn_submit))
        util.matches(withId(R.id.tv_error_ratings), withText("Please select a rating"))
        util.matches(allOf(withId(R.id.textinput_error), isDescendantOfA(withId(R.id.til_review_title))), withText("This is a required field"))
        util.matches(allOf(withId(R.id.textinput_error), isDescendantOfA(withId(R.id.til_review_body))), withText("This is a required field"))
    }

    @Test
    fun pdpValidateNutritionTab() {
        util.searchText("atest - Almond Flour")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.iv_product))
        util.scrollTo(withId(R.id.tab_product))
        util.click(isDescendantOfA(withId(R.id.tab_product)), withContentDescription("Nutrition"))
    }

    @Test
    fun pdpValidateRelatedTab() {
        util.searchText("atest - Almond Flour")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.iv_product))
        util.scrollTo(withId(R.id.tab_product))
        util.click(isDescendantOfA(withId(R.id.tab_product)), withContentDescription("Related"))
        util.scrollTo(allOf(withId(R.id.tv_label), withText("Members Also Viewed")))
        util.matches(allOf(withId(R.id.tv_label), withText("Members Also Bought")), isDisplayed())
        util.matches(allOf(withId(R.id.rv_product_list), isDescendantOfA(allOf(withId(R.id.tm_view_holder_root), hasDescendant(withText("Members Also Bought"))))), hasMinimumChildCount(3))
        util.scrollTo(allOf(withId(R.id.rv_product_list), isDescendantOfA(allOf(withId(R.id.tm_view_holder_root), hasDescendant(withText("Members Also Viewed"))))))
        util.matches(allOf(withId(R.id.rv_product_list), isDescendantOfA(allOf(withId(R.id.tm_view_holder_root), hasDescendant(withText("Members Also Viewed"))))), hasMinimumChildCount(3))
    }

//    @Test
//    fun pdpIncrementDecrement() {
//        util.searchText("atest - Almond Flour")
//        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.iv_product))
//        util.click(isDescendantOfA(withId(R.id.ll_add_to_cart_container)), withId(R.id.add_to_cart), withText(containsString("Add to box")))
//        util.click(withId(R.id.touch_outside))
//        util.click(withContentDescription("Navigate up"))
//        util.matches(withId(R.id.tv_quantity), withText("1"))
//        util.click(isDescendantOfA(withId(R.id.ll_add_to_cart_container)), withId(R.id.btn_increment))
//        util.click(withId(R.id.touch_outside))
//        util.matches(withId(R.id.tv_quantity), withText("2"))
//
//        Thread.sleep(10000000)
//    }


}
