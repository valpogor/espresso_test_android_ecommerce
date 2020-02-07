package com.ecommerce.app.ui.tests

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
import org.junit.FixMethodOrder;
import org.junit.Test
import org.junit.runners.MethodSorters
import com.ecommerce.app.ui.support.screens.*
import org.hamcrest.CoreMatchers.*

@LargeTest
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeMenuTest {

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
    fun verifyHomeMenuLayout() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.matches(withContentDescription("Ecommerce Market"), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Home")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("My Account")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Deals")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Favorites")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Messages")), isDisplayed())

        util.matches(withId(R.id.tv_title), withText("Categories"))
        util.matches(allOf(withId(R.id.category_text), withText("Ecommerce Market Goods")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Food")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Meat & Seafood")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Wine")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Vitamins & Supplements")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Beauty")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Bath & Body")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Babies & Kids")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Home")), isDisplayed())
        util.swipeUp(withId(R.id.nav_menu_list))
        util.matches(allOf(withId(R.id.category_text), withText("Pet")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("New")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Gift Cards")), isDisplayed())

        util.matches(withId(R.id.tv_title), withText("Values"))
        util.matches(allOf(withId(R.id.category_text), withText("Gluten-Free")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Organic")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Paleo")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Raw")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Vegan")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Vegetarian")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Genic")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("AIP Diet")), isDisplayed())
        util.scrollTo(allOf(withId(R.id.category_text), withText("Give Us Feedback")))
        util.matches(allOf(withId(R.id.category_text), withText("Low FODMAP")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("All Values")), isDisplayed())

        util.matches(allOf(withId(R.id.category_text), withText("Redeem gift card")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Blog")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("About Ecommerce Market")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Give & Get $25")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Help")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Give Us Feedback")), isDisplayed())
    }

    @Test
    fun verifyHomeMenuLayoutGuest() {
        AccountManager.clearData()
        util.click(withId(R.id.tv_logout))
        util.click(withText("OK"))
        util.click(withId(R.id.tv_skip))
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.matches(withContentDescription("Ecommerce Market"), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Home")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("My Account")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Deals")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Favorites")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Messages")), isDisplayed())

        util.matches(withId(R.id.tv_title), withText("Categories"))
        util.matches(allOf(withId(R.id.category_text), withText("Ecommerce Market Goods")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Food")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Meat & Seafood")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Wine")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Vitamins & Supplements")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Beauty")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Bath & Body")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Babies & Kids")), isDisplayed())
        util.swipeUp(withId(R.id.nav_menu_list))
        util.matches(allOf(withId(R.id.category_text), withText("Home")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Pet")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("New")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Gift Cards")), isDisplayed())

        util.matches(withId(R.id.tv_title), withText("Values"))
        util.matches(allOf(withId(R.id.category_text), withText("Gluten-Free")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Organic")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Paleo")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Raw")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Vegan")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Vegetarian")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Genic")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("AIP Diet")), isDisplayed())
        util.scrollTo(allOf(withId(R.id.category_text), withText("Give Us Feedback")))
        util.matches(allOf(withId(R.id.category_text), withText("Low FODMAP")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("All Values")), isDisplayed())

        util.matches(allOf(withId(R.id.category_text), withText("Redeem gift card")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Blog")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("About Ecommerce Market")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Give & Get $25")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Help")), isDisplayed())
        util.matches(allOf(withId(R.id.category_text), withText("Give Us Feedback")), isDisplayed())
    }


    @Test
    fun verifyMenuMyAccountForLead() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.matches(withText("My Account"), isDisplayed())

    }

    @Test
    fun verifyMenuMyAccountForGuest() {
        util.click(withId(R.id.tv_logout))
        util.click(withText("OK"))
        util.click(withId(R.id.tv_skip))
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("My Account")))
        util.click(allOf(withId(R.id.tv_login), withText("Sign in")))

    }

        @Test
    fun verifyMenuDeals() {
            util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
            util.click(allOf(withId(R.id.category_text), withText("Deals")))
            util.matches(allOf(withId(R.id.tv_header), withText("Extra savings")), isDisplayed())
        }

    @Test
    fun verifyMenuFavorite() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("Favorites")))
        util.matches(withText("Favorites"), isDisplayed())

    }

    @Test
    fun verifyMenuMessages() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.click(allOf(withId(R.id.category_text), withText("Messages")))
        util.matches(withText("Discover"), isDisplayed())

    }

    @Test
    fun verifyMenuCategories() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.scrollTo(allOf(withId(R.id.category_text), withText("New")))
        util.click(allOf(withId(R.id.category_text), withText("Pet")))
        util.matches(withText("Pet"), isDisplayed())
        util.click(allOf(withId(R.id.tv_category_name), withText("atest - Cat Toys & Accessories")))
        util.matches(withText("atest - Cat Toys & Accessories"), isDisplayed())

    }


    @Test
    fun verifyMenuGlutenFree() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Gluten-Free")))
        util.matches(withText("Gluten-Free"), isDisplayed())

    }

    @Test
    fun verifyMenuOrganic() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Organic")))
        util.matches(withText("Organic"), isDisplayed())
    }

    @Test
    fun verifyMenuPaleo() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Paleo")))
        util.matches(withText("Paleo"), isDisplayed())
    }

    @Test
    fun verifyMenuRaw() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Raw")))
        util.matches(withText("Raw"), isDisplayed())
    }

    @Test
    fun verifyMenuVegan() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Vegan")))
        util.matches(withText("Vegan"), isDisplayed())
    }

    @Test
    fun verifyMenuVegetarian() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Vegetarian")))
        util.matches(withText("Vegetarian"), isDisplayed())
    }

    @Test
    fun verifyMenuGenic() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Genic")))
        util.matches(withText("Genic"), isDisplayed())
    }

    @Test
    fun verifyMenuAipDiet() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("AIP Diet")))
        util.matches(withText("AIP Diet"), isDisplayed())
    }

    @Test
    fun verifyMenuLowFODMAP() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Low FODMAP")))
        util.matches(withText("Low FODMAP"), isDisplayed())
    }

    @Test
    fun verifyMenuAllValues() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("All Values")))
        util.matches(withText("All Values"), isDisplayed())
    }

    @Test
    fun verifyMenuRedeemGiftCard() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Redeem gift card")))
        util.matches(withText("Redeem your gift!"), isDisplayed())
    }

    @Test
    fun verifyMenuAboutecommerce() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("About Ecommerce Market")))
        util.matches(withText("About Ecommerce Market"), isDisplayed())
    }

    @Test
    fun verifyMenuGiveGet25() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Give & Get $25")))
        util.matches(allOf(withId(R.id.tv_Ecommerce_cash_balance), withText("Ecommerce Cash Balance: $0.00")), isDisplayed())
    }

    @Test
    fun verifyMenuGiveUsFeedback() {
        util.click(isDescendantOfA(withId(R.id.tm_tool_bar)), withContentDescription("Ecommerce Market"))
        util.swipeUp(withId(R.id.nav_menu_list))
        util.click(allOf(withId(R.id.category_text), withText("Give Us Feedback")))
        util.matches(allOf(withId(R.id.greeting_title), withText("Hello!")), isDisplayed())
    }
}


