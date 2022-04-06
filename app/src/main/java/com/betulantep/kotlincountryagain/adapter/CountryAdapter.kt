package com.betulantep.kotlincountryagain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.betulantep.kotlincountryagain.databinding.CountryRowBinding
import com.betulantep.kotlincountryagain.model.Country
import com.betulantep.kotlincountryagain.util.downloadFromUrl
import com.betulantep.kotlincountryagain.util.placeholderProgressBar
import com.betulantep.kotlincountryagain.view.FeedFragmentDirections

class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){
    class CountryViewHolder(val countryRowBinding: CountryRowBinding) : RecyclerView.ViewHolder(countryRowBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.countryRowBinding.name.text = countryList[position].countryName
        holder.countryRowBinding.capital.text = countryList[position].countryCapital

        holder.countryRowBinding.root.setOnClickListener {
            Navigation.findNavController(it).navigate(FeedFragmentDirections.actionFeedFragmentToCountryFragment())
        }

        holder.countryRowBinding.image.downloadFromUrl(countryList[position].countryImageUrl,
            placeholderProgressBar(holder.countryRowBinding.root.context))
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}