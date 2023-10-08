package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@get:Before
@get:Rule
val composeTestRule = createAndroidComposeRule<ComponentActivity>()
private lateinit var navController: TestNavHostController

fun setupCupCakeNavHost(){
    composeTestRule.setContent {
      navController = TestNavHostController(LocalContext.current).apply {
          navigatorProvider.addNavigator(ComposeNavigator())
      }
        CupcakeApp(navController = navController)

    }
}

@Test
fun cupcakeNavHost_verifyStartDestination(){
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

@Test
fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen(){
    val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
    composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
}

@Test
fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen(){
composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake)
    .performClick()
    navController.navigate(CupcakeScreen.Flavor.name)
}
private fun navigateToFlavorScreen() {
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake)
        .performClick()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.chocolate)
        .performClick()
}
private fun navigateToPickupScreen() {
    navigateToFlavorScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
        .performClick()
}


private fun navigateToSummaryScreen(){
    navigateToSummaryScreen()
    composeTestRule.onNodeWithText(getFormattedDate())
        .performClick()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
        .performClick()
}

private fun performNavigateUp() {
    val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
    composeTestRule.onNodeWithContentDescription(backText).performClick()
}
private fun navigateToStartScreenWithBackButtonFromFlavor(){
navigateToFlavorScreen()
    performNavigateUp()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}
private fun navigateToStartScreenWithCancelButtonFromFlavor() {
navigateToFlavorScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

private fun flavorNavigateToPickupScreenDirectly() {
navigateToFlavorScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
}

private fun navigateToFlavorScreenWithUpButtonFromPickUpScreen() {
navigateToPickupScreen()
   performNavigateUp()
    navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)

}
private fun navigateToStartScreenWithCancelButtonFromFPickup() {
    navigateToPickupScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}
private fun navigateToSummaryNextButtonToFlavorScreen(){
    navigateToPickupScreen()
    composeTestRule.onNodeWithText(getFormattedDate())
        .performClick()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Summary.name)
}

private fun navigateToStartCancelButtonfromSummaryScreen() {
navigateToSummaryScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}