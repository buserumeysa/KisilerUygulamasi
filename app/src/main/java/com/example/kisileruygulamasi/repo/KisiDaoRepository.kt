package com.example.kisileruygulamasi.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KisiDaoRepository(var application: Application) {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()
    var vt: Veritabani


    init {
        kisilerListesi = MutableLiveData()
        vt = Veritabani.veritabaniErisim(application)!!

    }

    fun KisileriGetir(): MutableLiveData<List<Kisiler>> {
        return kisilerListesi
        //livedatayı anasayfa viewmodel içerisindeki livedataya bağlar

    }


     fun tumKisileriAl() {
         val job:Job = CoroutineScope(Dispatchers.Main).launch {
             kisilerListesi.value=vt.kisilerDao().tumKisiler()
         }
     }


    fun KisiAra(aramaKelimesi: String) {
        val job :Job= CoroutineScope(Dispatchers.Main).launch {
           kisilerListesi.value=vt.kisilerDao().kisiAra(aramaKelimesi)
        }
    }


   fun kisiKayit(kisi_adi:String ,kisi_tel: String)  {
       val job :Job= CoroutineScope(Dispatchers.Main).launch {
           val yeniKisi = Kisiler(0,kisi_adi,kisi_tel)
           vt.kisilerDao().kisiEkle(yeniKisi)
       }



    }

    fun kisiGuncelle(kisi_id: Int, kisi_adi: String, kisi_tel: String) {
        val job :Job= CoroutineScope(Dispatchers.Main).launch {
            val guncellenenKisi = Kisiler(kisi_id,kisi_adi,kisi_tel)
            vt.kisilerDao().kisiGuncelle(guncellenenKisi)
        }

    }

    fun kisiSil(kisi_id: Int) {
        val job :Job= CoroutineScope(Dispatchers.Main).launch {
            val kisiSil = Kisiler(kisi_id,"","")
            vt.kisilerDao().kisiSil(kisiSil)
        }


    }
}