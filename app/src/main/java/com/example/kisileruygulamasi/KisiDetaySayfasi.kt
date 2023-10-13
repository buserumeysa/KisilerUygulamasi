package com.example.kisileruygulamasi

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.viewModel.AnasayfaViewModel
import com.example.kisileruygulamasi.viewModel.DetaySayfasiViewModel
import com.example.kisileruygulamasi.viewmodelfactory.AnasayfaViewModelFactory
import com.example.kisileruygulamasi.viewmodelfactory.DetaySayfasiViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KisiDetaySayfasi(gelenKisiler: Kisiler) {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel: DetaySayfasiViewModel = viewModel(
        factory = DetaySayfasiViewModelFactory(context.applicationContext as Application)
    )
    val localFocusManager = LocalFocusManager.current
    LaunchedEffect(key1 = true ){
        tfKisiAd.value=gelenKisiler.kisi_adi
        tfKisiTel.value=gelenKisiler.kisi_tel
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Kişi Detay") }, colors = TopAppBarDefaults.smallTopAppBarColors(
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
            TextField(value = tfKisiAd.value, onValueChange = { tfKisiAd.value = it }, label = {
                Text(
                    text = "Kişi Adını Giriniz"
                )
            })
            TextField(value = tfKisiTel.value, onValueChange = { tfKisiTel.value = it }, label = {
                Text(
                    text = "Telefon Numarası Giriniz"
                )
            })
            Button(onClick = {
                val kisi_adi = tfKisiAd.value
                val kisi_tel = tfKisiTel.value
                viewModel.guncelle(gelenKisiler.kisi_id,kisi_adi,kisi_tel)

                localFocusManager.clearFocus()
            }, modifier = Modifier.size(250.dp, 50.dp)) {
                Text(text = "Güncelle")


            }
        }

    })

}
