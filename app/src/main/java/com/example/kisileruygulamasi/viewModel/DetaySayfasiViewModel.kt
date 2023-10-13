package com.example.kisileruygulamasi.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.kisileruygulamasi.repo.KisiDaoRepository

class DetaySayfasiViewModel(application: Application): AndroidViewModel(application) {
    var krepo= KisiDaoRepository(application)
    fun guncelle(kisi_id:Int, kisi_tel: String, kisi_adi: String){
        krepo.kisiGuncelle(kisi_id,kisi_tel,kisi_adi)

    }
}