package com.example.kisileruygulamasi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.repo.KisiDaoRepository

class AnasayfaViewModel:ViewModel() {
    var krepo=KisiDaoRepository()
    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    init {
        kisiYukle()
        kisilerListesi=MutableLiveData()
        kisilerListesi=krepo.KisileriGetir()
    }
    fun kisiYukle(){
        krepo.TumKisileriAl()
    }
    fun ara(aramaKelimesi:String){
        krepo.KisiAra(aramaKelimesi)

    }
    fun sil(kisiId:Int){
        krepo.kisiSil(kisiId )

    }


}