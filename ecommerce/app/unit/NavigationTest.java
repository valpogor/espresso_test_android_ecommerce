package com.ecommerce.app.unit;

import androidx.test.runner.AndroidJUnit4;
import com.ecommerce.app.viewmodels.NavigationViewModel;
import com.ecommerce.core.models.Navigation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class NavigationTest extends BaseTestCase {
    private Navigation mNavigationModel;
    private NavigationViewModel mViewModel;
    private int mRequestCode;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        mViewModel = new NavigationViewModel(mContext, this);
    }

    @Test
    public void getNavigation() throws ExecutionException, InterruptedException {
        mViewModel.getNavigation();
        mRequestCode = getResponse();
        mNavigationModel = (Navigation) mViewModel.getModel();

        assertNotNull(mNavigationModel);
    }
}
