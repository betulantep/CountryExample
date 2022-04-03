package com.betulantep.kotlincountryagain.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulantep.kotlincountryagain.model.Country

class CountryViewModel : ViewModel() {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country = Country("Turkey","Ankara","TRY","Asia","Turkish","www.ss.com")
        countryLiveData.value = country
    }
}