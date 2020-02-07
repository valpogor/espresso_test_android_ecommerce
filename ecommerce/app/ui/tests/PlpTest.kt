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
class PlpTest {

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
    fun plpVerifyBrandHeader(){
        util.searchText("milk")
        util.matches(withId(R.id.tm_tool_bar), hasDescendant(withText("milk")))
    }

    @Test
    fun plpFilter(){
        util.searchText("milk")
        util.click(withId(R.id.tv_filter), withText("Filter"))
    }

    @Test
    fun plpSearchBrand() {
        util.searchText("milk")
        util.matches(withId(R.id.btn_add_to_cart), isDisplayed())
    }

    @Test
    fun plpClickProductImage(){
        util.searchText("milk")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.iv_product))
        util.matches(withId(R.id.tv_title), withText("milk atest - Almond Flour"))
    }

    @Test
    fun plpClickProductDescription(){
        util.searchText("milk")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.iv_product))
        util.matches(withId(R.id.tv_product_title), withText("milk atest - Almond Flour"))
    }

    @Test
    fun plpIncrementDecrement() {
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.click(withContentDescription("Navigate up"))
        plp.verifyProductCart("milk atest - Almond Flour", withId(R.id.tv_quantity), "1")
        util.matches(withId(R.id.text), withText("1"))
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.btn_increment))
        util.click(withId(R.id.touch_outside))
        util.click(withContentDescription("Navigate up"))
        plp.verifyProductCart("milk atest - Almond Flour", withId(R.id.tv_quantity), "2")
        util.matches(withId(R.id.text), withText("2"))
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.btn_decrement))
        plp.verifyProductCart("milk atest - Almond Flour", withId(R.id.tv_quantity), "1")
        util.matches(withId(R.id.text), withText("1"))
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.btn_decrement))
        plp.verifyProductCart("milk atest - Almond Flour", withId(R.id.button), "Add")
    }

    @Test
    fun plpAddToFavoriteToolbar() {
        util.searchText("milk")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.tb_favorite))
        util.matches(withId(R.id.snackbar_text), withText("Added to Favorites"))
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.tb_favorite))
        util.matches(withId(R.id.snackbar_text), withText("Removed from Favorites"))
    }

    @Test
    fun plpAddToFavorite() {
        util.searchText("milk")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.tb_favorite))
        val elemBefore: ViewInteraction = onView(CoreMatchers.allOf(
                util.getElementFromMatchAtPosition(CoreMatchers.allOf(withId(R.id.tv_product_title)), 0),
                isDisplayed()))
        val before = util.getText(elemBefore)
        util.click(withContentDescription("Navigate up"))
        util.click(withContentDescription("Collapse"))
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(withId(R.id.vp_indicator))
        val elemAfter: ViewInteraction = onView(withId(R.id.tv_product_title))
        var after = util.getText(elemAfter)
        before.equals(after,true)
    }

    @Test
    fun plpRemoveFromFavorite() {
        util.searchText("milk")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.tb_favorite))
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.tb_favorite))
        util.click(withContentDescription("Navigate up"))
        util.click(withContentDescription("Collapse"))
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(withId(R.id.vp_indicator))
        onView(isRoot()).perform(util.waitFor(5000))

        onView(Matchers.allOf(withId(R.id.tv_message), withText("Your favorites list is currently empty."),
                isDisplayed())).check(matches(withText("Your favorites list is currently empty.")))
                }

    @Test
    fun plpTMBrand() {
        util.searchText("Ecommerce market")
        util.click(withId(R.id.tv_filter))
        util.click(withId(R.id.title2), withContentDescription("Brand"))
        util.matches(withId(R.id.title), withText("Ecommerce Market"))
    }

    @Test
    fun plpVerifySale() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(withId(R.id.category_text), withText("Deals"))
        deals.clickDealsCardExtraSavings("Extra 10% Off Tutti Gourmet", withId(R.id.btn_view_deals))
        util.waitFor(10000)
        util.matches(withId(R.id.tv_product_primary_tag), withText("Sale"))
    }

    @Test
    fun plpOutOfStock() {
    }

    @Test
    fun plpAddProductToCart() {
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.matches(withId(R.id.tv_item_added), withText("Item added to box"))
        util.click(withId(R.id.touch_outside))
        util.click(withContentDescription("Navigate up"))
        util.matches(withId(R.id.text), withText("1"))
    }

    @Test
    fun plpDeleteProductFromCart() {
        util.searchText("milk")
        plp.clickProductCardAdd("milk atest - Almond Flour")
        util.click(withId(R.id.touch_outside))
        util.click(withContentDescription("Navigate up"))
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.btn_decrement))
        util.click(withId(R.id.btn_decrement), withContentDescription("Remove item"))
        util.matches(withId(R.id.btn_add_to_cart), isDisplayed())
    }

    @Test
    fun plpAddToFavoritePopUp() {
        util.searchText("milk")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.tb_favorite))
        util.matches(withId(R.id.snackbar_text), withText("Added to Favorites"))
    }

    @Test
    fun plpRemoveFromFavoritePopUp() {
        util.searchText("milk")
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.tb_favorite))
        plp.clickProductCard("milk atest - Almond Flour", withId(R.id.tb_favorite))
        util.matches(withId(R.id.snackbar_text), withText("Removed from Favorites"))
    }

    @Test
    fun plpVerifyOptionsAvailable() {
        util.searchText("milk")
        util.matches(withId(R.id.tv_product_primary_tag), withText("Options available"))

    }

    @Test
    fun plpVerifyEcommerceCash() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(withId(R.id.category_text), withText("Deals"))
        util.click(withText("Ecommerce CASH"))
        deals.clickDealsCardEcommerceCash("Earn 10% Ecommerce Cash Back on Charlotte's Web", withId(R.id.btn_view_deals))
//        util.verifyTextByIdPosition(withId(R.id.tv_product_primary_tag), "10% Ecommerce Cash", 1, 1)
        util.click(withId(R.id.category_text), withText("Deals"))
        deals.clickDealsCardExtraSavings("Extra 10% Off Tutti Gourmet", withId(R.id.btn_view_deals))
    }

    @Test
    fun plpVerifySortLowToHigh() {
        util.searchText("milk")
        util.click(withId(R.id.tv_sort))
        onView(isRoot()).perform(util.waitFor(2000))
        util.click(withText("Price Low to High"))
        util.click(withText("OK"))
        onView(isRoot()).perform(util.waitFor(2000))
        val priceText: ViewInteraction = onView(CoreMatchers.allOf(util.getElementFromMatchAtPosition(
                CoreMatchers.allOf(withId(R.id.tv_product_price)), 0), isDisplayed()))
        var price = util.getText(priceText)
        Assert.assertEquals("$0.00", price)
    }

    @Test
    fun plpVerifySortHighToLow() {
        util.searchText("milk")
        util.click(withId(R.id.tv_sort))
        onView(isRoot()).perform(util.waitFor(2000))
        util.click(withText("Price High to Low"))
        util.click(withText("OK"))
        onView(isRoot()).perform(util.waitFor(2000))
        val priceText: ViewInteraction = onView(CoreMatchers.allOf(util.getElementFromMatchAtPosition(
                CoreMatchers.allOf(withId(R.id.tv_product_price)), 0), isDisplayed()))
        var price = util.getText(priceText)
        Assert.assertNotEquals("$0.00", price)
    }

    @Test
    fun plpVerifySortMostPopular() {
        util.searchText("milk")
        util.click(withId(R.id.tv_sort))
        onView(isRoot()).perform(util.waitFor(2000))
        util.click(withText("Most Popular"))
        util.click(withText("OK"))
        onView(isRoot()).perform(util.waitFor(2000))
        val prodText: ViewInteraction = onView(CoreMatchers.allOf(util.getElementFromMatchAtPosition(
                CoreMatchers.allOf(withId(R.id.tv_product_title)), 0), isDisplayed()))
        var text = util.getText(prodText)
        Assert.assertEquals("milk atest - Build Your Own Meat & Seafood Box", text)
    }

    @Test
    fun plpVerifySortTopRated() {
        util.searchText("milk")
        util.click(withId(R.id.tv_sort))
        onView(isRoot()).perform(util.waitFor(2000))
        util.click(withText("Top Rated"))
        util.click(withText("OK"))
        onView(isRoot()).perform(util.waitFor(2000))
        val prodText: ViewInteraction = onView(CoreMatchers.allOf(util.getElementFromMatchAtPosition(
                CoreMatchers.allOf(withId(R.id.tv_product_title)), 0), isDisplayed()))
        var text = util.getText(prodText)
        Assert.assertEquals("milk atest - Build Your Own Meat & Seafood Box", text)
    }

    @Test
    fun plpVerifySortNewestArrivals() {
        util.searchText("milk")
        util.click(withId(R.id.tv_sort))
        onView(isRoot()).perform(util.waitFor(2000))
        util.click(withText("Newest Arrivals"))
        util.click(withText("OK"))
        onView(isRoot()).perform(util.waitFor(2000))
        val prodText: ViewInteraction = onView(CoreMatchers.allOf(util.getElementFromMatchAtPosition(
                CoreMatchers.allOf(withId(R.id.tv_product_title)), 1), isDisplayed()))
        var text = util.getText(prodText)
        Assert.assertEquals("milk atest - Build Your Own All Red Half Case of Wine", text)
    }

    @Test
    fun plpVerifySortFeatured() {
        util.searchText("milk")
        util.click(withId(R.id.tv_sort))
        onView(isRoot()).perform(util.waitFor(2000))
        util.click(withText("Featured"))
        util.click(withText("OK"))
        onView(isRoot()).perform(util.waitFor(2000))
        val prodText: ViewInteraction = onView(CoreMatchers.allOf(util.getElementFromMatchAtPosition(
                CoreMatchers.allOf(withId(R.id.tv_product_title)), 0), isDisplayed()))
        var text = util.getText(prodText)
        Assert.assertEquals("milk Magic Sponge Cloth, 3-pack", text)
    }
}
