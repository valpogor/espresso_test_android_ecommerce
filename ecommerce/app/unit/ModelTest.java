package com.ecommerce.app.unit;

import com.ecommerce.app.viewmodels.ModalViewModel;
import com.ecommerce.core.models.Modal;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertNotNull;

public class ModelTest extends BaseTestCase {
    private Modal mModal;
    private ModalViewModel mViewModel;
    private int mRequestCode;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        mViewModel = new ModalViewModel(mContext, this);
    }

    @Test
    public void getModal() throws ExecutionException, InterruptedException {
        mViewModel.getModal("welcome");
        mRequestCode = getResponse();
        mModal = (Modal) mViewModel.getModel();

        assertNotNull(mModal);
    }
}
