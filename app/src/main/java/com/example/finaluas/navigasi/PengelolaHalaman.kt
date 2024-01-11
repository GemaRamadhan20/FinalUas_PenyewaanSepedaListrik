package com.example.finaluas.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finaluas.R
import com.example.finaluas.ui.Home
import com.example.finaluas.ui.HomeScreenDisplay
import com.example.finaluas.ui.halamanPesanan.DestinasiEntryPesanan
import com.example.finaluas.ui.halamanPesanan.DestinasiHomePesanan
import com.example.finaluas.ui.halamanPesanan.DetailsPesananDestination
import com.example.finaluas.ui.halamanPesanan.EntryPesananScreen
import com.example.finaluas.ui.halamanPesanan.HomeScreenPesanan
import com.example.finaluas.ui.halamanPesanan.PesananDetailsScreen
import com.example.finaluas.ui.halamanPesanan.PesananEditDestination
import com.example.finaluas.ui.halamanPesanan.PesananEditScreen
import com.example.finaluas.ui.halamanSepeda.DestinasiEntrySepeda
import com.example.finaluas.ui.halamanSepeda.DestinasiHomeSepeda
import com.example.finaluas.ui.halamanSepeda.DetailsSepedaDestination
import com.example.finaluas.ui.halamanSepeda.EntrySepedaScreen
import com.example.finaluas.ui.halamanSepeda.HomeScreenSepeda
import com.example.finaluas.ui.halamanSepeda.SepedaDetailsScreen
import com.example.finaluas.ui.halamanSepeda.SepedaEditDestination
import com.example.finaluas.ui.halamanSepeda.SepedaEditScreen


@Composable
fun SepedaApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PesananTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier, scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back
                        )
                    )
                }
            }
        }
    )
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {NavHost(
    navController = navController,
    startDestination = Home.route,
    modifier = Modifier
) {
    composable(Home.route) {
        HomeScreenDisplay(
            modifier = modifier,
            navigateToPesanan = { navController.navigate(DestinasiHomePesanan.route) },
            navigateToSepeda = { navController.navigate(DestinasiHomeSepeda.route) }
        )
    }
    composable(DestinasiHomePesanan.route) {
        HomeScreenPesanan(
            navigateToItemEntry = { navController.navigate(DestinasiEntryPesanan.route) },
            onDetailClick = { pesananId -> navController.navigate("${DetailsPesananDestination.route}/$pesananId") },
            onBackClick = { navController.popBackStack() })
    }
    composable(DestinasiHomeSepeda.route) {
        HomeScreenSepeda(
            navigateToSepedaEntry = { navController.navigate(DestinasiEntrySepeda.route) },
            onDetailClick = { sepedaId -> navController.navigate("${DetailsSepedaDestination.route}/$sepedaId") },
            onBackClick = { navController.popBackStack() }
        )
    }
    composable(DestinasiEntryPesanan.route) {
        EntryPesananScreen(navigateBack = { navController.popBackStack() })
    }
    composable(DestinasiHomeSepeda.route) {
        EntrySepedaScreen(navigateBack = { navController.popBackStack() })
    }
    composable(
        DetailsPesananDestination.routeWithArgs,
        arguments = listOf(navArgument(DetailsPesananDestination.pesananIdArg) {
            type = NavType.IntType
        })
    ) { backStackEntry ->
        val pesananId =
            backStackEntry.arguments?.getInt(DetailsPesananDestination.pesananIdArg)
        pesananId?.let {
            PesananDetailsScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = { navController.navigate("${PesananEditDestination.route}/$it") }
            )
        }
    }
    composable(
        DetailsSepedaDestination.routeWithArgs,
        arguments = listOf(navArgument(DetailsSepedaDestination.sepedaIdArg) {
            type = NavType.IntType
        })
    ) { backStackEntry ->
        val sepedaId = backStackEntry.arguments?.getInt(DetailsSepedaDestination.sepedaIdArg)
        sepedaId?.let {
            SepedaDetailsScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = { navController.navigate("${SepedaEditDestination.route}/$it") }
            )
        }
    }
    composable(
        PesananEditDestination.routeWithArgs,
        arguments = listOf(navArgument(PesananEditDestination.itemIdArg) {
            type = NavType.IntType
        })
    ) {
        PesananEditScreen(
            navigateBack = { navController.popBackStack() },
            onNavigateUp = { navController.navigateUp() })
    }
    composable(
        SepedaEditDestination.routeWithArgs,
        arguments = listOf(navArgument(SepedaEditDestination.sepedaIdArg) {
            type = NavType.IntType
        })
    ) {
        SepedaEditScreen(
            navigateBack = { navController.popBackStack() },
            onNavigateUp = { navController.navigateUp() })
    }
}
}