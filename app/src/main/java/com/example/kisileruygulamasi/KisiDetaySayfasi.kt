package com.example.kisileruygulamasi

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalFocusManager

import androidx.compose.ui.unit.dp
import com.example.kisileruygulamasi.entity.Kisiler

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KisiDetaySayfasi(gelenKisiler: Kisiler) {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    LaunchedEffect(key1 = true, ){
        tfKisiAd.value=gelenKisiler.kisiAdi
        tfKisiTel.value=gelenKisiler.kisiTel
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
                val kisiAdi = tfKisiAd.value
                val kisiTel = tfKisiTel.value
                Log.e("KisiGüncelle", "${gelenKisiler.kisiId}- $kisiAdi - $kisiTel")
                localFocusManager.clearFocus()
            }, modifier = Modifier.size(250.dp, 50.dp)) {
                Text(text = "Güncelle")


            }
        }

    })

}
