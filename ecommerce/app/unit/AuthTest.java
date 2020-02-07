package com.ecommerce.app.unit;

import androidx.test.runner.AndroidJUnit4;

import com.ecommerce.app.viewmodels.AuthViewModel;

import com.ecommerce.core.RequestCodes;
import com.ecommerce.core.models.Account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AuthTest extends BaseTestCase {
    //private AuthDomain mAuth;
    private Account mAccountModel;
    private AuthViewModel mViewModel;
    private int mRequestCode;

    @Before
    @Override
    public void setUp() {
        super.setUp();

        mViewModel = new AuthViewModel(mContext, this);
    }

    @Test
    public void login() throws ExecutionException, InterruptedException {
        mViewModel.login("tester444@tester.com", "tester");
        mRequestCode = getResponse();

        mAccountModel = (Account) mViewModel.getModel();

        assertNotNull(mAccountModel);
        assertTrue(mAccountModel.success);
        assertEquals(mRequestCode, RequestCodes.AUTH_LOGIN_REQUEST_CODE);
    }

    @Test
    public void loginFacebook() throws ExecutionException, InterruptedException {
        mViewModel.loginFacebook("");
        mRequestCode = getResponse();

        mAccountModel = (Account) mViewModel.getModel();

        assertNotNull(mAccountModel);
        assertTrue(mAccountModel.success);
        assertEquals(mRequestCode, RequestCodes.AUTH_LOGIN_FACEBOOK_REQUEST_CODE);
    }
}
