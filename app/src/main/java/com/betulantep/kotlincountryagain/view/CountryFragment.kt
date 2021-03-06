package com.betulantep.kotlincountryagain.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.betulantep.kotlincountryagain.R
import com.betulantep.kotlincountryagain.databinding.FragmentCountryBinding
import com.betulantep.kotlincountryagain.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private var _binding : FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private var countryUuid = 0
    private lateinit var viewModel : CountryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCountryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        viewModel.getDataFromRoom()
        arguments?.let{
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        observableLiveData()
    }
    private fun observableLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.apply {
                    countryCapital.text = it.countryCapital
                    countryName.text = it.countryName
                    countryCurrency.text = it.countryCurrency
                    countryLanguage.text = it.countryLanguage
                    countryRegion.text = it.countryRegion
                }
            }

        })
    }
}