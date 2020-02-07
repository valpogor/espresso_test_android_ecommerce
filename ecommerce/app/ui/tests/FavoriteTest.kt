package com.ecommerce.app.ui.tests

import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ecommerce.app.R
import org.junit.*
import org.junit.runner.RunWith
import com.ecommerce.app.activities.SplashActivity
import com.ecommerce.app.account.AccountManager
import com.ecommerce.app.ui.support.*
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@LargeTest
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class FavoriteTest {

    @Rule
    @JvmField

    var activityTestRule = ActivityTestRule(SplashActivity::class.java, false, false)
    var userAccount = UserAccount()
    var util = Utility()

    @Before
    fun setUp() {
        AccountManager.clearData()
        userAccount.lead()
        activityTestRule.launchActivity(null)
    }

    @Test
    fun verify1emptyProductFavoriteElements() {
        util.click(ViewMatchers.isDescendantOfA(ViewMatchers.withId(R.id.tm_tool_bar)), ViewMatchers.withContentDescription("Ecommerce Market"))
        util.click(ViewMatchers.withId(R.id.vp_indicator))
        util.verifyAllElemInEmptyFavorite()
    }
}
