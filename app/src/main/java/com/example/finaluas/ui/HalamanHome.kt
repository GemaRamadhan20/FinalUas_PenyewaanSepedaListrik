package com.example.finaluas.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.finaluas.R
import com.example.finaluas.navigasi.DestinasiNavigasi
import com.example.finaluas.navigasi.PesananTopAppBar

object Home : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenDisplay(
    modifier: Modifier,
    navigateToSepeda: () -> Unit,
    navigateToPesanan: () -> Unit
) {
    Scaffold(
        modifier = modifier, topBar = {
            PesananTopAppBar(title = stringResource(id = Home.titleRes), canNavigateBack = false)
        }
    ) { innerPadding ->
        HalamanHome(
            modifier = Modifier.padding(innerPadding),
            onPesananClicked = navigateToPesanan,
            onSepedaClicked = navigateToSepeda
        )
    }
}

@Composable
fun HalamanHome(
    modifier: Modifier,
    onPesananClicked: () -> Unit,
    onSepedaClicked: () -> Unit,
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Selamat Datang!")
        Text(text = "Aplikasi PAM")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick =  onSepedaClicked
            ) {
                Text(text = "Data Penyewa")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = onPesananClicked
            ) {
                Text(text = "Data Pesanan")
            }
        }
    }
}