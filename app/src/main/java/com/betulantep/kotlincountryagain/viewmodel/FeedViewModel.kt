package com.betulantep.kotlincountryagain.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulantep.kotlincountryagain.model.Country
import com.betulantep.kotlincountryagain.services.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()



    fun refreshData(){
       /* val country1 = Country("Turkey","Ankara","TRY","Asia","Turkish","www.ss.com")
        val country2 = Country("France","Paris","EUR","Europe","French","www.ss.com")
        val country3 = Country("Germany","Berlin","EUR","Europe","German","www.ss.com")

        val countryList = arrayListOf<Country>(country1,country2,country3)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false*/
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        countryLoading.value = true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        countries.value = t
                        countryLoading.value = false
                        countryError.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryError.value = true
                        countryLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }
}