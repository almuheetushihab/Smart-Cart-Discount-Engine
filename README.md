# Smart Cart Discount Engine 🛒

A modern Android application designed to simplify shopping cart management and real-time discount calculations. Built using Jetpack Compose and MVVM architecture.

## 🚀 Features

*   **Product Listing:** Browse a variety of available products with their names and prices.
*   **Add to Cart:** Easily add products to your cart. Adding the same product multiple times automatically updates its quantity.
*   **Real-time Calculation:** 
    *   **Sub-Total:** Instant calculation of the total price of all items in the cart.
    *   **VAT (5%):** Automatically calculates a 5% VAT on the sub-total.
    *   **Grand Total:** Displays the final price after adding VAT and subtracting any custom discounts.
*   **Custom Discount:** Users can manually input a discount amount.
*   **Error Handling:** Includes validation to prevent discounts from exceeding the sub-total, displaying a red warning message for invalid inputs.

## 🛠 Technology Stack

*   **Language:** Kotlin
*   **UI Framework:** Jetpack Compose (Modern Declarative UI)
*   **Architecture:** MVVM (Model-View-ViewModel)
*   **State Management:** Compose State & `derivedStateOf` for optimized performance.
*   **Components:** Material 3, LazyColumn, ViewModel.

## 📂 Project Structure

```text
com.shihab.myapplication
├── models          # Data Models (Product, CartItem)
├── viewmodel       # Business Logic & State Management (CartViewModel)
├── ui.screens      # User Interface (SmartCartScreen)
└── MainActivity    # App Entry Point
```

## ⚙️ How It Works

1.  **Data Management:** The `CartViewModel` manages the list of available products and the cart items using `mutableStateListOf`.
2.  **Reactive Updates:** By using `derivedStateOf`, the app automatically re-calculates the `Sub-Total`, `VAT`, and `Grand Total` whenever the cart changes or a discount is entered.
3.  **User Interface:** The UI is entirely reactive, meaning any state change in the ViewModel is immediately reflected on the screen without manual refreshes.

## 📸 Preview
You can view the look and feel of the `SmartCartScreen` directly in Android Studio using the **Compose Preview** feature.

---
Developed with ❤️ by **Shihab**
