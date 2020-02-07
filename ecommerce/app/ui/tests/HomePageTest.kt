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

class HomePageTest {

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
    fun verifyHomePageHeader() {
        util.matches(withText("Ecommerce Market"), isDisplayed())
        util.matches(withId(R.id.menu_item_cart), isDisplayed())
        util.matches(withId(R.id.menu_item_search), isDisplayed())
        util.matches(withId(R.id.menu_item_scan), isDisplayed())

    }

    @Test
    fun verifyHomePageBanner() {
        util.matches(withId(R.id.iv_hero_banner_slide_item), isDisplayed())
        util.matches(withId(R.id.constraintLayout2), isDisplayed())
    }

    @Test
    fun verifyHomeShopByDiet() {
        util.matches(allOf(withId(R.id.tv_label), withText("Shop by diet")), isDisplayed())
        util.matches(withText("Genic"), isDisplayed())
        util.matches(withText("WHOLE30 APPROVED"), isDisplayed())
        util.matches(withText("PALEO"), isDisplayed())
//        util.swipeLeft(withId(R.id.rv_product))
//        util.matches(withText("GLUTEN FREE"), isDisplayed())
//        util.matches(withText("VEGAN"), isDisplayed())
//        util.matches(withText("ORGANIC"), isDisplayed())
//        util.matches(withText("RAW"), isDisplayed())
//        util.matches(withText("VEGETARIAN"), isDisplayed())
//        util.matches(withText("NON-GMO"), isDisplayed())
//        util.matches(withText("DAIRY FREE"), isDisplayed())
//        util.matches(withText("CRUELTY FREE"), isDisplayed())
//        util.matches(withText("BIODYNAMIC"), isDisplayed())
    }

    @Test
    fun verifyHomeGenicDiet() {
        util.click(withText("Genic"), isDisplayed())
        util.matches(withText("Genic"), isDisplayed())
    }

    @Test
    fun verifyHomeBrandsWeLove() {
        util.matches(allOf(withId(R.id.tv_label), withText("Brands we love")), isDisplayed())
//        util.click( util.getElementFromMatchAtPosition(withId(R.id.btn_add_to_cart), 0))
//        util.matches(withId(R.id.tv_item_added), withText("Item added to box"))
//        util.click( util.getElementFromMatchAtPosition(withId(R.id.tb_favorite), 0))
//        util.matches(withId(R.id.snackbar_text), withText("Added to Favorites"))
   }

}