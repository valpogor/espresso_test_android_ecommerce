package com.ecommerce.app.unit;

import androidx.test.runner.AndroidJUnit4;

import com.ecommerce.app.viewmodels.DirectoryViewModel;
import com.ecommerce.core.models.Region;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DirectoryTest extends BaseTestCase {
    private Region.Lists mRegions;
    private DirectoryViewModel mViewModel;
    private int mRequestCode;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        mViewModel = new DirectoryViewModel(mContext, this);
    }

    @Test
    public void getRegions() throws ExecutionException, InterruptedException {
        mViewModel.getRegions();
        mRequestCode = getResponse();
        mRegions = (Region.Lists) mViewModel.getModel();

        assertNotNull(mRegions);
        assertTrue((mRegions.regions.size() > 0));
    }
}
