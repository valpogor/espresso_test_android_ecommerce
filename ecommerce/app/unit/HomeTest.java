package com.ecommerce.app.unit;

import androidx.test.runner.AndroidJUnit4;
import com.ecommerce.core.models.Home;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
public class HomeTest extends BaseTestCase{
    private Home mHomeModel;
    private int mRequestCode;

    @Before
    @Override
    public void setUp() {
        super.setUp();

    }

    @Test
    public void getHome() throws ExecutionException, InterruptedException {

    }
}
