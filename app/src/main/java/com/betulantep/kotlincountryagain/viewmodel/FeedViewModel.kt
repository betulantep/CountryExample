package com.betulantep.kotlincountryagain.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulantep.kotlincountryagain.model.Country

class FeedViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country1 = Country("Turkey","Ankara","TRY","Asia","Turkish","www.ss.com")
        val country2 = Country("France","Paris","EUR","Europe","French","www.ss.com")
        val country3 = Country("Germany","Berlin","EUR","Europe","German","www.ss.com")

        val countryList = arrayListOf<Country>(country1,country2,country3)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }
}