package com.shihab.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shihab.myapplication.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartCartScreen(viewModel: CartViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Store Products",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.availableProducts) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(product.name,
                                fontWeight = FontWeight.SemiBold)
                            Text("৳${product.price}",
                                color = Color.Gray)
                        }
                        Button(onClick = { viewModel.addToCart(product) }) {
                            Text("Add")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 2.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Cart Summary",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Sub-Total:")
            Text("৳${viewModel.subTotal}")
        }

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("VAT (5%):")
            Text("৳${viewModel.vat}")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.discountInput,
            onValueChange = { viewModel.discountInput = it },
            label = { Text("Custom Discount (৳)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = viewModel.isDiscountError,
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (viewModel.isDiscountError) {
                    Text("Discount cannot exceed Sub-Total!", color = Color.Red)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Grand Total:", fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "৳${viewModel.grandTotal}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Smart Cart Preview")
@Composable
fun SmartCartScreenPreview() {
    MaterialTheme {
        SmartCartScreen(viewModel = CartViewModel())
    }
}