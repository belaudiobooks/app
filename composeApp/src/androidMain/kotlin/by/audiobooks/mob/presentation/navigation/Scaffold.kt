package by.audiobooks.mob.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.audiobooks.mob.R


@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        //topBar = { Box(modifier = Modifier.fillMaxWidth().requiredHeight(20.dp)) { Text("Not sure about that panel =(") } }
    ) { paddingValues ->
        var selectedItemIndex by remember { mutableIntStateOf(1) }
        NavigationSuiteScaffold(
            modifier = Modifier.padding(paddingValues),
            navigationSuiteItems = {
                item(
                    selected = 1 == selectedItemIndex,
                    onClick = {
                        selectedItemIndex = 1
                        navHostController.navigate(Home)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = stringResource(R.string.scaffold_btn_home)
                        )
                    },
                    label = {
                        Text(stringResource(R.string.scaffold_btn_home))
                    }
                )
                item(
                    selected = 2 == selectedItemIndex,
                    onClick = {
                        selectedItemIndex = 2
                        navHostController.navigate(Catalog)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = stringResource(R.string.scaffold_btn_catalog)
                        )
                    },
                    label = {
                        Text(stringResource(R.string.scaffold_btn_catalog))
                    }
                )
                item(
                    selected = 3 == selectedItemIndex,
                    onClick = {
                        selectedItemIndex = 3
                        navHostController.navigate(Search)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.scaffold_btn_search)
                        )
                    },
                    label = {
                        Text(stringResource(R.string.scaffold_btn_search))
                    }
                )
            }
        ) {
            AppNavigation(
                modifier = modifier,
                navHostController = navHostController
            )
        }
    }
}
