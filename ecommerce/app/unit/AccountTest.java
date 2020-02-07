package com.ecommerce.app.unit;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.ecommerce.app.viewmodels.AccountViewModel;
import com.ecommerce.core.RequestCodes;
import com.ecommerce.core.models.Account;
import com.ecommerce.core.models.Address;
import com.ecommerce.core.models.CreditCard;
import com.ecommerce.core.models.Order;
import com.ecommerce.core.models.ResetPassword;
import com.ecommerce.core.models.WebLink;
import com.ecommerce.core.models.WishList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AccountTest extends BaseTestCase {
    private Context mContext;
    private Account mAccountModel;
    private Address.Lists mAddresses;
    private ResetPassword mResetPasswordModel;
    private WebLink mWebLinkModel;
    private WishList mWishListModel;
    private Order mOrderModel;
    private Order.Lists mOrders;
    private CreditCard.Lists mCreditCards;
    private AccountViewModel mViewModel;
    private int mRequestCode;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        mContext = InstrumentationRegistry.getTargetContext();
        mViewModel = new AccountViewModel(mContext, this);
    }

    @Test
    public void addAddress() throws ExecutionException, InterruptedException  {
        Address model = new Address();
        model.firstname = "Richard";
        model.lastname = "Robles";
        model.street1 = "100 Main Street";
        model.street2 = "#48";
        model.city = "Monterey Park";
        model.postcode = "91754";
        model.region_code = "CA";
        model.country_id = "US";
        model.telephone = "9999999999";
        model.fax = "9999999999";
        model.is_default_billing = false;
        model.is_default_shipping = false;
        model.region = "California";

        mViewModel.addAddress(model);
        mRequestCode = getResponse();
        mAddresses = (Address.Lists) mViewModel.getModel();

        assertNotNull(mAddresses);
        assertTrue((mAddresses.addresses.size() > 0));
        assertEquals(mRequestCode, RequestCodes.ACCOUNT_ADD_ADDRESS_REQUEST_CODE);
    }

    @Test
    public void addWishList() throws ExecutionException, InterruptedException {
        mViewModel.addWishList(4141, "product");
        mRequestCode = getResponse();
        mWishListModel = (WishList) mViewModel.getModel();

        assertNotNull(mWishListModel);
        assertTrue((mWishListModel.items.size() > 0));
    }

    @Test
    public void deleteAddress() throws ExecutionException, InterruptedException  {
        mViewModel.deleteAddress(730194);
        mRequestCode = getResponse();
        mAddresses = (Address.Lists) mViewModel.getModel();

        assertNotNull(mAddresses);
        assertTrue(mAddresses.success);
        assertEquals(mAddresses.addresses.size(), 0);
    }

    @Test
    public void deleteCreditCard() throws ExecutionException, InterruptedException {
        mViewModel.deleteCreditCard("4jd7vm");
        mRequestCode = getResponse();
        mCreditCards = (CreditCard.Lists) mViewModel.getModel();

        assertNotNull(mCreditCards);
        assertTrue(mCreditCards.success);
        assertEquals(mCreditCards.cards.size(), 0);
    }

    @Test
    public void deleteWishList() throws ExecutionException, InterruptedException {
        mViewModel.deleteWishList(4141);
        mRequestCode = getResponse();
        mWishListModel = (WishList) mViewModel.getModel();

        assertNotNull(mWishListModel);
        assertTrue(mWishListModel.success);
        assertEquals(mWishListModel.items.size(), 0);
    }

    @Test
    public void getAccount() throws ExecutionException, InterruptedException {
        mViewModel.getAccount();
        mRequestCode = getResponse();
        mAccountModel = (Account) mViewModel.getModel();

        assertNotNull(mAccountModel);
        assertTrue(mAccountModel.success);
    }

    @Test
    public void generateWebLink() throws ExecutionException, InterruptedException {
        mViewModel.generateWebLink("food");
        mRequestCode = getResponse();
        mWebLinkModel = (WebLink) mViewModel.getModel();

        assertNotNull(mWebLinkModel);
        assertNotNull(mWebLinkModel.url);
    }

    @Test
    public void getAddress() throws ExecutionException, InterruptedException  {
        mViewModel.getAddress();
        mRequestCode = getResponse();
        mAddresses = (Address.Lists) mViewModel.getModel();

        assertNotNull(mAddresses);
        assertTrue((mAddresses.addresses.size() > 0));
    }

    @Test
    public void getCreditCards() throws ExecutionException, InterruptedException {
        mViewModel.getCreditCards();
        mRequestCode = getResponse();
        mCreditCards = (CreditCard.Lists) mViewModel.getModel();

        assertNotNull(mCreditCards);
        assertTrue(mCreditCards.success);
    }

    @Test
    public void getOrder() throws ExecutionException, InterruptedException {
        mViewModel.getOrder(0);
        mRequestCode = getResponse();
        mOrderModel = (Order) mViewModel.getModel();

        assertNotNull(mOrderModel);
    }

    @Test
    public void getOrders() throws ExecutionException, InterruptedException {
        mViewModel.getOrders();
        mRequestCode = getResponse();
        mOrders = (Order.Lists) mViewModel.getModel();

        assertNotNull(mOrders);
    }

    @Test
    public void getWishList() throws ExecutionException, InterruptedException {
        mViewModel.getWishList();
        mRequestCode = getResponse();
        mWishListModel = (WishList) mViewModel.getModel();

        assertNotNull(mWishListModel);
        assertTrue((mWishListModel.items.size() > 0));
    }

    @Test
    public void register() throws ExecutionException, InterruptedException {
        Account model = new Account();
        model.firstname = "";
        model.lastname = "";
        model.email = "rrobles002@ecommerce.net";
        model.password = "";

        mViewModel.register(model);
        mRequestCode = getResponse();
        mAccountModel = (Account) mViewModel.getModel();

        assertNotNull(mAccountModel);
        assertNotNull(mAccountModel.session_id);
    }

    @Test
    public void resetPassword() throws ExecutionException, InterruptedException {
        mViewModel.resetPassword("rrobles001@ecommerce.net");
        mRequestCode = getResponse();
        mResetPasswordModel = (ResetPassword) mViewModel.getModel();

        assertNotNull(mResetPasswordModel);
        assertTrue(mResetPasswordModel.success);
    }

    @Test
    public void updateAccount() throws ExecutionException, InterruptedException {
        Account model = new Account();
        model.firstname = "Gissella";
        model.lastname = "Galvan";

        mViewModel.updateAccount(model);
        mRequestCode = getResponse();
        mAccountModel = (Account) mViewModel.getModel();

        assertNotNull(mAccountModel);
    }

    @Test
    public void updateAddress() throws ExecutionException, InterruptedException  {
        Address model = new Address();
        model.id = 730194;
        model.street1 = "110 Main Street";
        model.street2 = "#78";
        model.postcode = "92557";

        mViewModel.updateAddress(model);
        mRequestCode = getResponse();
        mAddresses = (Address.Lists) mViewModel.getModel();

        assertNotNull(mAddresses);
    }

    @Test
    public void updateCreditCard() throws ExecutionException, InterruptedException {
        mViewModel.updateCreditCard("4jd7vm", 0);
        mRequestCode = getResponse();
        mCreditCards = (CreditCard.Lists) mViewModel.getModel();

        assertNotNull(mCreditCards);
    }
}
