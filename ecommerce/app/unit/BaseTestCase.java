package com.ecommerce.app.unit;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.ecommerce.app.viewmodels.BaseViewModel;
import com.ecommerce.common.net.HttpError;
import com.ecommerce.core.HttpCookieStore;
import com.ecommerce.core.RequestCodes;

import org.junit.Before;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BaseTestCase implements Future<Integer>,
        BaseViewModel.Listener {
    private int mRequestCode = RequestCodes.DEFAULT_REQUEST_CODE;

    Context mContext;

    @Before
    public void setUp() {
        mContext = InstrumentationRegistry.getContext();
        CookieManager cookieManager = new CookieManager(HttpCookieStore.Companion.getInstance(mContext),
                CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return (mRequestCode != RequestCodes.DEFAULT_REQUEST_CODE);
    }

    @Override
    public Integer get() throws InterruptedException, ExecutionException {
        return getResponse(0);
    }

    @Override
    public Integer get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return getResponse(TimeUnit.MILLISECONDS.convert(timeout, unit));
    }

    @Override
    public synchronized <T> void notifySuccess(int requestCode, T data) {
        mRequestCode = requestCode;
        notifyAll();
    }

    @Override
    public synchronized void notifyError(HttpError httpError) {
        mRequestCode = httpError.getRequestCode();
        notifyAll();
    }

    protected Integer getResponse(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return get(timeout, unit);
    }

    protected Integer getResponse() throws InterruptedException, ExecutionException {
        return get();
    }

    private synchronized int getResponse(long timeout)  throws InterruptedException {
        mRequestCode = RequestCodes.DEFAULT_REQUEST_CODE;
        if(timeout == 0) {
            wait(0);
        } else if(timeout > 0) {
            wait(timeout);
        }

        return mRequestCode;
    }
}
