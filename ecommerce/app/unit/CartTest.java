package com.ecommerce.app.unit;

import androidx.test.runner.AndroidJUnit4;

import com.ecommerce.app.viewmodels.CartViewModel;
import com.ecommerce.core.models.Address;
import com.ecommerce.core.models.Cart;
import com.ecommerce.core.models.Product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CartTest extends BaseTestCase {
    private Cart mCartModel;
    private CartViewModel mViewModel;
    private int mRequestCode;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        mViewModel = new CartViewModel(mContext, this);
    }

    public void addGiftCard() throws ExecutionException, InterruptedException {
        Product giftCard = new Product();
        giftCard.id = 5333;
        giftCard.gc_amount = 0;
        giftCard.gc_custom_amount = 50.00;
        giftCard.gc_message = "Test Message";
        giftCard.gc_recipient_email = "test-recipient@test.com";
        giftCard.gc_recipient_name = "Test Recipient";
        giftCard.gc_sender_email = "test-sender@test.com";
        giftCard.gc_sender_name = "Test Sender";

        mViewModel.addGiftCard(giftCard, 1);
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
        assertTrue((mCartModel.items.size() > 0));
    }

    @Test
    public void addItem() throws ExecutionException, InterruptedException {
        mViewModel.addItem(4706, 1);
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
        assertTrue((mCartModel.items.size() > 0));
    }

    @Test
    public void addShipping() throws ExecutionException, InterruptedException {
        Address model = new Address();
        model.firstname = "Richard";
        model.lastname = "Robles";
        model.street1 = "10067 Thrasher Circle";
        model.city = "Moreno Valley";
        model.postcode = "92557";
        model.region_id = 12;
        model.country_id = "US";
        model.telephone = "9999999999";

        mViewModel.addShipping("premiumrateFedex-_Ground", model);
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
        assertTrue((mCartModel.items.size() > 0));
    }

    @Test
    public void addShippingWithId() throws ExecutionException, InterruptedException {
        mViewModel.addShipping("premiumrateFedex-_Ground", 730196);
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
        assertTrue((mCartModel.items.size() > 0));
    }

    @Test
    public void addToWishList() throws ExecutionException, InterruptedException {
        mViewModel.addToWishList();
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
        assertTrue((mCartModel.items.size() > 0));
    }

    @Test
    public void applyCoupon() throws ExecutionException, InterruptedException {
        mViewModel.applyCoupon("iostest");
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
    }

    @Test
    public void applyGiftCard() throws ExecutionException, InterruptedException {
        mViewModel.applyGiftCard("");
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
    }

    @Test
    public void deleteCoupon() throws ExecutionException, InterruptedException {
        mViewModel.deleteCoupon("iostest");
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
    }

    @Test
    public void deleteGiftCard() throws ExecutionException, InterruptedException {
        mViewModel.deleteGiftCard("");
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
    }

    @Test
    public void deleteItem() throws ExecutionException, InterruptedException {
        mViewModel.deleteItem(13568798);
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
    }

    @Test
    public void getCart() throws ExecutionException, InterruptedException {
        mViewModel.getCart();
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
    }

    @Test
    public void reorder() throws ExecutionException, InterruptedException {
        mViewModel.reorder(0);
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
    }

    @Test
    public void updateCart() throws ExecutionException, InterruptedException {
        mViewModel.updateCart(13568787, 1, false);
        mRequestCode = getResponse();
        mCartModel = (Cart) mViewModel.getModel();

        assertNotNull(mCartModel);
        assertTrue(mCartModel.success);
    }
}
