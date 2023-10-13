package com.example.kisileruygulamasi

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kisileruygulamasi.viewModel.AnasayfaViewModel
import com.example.kisileruygulamasi.viewModel.KayitSayfasiViewModel
import com.example.kisileruygulamasi.viewmodelfactory.AnasayfaViewModelFactory
import com.example.kisileruygulamasi.viewmodelfactory.KayitSayfasiViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KayitSayfasi() {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current
    val viewModel: KayitSayfasiViewModel = viewModel(
        factory = KayitSayfasiViewModelFactory(context.applicationContext as Application)
    )
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "KİŞİLER") }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.Blue,
                titleContentColor = Color.White
            )
        )
    }, content = {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(value = tfKisiAd.value, onValueChange = {tfKisiAd.value=it}, label = { Text(
                text = "Kişi Adını Giriniz"
            )})
            TextField(value = tfKisiTel.value, onValueChange ={tfKisiTel.value=it} , label = { Text(
                text = "Telefon Numarası Giriniz"
            )})
            Button(onClick = {
                val kisi_adi=tfKisiAd.value
                val kisi_tel=tfKisiTel.value
                viewModel.kayit(kisi_adi,kisi_tel)
                localFocusManager.clearFocus()
            }, modifier = Modifier.size(250.dp,50.dp)) {
                Text(text = "KAYDET")


            }
        }

    })

}
