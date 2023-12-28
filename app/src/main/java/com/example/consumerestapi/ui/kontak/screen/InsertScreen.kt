package com.example.consumerestapi.ui.kontak.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.consumerestapi.navigation.DestinasiNavigasi
import com.example.consumerestapi.ui.kontak.viewmodel.InsertUiEvent
import com.example.consumerestapi.ui.kontak.viewmodel.InsertUiState
import com.example.consumerestapi.ui.kontak.viewmodel.InsertViewModel
import com.example.consumerestapi.ui.theme.PenyediaViewModel
import com.example.consumerestapi.ui.theme.TopAppBarKontak
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputSiswa(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChage: (InsertUiEvent) -> Unit = {},
    enable: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.nama,
            onValueChange ={onValueChage(insertUiEvent.copy(nama = it)) },
            label =  { Text(text = "Nama")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enable,
            singleLine = true
            )
        OutlinedTextField(
            value = insertUiEvent.email,
            onValueChange ={onValueChage(insertUiEvent.copy(email = it)) },
            label =  { Text(text = "Email")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enable,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.nohp,
            onValueChange ={onValueChage(insertUiEvent.copy(nohp = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label =  { Text(text = "No HP")},
            modifier = Modifier.fillMaxWidth(),
            enabled = enable,
            singleLine = true
        )
        if(enable){
            Text(
                text = "Isi semua data",
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )

    }
}

@Composable
fun EntryKontakBody(
    insertUiState: InsertUiState,
    onSiswaValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
    ){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInputSiswa(
            insertUiEvent =  insertUiState.insertUiEvent,
            onValueChage = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
            ){
                Text(text = "Simpan")
            }
        }
    }

object DestinasiEntry : DestinasiNavigasi{
    override val route = "item_entry"
    override val titleRes = "Entry Siswa"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKontakScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory),
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarKontak(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }) { innerPadding ->
        EntryKontakBody(
            insertUiState = viewModel.insertKontakState,
            onSiswaValueChange = viewModel::updateInsertKontakState,
            onSaveClick = {
                coroutineScope.launch{
                    viewModel.insertKontak()
                    navigateBack()
                }

            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }

}

