package com.ecommerce.app.unit;

import androidx.test.runner.AndroidJUnit4;
import com.ecommerce.app.viewmodels.OrderViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class OrderTest extends BaseTestCase {
    private OrderViewModel mViewModel;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        mViewModel = new OrderViewModel(mContext, this);
    }

    @Test
    public void placeOrder() {
        //mViewModel.placeOrder(null, null, "", "", "", "");
    }
}
