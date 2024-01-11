package com.example.finaluas.ui.halamanSepeda

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finaluas.R
import com.example.finaluas.data.Sepeda
import com.example.finaluas.model.PenyediaViewModel
import com.example.finaluas.model.sepeda.SepedaHomeViewModel
import com.example.finaluas.navigasi.DestinasiNavigasi
import com.example.finaluas.navigasi.PesananTopAppBar

object DestinasiHomeSepeda : DestinasiNavigasi {
    override val route = "home_sepeda"
    override val titleRes = R.string.app_name
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenSepeda(
    navigateToSepedaEntry: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: SepedaHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
            PesananTopAppBar(
                title = stringResource(id = DestinasiHomeSepeda.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                navigateUp = onBackClick
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToSepedaEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.entry_sepeda)
                )
            }
        }
    ) { innerPadding ->
        val uiStateSepeda by viewModel.homeUiState.collectAsState()
        BodyHomeSepeda(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            itemSepeda = uiStateSepeda.listSepeda,
            onSepedaClick = onDetailClick
        )
    }
}

@Composable
fun BodyHomeSepeda(
    modifier: Modifier,
    itemSepeda: List<Sepeda>,
    onSepedaClick: (Int) -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        if (itemSepeda.isEmpty()) {
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListSepeda(
                itemSepeda = itemSepeda,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onSepedaClick(it.id) }
            )
        }
    }
}

@Composable
fun ListSepeda(
    itemSepeda: List<Sepeda>,
    modifier: Modifier = Modifier,
    onItemClick: (Sepeda) -> Unit
) {
    LazyColumn(modifier = Modifier) {
        items(items = itemSepeda, key = { it.id }) { person ->
            com.example.finaluas.ui.halamanSepeda.DataSepeda(
                sepeda = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}

@Composable
fun DataSepeda(
    sepeda: Sepeda, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = sepeda.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null
                )
                Text(
                    text = sepeda.harga,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = sepeda.merk,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
