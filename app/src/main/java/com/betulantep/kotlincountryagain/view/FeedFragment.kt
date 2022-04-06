package com.betulantep.kotlincountryagain.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.betulantep.kotlincountryagain.R
import com.betulantep.kotlincountryagain.adapter.CountryAdapter
import com.betulantep.kotlincountryagain.databinding.FragmentFeedBinding
import com.betulantep.kotlincountryagain.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.refreshData()
        binding.countryList.adapter = countryAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.apply {
                countryError.visibility = View.GONE
                countryLoading.visibility = View.VISIBLE
                countryList.visibility = View.GONE
                viewModel.refreshData()
                swipeRefreshLayout.isRefreshing = false
            }
        }
        observableLiveData()
    }

    private fun observableLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->
            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if(it){
                    binding.apply {
                        countryError.visibility = View.VISIBLE
                        countryList.visibility = View.GONE
                        countryLoading.visibility = View.GONE
                    }
                }else{
                    binding.countryError.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    binding.apply {
                        countryError.visibility = View.GONE
                        countryList.visibility = View.GONE
                        countryLoading.visibility = View.VISIBLE
                    }
                }else{
                    binding.countryLoading.visibility = View.GONE
                }
            }
        })
    }

}
