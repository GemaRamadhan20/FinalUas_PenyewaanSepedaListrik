package com.example.finaluas.ui.halamanSepeda

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finaluas.R
import com.example.finaluas.model.EditViewModel
import com.example.finaluas.model.PenyediaViewModel
import com.example.finaluas.model.sepeda.SepedaEditViewModel
import com.example.finaluas.navigasi.DestinasiNavigasi
import com.example.finaluas.navigasi.PesananTopAppBar
import kotlinx.coroutines.launch


object SepedaEditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_sepeda
    const val sepedaIdArg = "itemId"
    val routeWithArgs = "$route/{$sepedaIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SepedaEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SepedaEditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            PesananTopAppBar(
                title = stringResource(SepedaEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntrySepedaBody(
            uiStateSepeda = viewModel.sepedaUiState,
            onSepedaValueChange = viewModel::updateUiState,
            onSaveClick = {
                // Note: If the user rotates the screen very fast, the operation may get cancelled
                // and the item may not be updated in the Database. This is because when config
                // change occurs, the Activity will be recreated and the rememberCoroutineScope will
                // be cancelled - since the scope is bound to composition.
                coroutineScope.launch {
                    viewModel.updateSepeda()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}