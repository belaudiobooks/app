package by.audiobooks.mob.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import by.audiobooks.mob.presentation.catalog.CatalogScreen
import by.audiobooks.mob.presentation.catalog.CategoryScreen
import by.audiobooks.mob.presentation.home.HomeScreen
import by.audiobooks.mob.presentation.search.SearchScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                navController = navHostController
            )
        }
        composable<Catalog> {
            CatalogScreen(
                navHostController = navHostController
            )
        }
        composable<Category>{
            val args = it.toRoute<Category>()
            CategoryScreen(
                tagId = args.tagId,
                navHostController = navHostController
            )
        }
        composable<Book>{
            val args = it.toRoute<Book>()
            Text("Book page ${args.bookUuid}")
        }
        composable<Person>{
            val args = it.toRoute<Person>()
            Text("Person page")
        }
        composable<Publisher>{
            val args = it.toRoute<Publisher>()
            Text("Publisher page")
        }
        composable<Search> {
            SearchScreen(
                navHostController = navHostController
            )
        }
    }
}

@Serializable
object Home

@Serializable
object Catalog

@Serializable
data class Category(
    val tagId: Long
)

@Serializable
data class Book(
    val bookUuid: String
)

@Serializable
data class Person(
    val personUuid: String
)

@Serializable
data class Publisher(
    val publisherUuid: String
)

@Serializable
object Search
