package com.example.kisileruygulamasi.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.repo.KisiDaoRepository

class DetaySayfasiViewModel:ViewModel() {
    var krepo= KisiDaoRepository()
    fun guncelle(kisiId:Int,kisiTel: String,kisiAdi: String){
        krepo.kisiGuncelle(kisiId,kisiTel,kisiAdi)

    }
}