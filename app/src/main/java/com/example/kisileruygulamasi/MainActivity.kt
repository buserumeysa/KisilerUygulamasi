package com.example.kisileruygulamasi

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.ui.theme.KisilerUygulamasiTheme
import com.example.kisileruygulamasi.viewModel.AnasayfaViewModel
import com.example.kisileruygulamasi.viewModel.DetaySayfasiViewModel
import com.example.kisileruygulamasi.viewmodelfactory.AnasayfaViewModelFactory
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KisilerUygulamasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SayfaGecisleri()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KisilerUygulamasiTheme {

    }
}

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa") {
        composable("anasayfa") {
            Anasayfa(navController = navController)
        }

        composable("kayit_sayfasi") {
            KayitSayfasi()
        }

        composable("detaySayfasi/{kisi}", arguments = listOf(
            navArgument("kisi") { type = NavType.StringType }
        )) {
            val json = it.arguments?.getString("kisi")
            val nesne = Gson().fromJson(json, Kisiler::class.java)
            KisiDetaySayfasi(gelenKisiler = nesne)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController) {
    val aramaKontrol = remember { mutableStateOf(false) }
    val aramaSonuc = remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel: AnasayfaViewModel = viewModel(
        factory = AnasayfaViewModelFactory(context.applicationContext as Application)
    )
    val kisiListesi = viewModel.kisilerListesi.observeAsState(listOf())
    LaunchedEffect(key1 = true ){
        viewModel.kisiYukle()
    }


    Scaffold(topBar = {
        TopAppBar(title = {
            if (aramaKontrol.value) {
                TextField(
                    value = aramaSonuc.value, onValueChange = {
                        aramaSonuc.value = it
                       viewModel.ara(it)
                    },
                    label = {
                        Text(text = "Ara")
                    }, colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        textColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                    )
                )

            } else {
                Text(text = "KİŞİLER")

            }
        }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White
        ), actions = {
            if (aramaKontrol.value) {

                IconButton(onClick = {
                    aramaKontrol.value = false
                    aramaSonuc.value = ""
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.kapatma_icon),
                        contentDescription = "",
                        tint = Color.White
                    )

                }

            } else {

                IconButton(onClick = { aramaKontrol.value = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arama_icon),
                        contentDescription = "",
                        tint = Color.White
                    )

                }

            }

        })
    }, content = {
        LazyColumn(modifier = Modifier.padding(vertical = 55.dp)) {
            items(
                count = kisiListesi.value!!.count(),
                itemContent = {
                    val kisi = kisiListesi.value!![it]
                    Card(
                        modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxSize()
                    ) {
                        Row(modifier = Modifier.clickable {
                            val kisiJson=Gson().toJson(kisi)
                            navController.navigate("detaySayfasi/${kisiJson}")

                        }) {
                            Row(
                                modifier = Modifier
                                    .padding(all = 10.dp)
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "${kisi.kisi_adi}-${kisi.kisi_tel}")
                                IconButton(onClick = { viewModel.sil(kisi.kisi_id) }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.silme_icon),
                                        contentDescription = "",
                                        tint = Color.Gray,
                                    )

                                }

                            }
                        }
                    }

                }
            )
        }

    },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("kayit_sayfasi")
            },
                containerColor = colorResource(id = R.color.teal_200),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ekleme_icon),
                        contentDescription = "",
                        tint = Color.White,
                    )
                })
        })

}

