package com.example.mvvm_condosa.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_condosa.ui.account.ui.AccountScreen
import com.example.mvvm_condosa.ui.home.ui.HomeScreen
import com.example.mvvm_condosa.ui.settings.ui.SettingsSreen


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NavBar() {
    val navController = rememberNavController()
    val navigateAction = remember(navController) {
        MyAppNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectDestination = navBackStackEntry?.destination?.route?:MyAppRoute.HOME

    MyAppContent(
        navController = navController,
        selectedDestination = selectDestination,
        navigateTopLevelDestination = navigateAction::navigateTo
    )
}

@Composable
fun MyAppContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedDestination: String,
    navigateTopLevelDestination: (MyAppTopLevelDestination) -> Unit
) {
    Row(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = MyAppRoute.HOME
            ) {
                composable(MyAppRoute.HOME) {
                    HomeScreen()
                }
                composable(MyAppRoute.ACCOUNT) {
                    AccountScreen()
                }
                composable(MyAppRoute.SETTINGS) {
                    SettingsSreen()
                }
            }
            MyAppBottomNavigation(
                selectedDestination = selectedDestination,
                navigateTopLevelDestination = navigateTopLevelDestination
            )
        }
    }

}

@Composable
fun MyAppBottomNavigation(
    selectedDestination: String,
    navigateTopLevelDestination: (MyAppTopLevelDestination) -> Unit
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        TOP_LEVEL_DESTINATIONS.forEach { destinations ->
            NavigationBarItem(
                selected = selectedDestination == destinations.route,
                onClick = { navigateTopLevelDestination(destinations) },
                icon = {
                    Icon(
                        imageVector = destinations.selectedIcon,
                        contentDescription = stringResource(id = destinations.iconTextId)
                    )
                }
            )
        }
    }
}