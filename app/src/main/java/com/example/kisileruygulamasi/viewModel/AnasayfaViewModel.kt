package com.example.kisileruygulamasi.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.repo.KisiDaoRepository
import com.example.kisileruygulamasi.room.Veritabani
import kotlinx.coroutines.launch

class AnasayfaViewModel(application: Application) : AndroidViewModel(application) {
    var krepo = KisiDaoRepository(application)
    var kisilerListesi = MutableLiveData<List<Kisiler>>()
   // var vt: Veritabani


    init {
        kisiYukle()
     //   kisilerListesi = MutableLiveData()
        kisilerListesi = krepo.KisileriGetir()
        // vt = Veritabani.veritabaniErisim(application)!!

    }

    fun kisiYukle() {
        krepo.tumKisileriAl()
    }

    fun ara(aramaKelimesi: String) {
        krepo.KisiAra(aramaKelimesi)

    }

    fun sil(kisi_id: Int) {
        krepo.kisiSil(kisi_id)

    }


}