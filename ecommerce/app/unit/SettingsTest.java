package com.ecommerce.app.unit;


import androidx.test.runner.AndroidJUnit4;
import com.ecommerce.app.viewmodels.SettingsViewModel;
import com.ecommerce.core.models.Settings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SettingsTest extends BaseTestCase {
    private SettingsViewModel mViewModel;
    private Settings mSettingsModel;
    private int mRequestCode;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        mViewModel = new SettingsViewModel(mContext, this);
    }

    @Test
    public void getSettings() throws ExecutionException, InterruptedException {
        mViewModel.getSettings();
        mRequestCode = getResponse();
        mSettingsModel = (Settings) mViewModel.getModel();

        assertNotNull(mSettingsModel);
        assertTrue(mSettingsModel.success);
    }
}
