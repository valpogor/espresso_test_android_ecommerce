package com.ecommerce.app.ui.support

import android.content.Context
import androidx.test.InstrumentationRegistry

import androidx.test.runner.AndroidJUnit4
import com.ecommerce.app.account.AccountManager
import com.ecommerce.app.fragments.OrderReviewFragment
import com.ecommerce.app.managers.CartManager
import com.ecommerce.core.models.Account
import com.ecommerce.core.models.Address
import com.ecommerce.core.models.Product
import org.junit.runner.RunWith
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutionException

@RunWith(AndroidJUnit4::class)
class UserAccount {

    private var mContext : Context
    private var accountManager : AccountManager
    private var cartManager : CartManager
    private var orderManager : OrderReviewFragment

    init {
        mContext = InstrumentationRegistry.getTargetContext()
        accountManager = AccountManager(mContext)
        cartManager = CartManager.getInstance()
        orderManager = OrderReviewFragment.getInstance()
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun lead() {
        nonTrialLead()
        Thread.sleep(3000)
        accountManager.activateMembershipD2T()
    }

    fun nonTrialLead() {

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
        val formatted = current.format(formatter)

        val accountModel = Account()
        accountModel.firstname = ""
        accountModel.lastname = ""
        accountModel.email = "testerqa+$formatted@mailinator.com"
        accountModel.password = "tester"

        accountManager.register(accountModel)

        val addressModel = Address()
        addressModel.firstname = "Tester"
        addressModel.lastname = "Test"
        addressModel.street1 = "100 Main Street"
        addressModel.street2 = "#48"
        addressModel.city = "Monterey Park"
        addressModel.postcode = "91754"
        addressModel.region_code = "CA"
        addressModel.country_id = "US"
        addressModel.telephone = "9999999999"
        addressModel.fax = "9999999999"
        addressModel.is_default_billing = true
        addressModel.is_default_shipping = true
        addressModel.region = "California"
        accountManager.addAddress(addressModel)
        Thread.sleep(3000)

        accountManager.addPayment("fake-valid-visa-nonce")
        accountManager.updateCreditCard("4jd7vm", 0)
    }

    fun member(){
        lead()
        Thread.sleep(10000)


        val prodModel = Product()
        prodModel.id = 9494
        cartManager.addItem(prodModel, 1)
        Thread.sleep(5000)

    }

    fun clear() {
        AccountManager.clearData()
    }

}
