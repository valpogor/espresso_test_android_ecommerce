package com.ecommerce.app.filter

import com.ecommerce.core.models.FilterOption
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Unit tests of the ProductListFilterViewModel.
 *
 * Tests should follow this naming structure:
 * UnitOfWork_StateUnderTest_ExpectedBehavior
 */
class ProductListFilterViewModelTest {
    @Test
    fun onFiltersLoaded_empty_shouldShowFilters() {
        val viewModel = createViewModel()
        val emptyFilterOptions = hashSetOf<FilterOption>()
        viewModel.onFiltersLoaded(
                filters = emptyFilterOptions
        )
        assert(viewModel.filters.containsAll(elements = emptyFilterOptions))
        assert(emptyFilterOptions.containsAll(elements = viewModel.filters))
    }

    @Test
    fun onFiltersLoaded_full_shouldShowFilters() {
        val viewModel = createViewModel()
        val multipleFilterOptions = listOf<FilterOption>(
                mock(FilterOption::class.java),
                mock(FilterOption::class.java),
                mock(FilterOption::class.java)
        )
        viewModel.onFiltersLoaded(
                filters = multipleFilterOptions
        )
        assert(viewModel.filters.containsAll(elements = multipleFilterOptions))
        assert(multipleFilterOptions.containsAll(elements = viewModel.filters))
    }

    @Test
    fun onFilterSelected_category_shouldShowCategoryOptions() {
        val viewModel = createViewModel()
        val filter = FilterOption()
        val option = FilterOption.Option()
        filter.key = "test"
        filter.options = listOf(element = option)
        val filters = listOf(element = filter)
        viewModel.onFiltersLoaded(
                filters = filters
        )
        viewModel.onFilterSelected(
                filter = filter
        )
    }

    private fun createViewModel(): ProductListFilterViewModel {
        return ProductListFilterViewModel(
                viewState = ProductListFilterViewState()
        )
    }
}