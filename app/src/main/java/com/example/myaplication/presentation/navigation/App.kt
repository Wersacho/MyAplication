package com.example.myaplication.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.myaplication.R
import com.example.myaplication.presentation.LoginScreenUi
import com.example.myaplication.presentation.Screens.AllCategoriesScreen
import com.example.myaplication.presentation.Screens.CartScreen
import com.example.myaplication.presentation.Screens.CheckoutScreen
import com.example.myaplication.presentation.Screens.EachProductDetailsScreenUi
import com.example.myaplication.presentation.Screens.GetAllFav
import com.example.myaplication.presentation.Screens.GetAllProducts
import com.example.myaplication.presentation.Screens.HomeScreenUi
import com.example.myaplication.presentation.Screens.ProfileScreenUi
import com.example.myaplication.presentation.SignUpScreen
import com.google.firebase.auth.FirebaseAuth


data class BottomNavItem(
    val name: String,
    val icon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun App(

    firebaseAuth: FirebaseAuth

){

    val navController = rememberNavController()

    var selectedItem by remember { mutableStateOf(0) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination?.route

    val shouldShowBottomBar = remember { mutableStateOf(false) }

    LaunchedEffect(currentDestination) {

        shouldShowBottomBar.value = when(currentDestination) {

            Routes.LoginScreen::class.qualifiedName, Routes.SignUpScreen::class.qualifiedName -> false
            else -> true

        }

    }

    val BottomNavItem = listOf(

        BottomNavItem("Главная", Icons.Default.Home, unselectedIcon = Icons.Outlined.Home),
        BottomNavItem("Избранное", Icons.Default.Favorite, unselectedIcon = Icons.Outlined.Favorite),
        BottomNavItem("Корзина", Icons.Default.ShoppingCart, unselectedIcon = Icons.Outlined.ShoppingCart),
        BottomNavItem("Профиль", Icons.Default.Person, unselectedIcon = Icons.Outlined.Person)

    )

    var  startScreen = if(firebaseAuth.currentUser == null) {

        SubNavigation.LoginSignUpScreen

    } else {

        SubNavigation.MainHomeScreen

    }

    Scaffold(

        bottomBar = {

            if (shouldShowBottomBar.value) {

                Surface (

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())

                ) {

                    AnimatedBottomBar (

                        selectedItem = selectedItem,
                        itemSize = BottomNavItem.size,
                        containerColor = Color.Transparent,
                        indicatorColor = colorResource(id = R.color.red),
                        indicatorDirection = IndicatorDirection.BOTTOM,
                        indicatorStyle = IndicatorStyle.FILLED

                    ) {

                        BottomNavItem.forEachIndexed { index, navigationItem ->

                            BottomBarItem(

                                selected = selectedItem == index,
                                onClick = {

                                    selectedItem = index
                                    when(index){

                                        0 -> navController.navigate(Routes.HomeScreen)
                                        1 -> navController.navigate(Routes.WishListScreen)
                                        2 -> navController.navigate(Routes.CartScreen)
                                        3 -> navController.navigate(Routes.ProfileScreen)

                                    }

                                },

                                imageVector = navigationItem.icon,
                                label = navigationItem.name,
                                containerColor = Color.Transparent

                            )

                        }

                    }

                }

            }

        }) { innerpadding ->

        Box(

            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (shouldShowBottomBar.value) 60.dp else 0.dp)

        ) {

            NavHost(navController = navController, startDestination = startScreen) {

                navigation<SubNavigation.LoginSignUpScreen>(startDestination = Routes.LoginScreen) {

                    composable<Routes.LoginScreen> {

                        LoginScreenUi(navController = navController)

                    }

                    composable<Routes.SignUpScreen> {

                        SignUpScreen(navController = navController)

                    }

                }

                navigation<SubNavigation.MainHomeScreen>(startDestination = Routes.HomeScreen) {

                    composable<Routes.HomeScreen> {

                        HomeScreenUi(navController = navController)

                    }

                    composable<Routes.ProfileScreen> {

                        ProfileScreenUi(navController = navController, firebaseAuth = firebaseAuth)

                    }

                    composable<Routes.WishListScreen> {

                        GetAllFav(navController = navController)

                    }

                    composable<Routes.CartScreen> {

                        CartScreen(navController = navController)

                    }

                    composable<Routes.SeeAllProductScreen> {

                        GetAllProducts(navController = navController)

                    }

                    composable<Routes.AllCategoriesScreen> {

                        AllCategoriesScreen(navController = navController)

                    }


                }

                composable<Routes.EachProductDetailsScreen> {

                    val product : Routes.EachProductDetailsScreen = it.toRoute()

                    EachProductDetailsScreenUi(navController = navController, productId = product.productID)

                }

                composable<Routes.EachCategoryItemsScreens> {

                    val category : Routes.EachCategoryItemsScreens = it.toRoute()

                    EachProductDetailsScreenUi(navController = navController, productId = category.categoryName)

                }

                composable<Routes.CheckoutScreen> {

                    val product : Routes.EachProductDetailsScreen = it.toRoute()

                    CheckoutScreen(productID = product.productID, navController = navController)

                }

            }


        }



    }

}