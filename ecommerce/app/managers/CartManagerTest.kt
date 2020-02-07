package com.ecommerce.app.managers


import android.content.Context
import com.ecommerce.core.models.Product
import com.ecommerce.core.services.CartService
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.api.support.membermodification.MemberMatcher.constructor
import org.powermock.api.support.membermodification.MemberModifier.suppress
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox
import java.util.concurrent.ConcurrentHashMap
import org.powermock.api.support.membermodification.MemberMatcher.field
import com.android.volley.Response.success
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat


@RunWith(PowerMockRunner::class)
@PrepareForTest(CartManager::class)
class CartManagerTest {

    @Mock
    internal var context: Context? = null


    @Test //positive testing
    @Throws(Exception::class)
    fun hasOnlyGiftItemInCart_ReturnTrue() {
        //prevent calling CartService constructor in CartManager
        suppress(constructor(CartService::class.java))

        val mockCartManager = Whitebox.invokeConstructor(CartManager::class.java, context)

        //Assign mock object to instance variable in CartManager
        Whitebox.setInternalState(CartManager::class.java, "mInstance", mockCartManager)

        //Assign items to be tested member variable in CartManager
        Whitebox.setInternalState(mockCartManager, "mCartItems", cartItemsWithOnlyGiftCardItems)

        //Test that the input items have only gift card items in it
        val result = mockCartManager.hasOnlyGiftItemInCart()
        assertTrue(result)
    }

    @Test //negative testing
    fun hasOnlyGiftItemInCart_ReturnFalse() {
        //prevent calling CartService constructor in CartManager
        suppress(constructor(CartService::class.java))
        //Mock CartManager
        val mockCartManager = Whitebox.invokeConstructor(CartManager::class.java, context)
        Whitebox.setInternalState(CartManager::class.java, "mInstance", mockCartManager)
        Whitebox.setInternalState(mockCartManager, "mCartItems", cartItemsWithOtherItems)
        val result = mockCartManager.hasOnlyGiftItemInCart()
        assertThat("Checking that Cart items other has other items other than Gift Card ...returns false", result, `is`(false))
    }


    @Test
    fun isFreeGift_Returns_True() {
        //prevent calling CartService constructor in CartManager
        suppress(constructor(CartService::class.java))
        suppress(field(CartManager::class.java, "mCartItems"))
        val mockCartManager = Whitebox.invokeConstructor(CartManager::class.java, context)
        Whitebox.setInternalState(CartManager::class.java, "mInstance", mockCartManager)
        val result = mockCartManager.isFreeGift(productWithFreeGift)
        assertThat("Checking that Cart items has only Gift Card items ...returns true", result, `is`(true))
    }

    @Test
    fun isFreeSample_Returns_True() {
        //prevent calling CartService constructor in CartManager
        suppress(constructor(CartService::class.java))
        suppress(field(CartManager::class.java, "mCartItems"))
        val mockCartManager = Whitebox.invokeConstructor(CartManager::class.java, context)
        Whitebox.setInternalState(CartManager::class.java, "mInstance", mockCartManager)
        val result = mockCartManager.isFreeSample(productWithFreeSample)
        assertThat("Checking that Cart Items are Free Samples ...returns true", result, `is`(true))
    }

    @Test
    fun isWine_Returns_True() {
        //prevent calling CartService constructor in CartManager
        suppress(constructor(CartService::class.java))
        suppress(field(CartManager::class.java, "mCartItems"))
        val mockCartManager = Whitebox.invokeConstructor(CartManager::class.java, context)

        Whitebox.setInternalState(CartManager::class.java, "mInstance", mockCartManager)
        val result = mockCartManager.isWine(productWithWine)
        assertThat("Checking the Product is wine ...returns true", result, `is`(true))
    }


    private val productWithWine: Product
        get() {
            return Product().apply {
                this.page_layout = Product.PRODUCT_PAGE_LAYOUT_ADVANCED_BUNDLE_IDL
            }
        }

    private val productWithFreeSample: Product
        get() {
            return Product().apply {
                this.tc_product_type = Product.PRODUCT_TC_TYPE_SAMPLE
            }
        }

    private val productWithFreeGift: Product
        get() {
            return Product().apply {
                this.tc_product_type = Product.PRODUCT_TC_TYPE_GWP
                this.product_type = Product.PRODUCT_TYPE_SIMPLE
            }
        }

    private val productWithGiftCard: Product
        get() {
            return Product().apply {
                this.product_type = Product.PRODUCT_TYPE_GIFT_CARD
            }
        }

    private val productWithIcePack: Product
        get() {
            return Product().apply {
                this.sku = Product.PRODUCT_SKU_ICE_PACK
            }
        }


    //Cart with donations type and an amount (value)
    private val cartWithDonationItem: Cart
        get() {
            val totalList = arrayListOf<Total>()
            totalList.add(Total().apply {
                this.type = Total.TYPE_DONATION
                this.value = 3.4
            })
            return Cart().apply { this.totals = totalList }
        }

    //Cart with only Gift Card Items
    private val cartItemsWithOnlyGiftCardItems: Map<String, Product>
        get() {
            val map = ConcurrentHashMap<String, Product>()
            val product1 = Product()
            product1.product_type = Product.PRODUCT_TYPE_GIFT_CARD

            val product2 = Product()
            product2.product_type = Product.PRODUCT_TYPE_GIFT_CARD

            val product3 = Product()
            product3.product_type = Product.PRODUCT_TYPE_GIFT_CARD

            map["PRODUCT_ID-LINE_ITEM_ID_01"] = product1
            map["PRODUCT_ID-LINE_ITEM_ID_02"] = product2
            map["PRODUCT_ID-LINE_ITEM_ID_03"] = product3

            return map
        }

    //Cart with other Items other than a Gift Card Item
    private val cartItemsWithOtherItems: Map<String, Product>
        get() {
            val map = ConcurrentHashMap<String, Product>()
            val product1 = Product()
            product1.product_type = Product.PRODUCT_TYPE_GIFT_CARD

            val product2 = Product()
            product2.product_type = Product.PRODUCT_TYPE_CONFIGURABLE

            val product3 = Product()
            product3.product_type = Product.PRODUCT_TYPE_MEMBERSHIP

            map["PRODUCT_ID-LINE_ITEM_ID"] = product1
            map["PRODUCT_ID-OPTION_VALUE_ID"] = product2
            map["CART_ITEM_ID"] = product3

            return map
        }

}