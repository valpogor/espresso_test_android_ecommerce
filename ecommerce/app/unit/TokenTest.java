package com.ecommerce.app.unit;

import androidx.test.runner.AndroidJUnit4;
import com.ecommerce.app.viewmodels.TokenViewModel;
import com.ecommerce.core.models.Token;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class TokenTest extends BaseTestCase {
    private Token mTokenModel;
    private TokenViewModel mViewModel;
    private int mRequestCode;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        mViewModel = new TokenViewModel(mContext, this);
    }

    @Test
    public void getPaymentToken() throws ExecutionException, InterruptedException {
        mViewModel.getPaymentToken();
        mRequestCode = getResponse();
        mTokenModel = (Token) mViewModel.getModel();

        assertNotNull(mTokenModel);
        assertNotNull(mTokenModel.token);
    }
}
