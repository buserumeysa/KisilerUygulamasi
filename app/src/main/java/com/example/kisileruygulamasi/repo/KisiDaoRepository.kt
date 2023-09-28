package com.example.kisileruygulamasi.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasi.entity.Kisiler

class KisiDaoRepository {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    init {
        kisilerListesi = MutableLiveData<List<Kisiler>>()
    }
    fun KisileriGetir():MutableLiveData<List<Kisiler>>{
        return kisilerListesi
        //livedatayı anasayfa viewmodel içerisindeki livedataya bağlar

    }

    fun TumKisileriAl() {
        val liste = mutableListOf<Kisiler>()
        val k1 = Kisiler(1, "Betül", "9453")
        val k2 = Kisiler(2, "Buse", "9124")

      liste.add(k1)
        liste.add(k2)
        kisilerListesi.value = liste
    }
    fun KisiAra(aramaKelimesi:String){
        Log.e("Kisi", aramaKelimesi)

    }
    fun KisiKayit(kisiAdi:String,kisiTel:String){
        Log.e("Kisi Kayit ", "$kisiAdi-$kisiTel")


    }
    fun kisiGuncelle(kisiId:Int,kisiTel: String,kisiAdi: String){
        Log.e("Kisi Güncelle ", "$kisiId-$kisiAdi-$kisiTel")

    }
    fun kisiSil(kisiId: Int){
        Log.e("Kisi Sil ", "$kisiId")

    }
}