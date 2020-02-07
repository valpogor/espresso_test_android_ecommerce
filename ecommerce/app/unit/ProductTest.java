package com.ecommerce.app.unit;

import androidx.test.runner.AndroidJUnit4;
import com.ecommerce.app.viewmodels.ProductViewModel;
import com.ecommerce.core.models.Product;
import com.ecommerce.core.models.Reminder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ProductTest extends BaseTestCase {
    private Product.Lists mProducts;
    private Product mProductModel;
    private Reminder mReminderModel;
    private ProductViewModel mViewModel;
    private int mRequestCode;

    @Before
    @Override
    public void setUp() {
        super.setUp();

        mViewModel = new ProductViewModel(mContext, this);
    }

    @Test
    public void addReview() throws ExecutionException, InterruptedException {
        mViewModel.addReview(4706, 3, "Test Description", "Test Title", "rrobles");
        mRequestCode = getResponse();
        mProductModel = (Product) mViewModel.getModel();

        assertNotNull(mProductModel);
        assertTrue(mProductModel.success);
    }

    @Test
    public void deleteReview() throws ExecutionException, InterruptedException {
        mViewModel.deleteReview(4706);
        mRequestCode = getResponse();
        mProductModel = (Product) mViewModel.getModel();

        assertNotNull(mProductModel);
        assertTrue(mProductModel.success);
    }

    @Test
    public void getProducts() throws ExecutionException, InterruptedException {
        Map<String, String> filters = new HashMap<>();
        filters.put("categories", "645");

        Map<String, String> sorts = new HashMap<>();
        sorts.put("position_category_645", "asc");

        //mViewModel.getProducts(filters, sorts, 1, 10);
        mRequestCode = getResponse();
        mProducts = (Product.Lists) mViewModel.getModel();

        assertNotNull(mProducts);
        assertTrue((mProducts.total != 0));
    }

    @Test
    public void getProduct() throws ExecutionException, InterruptedException {
        mViewModel.getProduct(4706);
        mRequestCode = getResponse();
        mProductModel = (Product) mViewModel.getModel();

        assertNotNull(mProductModel);
        assertTrue((mProductModel.id == 4706));
    }

    @Test
    public void setReminders() throws ExecutionException, InterruptedException {
        mViewModel.setReminders(new int[] {4706, 4141});
        mRequestCode = getResponse();
        mReminderModel = (Reminder) mViewModel.getModel();

        assertNotNull(mReminderModel);
        assertTrue((mReminderModel.reminders_created.size() > 0));
    }
}
