package com.betulantep.kotlincountryagain.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName : String?,
    @SerializedName("capital")
    val countryCapital : String?,
    @SerializedName("currency")
    val countryCurrency : String?,
    @SerializedName("region")
    val countryRegion : String?,
    @SerializedName("language")
    val countryLanguage : String?,
    @SerializedName("flag")
    val countryImageUrl : String
)