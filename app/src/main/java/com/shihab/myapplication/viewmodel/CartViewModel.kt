package com.shihab.myapplication.viewmodel


import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shihab.myapplication.models.CartItem
import com.shihab.myapplication.models.Product
class CartViewModel : ViewModel() {

    val availableProducts = listOf(
        Product(1, "Wireless Mouse", 1000.0),
        Product(2, "Mechanical Keyboard", 3500.0),
        Product(3, "Gaming Monitor", 15000.0),
        Product(4, "Headphones", 2500.0)
    )

    val cartItems = mutableStateListOf<CartItem>()
    var discountInput by mutableStateOf("")

    val subTotal by derivedStateOf {
        cartItems.sumOf { it.product.price * it.quantity }
    }

    val vat by derivedStateOf {
        subTotal * 0.05
    }

    val isDiscountError by derivedStateOf {
        val discountAmount = discountInput.toDoubleOrNull() ?: 0.0
        discountAmount > subTotal
    }

    val validDiscount by derivedStateOf {
        if (isDiscountError) 0.0 else (discountInput.toDoubleOrNull() ?: 0.0)
    }

    val grandTotal by derivedStateOf {
        subTotal + vat - validDiscount
    }

    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            val index = cartItems.indexOf(existingItem)
            cartItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            cartItems.add(CartItem(product, 1))
        }
    }
}