package com.ecommerce.app.unit;

import androidx.test.runner.AndroidJUnit4;
import com.ecommerce.app.viewmodels.SearchViewModel;
import com.ecommerce.core.models.Search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import androidx.test.runner.AndroidJUnit4;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SearchTest extends BaseTestCase {
    private Search mSearchModel;
    private SearchViewModel mViewModel;
    private int mRequestCode;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        mViewModel = new SearchViewModel(mContext, this);
    }

    @Test
    public void search() throws ExecutionException, InterruptedException {
        mViewModel.search("corn", "product");
        mRequestCode = getResponse();
        mSearchModel = (Search) mViewModel.getModel();

        assertNotNull(mSearchModel);
        assertTrue(mSearchModel.success);
        assertTrue((mSearchModel.products.size() > 0));
    }

    @Test
    public void searchAddress() throws ExecutionException, InterruptedException {
        mViewModel.searchAddress("91801");
        mRequestCode = getResponse();
        mSearchModel = (Search) mViewModel.getModel();

        assertNotNull(mSearchModel);
        assertNotNull(mSearchModel.address);
        //assertTrue(mSearchModel.success);
    }

    @Test
    public void searchSuggest() throws ExecutionException, InterruptedException {
        mViewModel.searchSuggest();
        mRequestCode = getResponse();
        mSearchModel = (Search) mViewModel.getModel();

        assertNotNull(mSearchModel);
        assertTrue(mSearchModel.success);
        assertTrue((mSearchModel.most_popular.size() > 0));
    }

    @Test
    public void searchSuggestTerms() throws ExecutionException, InterruptedException {
        mViewModel.searchSuggest("corn", "product");
        mRequestCode = getResponse();
        mSearchModel = (Search) mViewModel.getModel();

        assertNotNull(mSearchModel);
        assertTrue(mSearchModel.success);
        assertTrue((mSearchModel.terms.size() > 0));
    }
}
