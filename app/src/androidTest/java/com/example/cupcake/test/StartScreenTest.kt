package com.example.cupcake.test

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.data.DataSource
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Test

fun CupcakeOrderScreenTest() {
    val fakeOrderUiState = OrderUiState(
        quantity = 5,
        flavor = "Chacolate",
        date = "Thur Oct 21",
        price = "$50",
        pickupOptions = listOf()
    )

    @Test
    fun startOrderScreen_verifyContent() {

        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = { }
            )
        }
        DataSource.quantityOptions.forEach{
            composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
        }
            }
    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal ="$50"
   composeTestRule.setContent {
       SelectOptionScreen(
           subtotal = subtotal,
           options =flavors
       )
   }
        flavors.forEach{flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }
           composeTestRule.onNodeWithText(
               composeTestRule.activity.getString(
                   com.example.cupcake.R.string.subtotal_price,
                   subtotal
               )
           ).assertIsDisplayed()
                composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
                    .assertIsNotEnabled()
            }


@Test
    fun selectOptionScreen_optionSelected_NextButtonEnabled() {
    val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
    // And sub total
    val subTotal = "$100"
    composeTestRule.setContent {
        SelectOptionScreen(subtotal =subTotal , options =flavors )
    }
composeTestRule.onNodeWithText("Cookie").performClick()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).assertIsNotEnabled()
}
    @Test
    fun summaryScreen_verifyContentDisplay() {
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = fakeOrderUiState,
                onCancelButtonClicked = {  },
                onSendButtonClicked ={_,_ ->},
                onExitButtonClicked = { }
            )
        }
composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()
composeTestRule.onNodeWithText(
    composeTestRule.activity.getString(
        com.example.cupcake.R.string.subtotal_price,
        fakeOrderUiState.price
    )
).assertIsDisplayed()
    }
        }


