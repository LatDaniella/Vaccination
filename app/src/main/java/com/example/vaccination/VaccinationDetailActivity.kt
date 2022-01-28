package com.example.vaccination

import android.os.Bundle
import com.example.vaccination.databinding.ActivityVaccinationDetailBinding

class VaccinationDetailActivity {

    private lateinit var binding: ActivityVaccinationDetailBinding

    companion object{
        val EXTRA_COUNTRY = "country"
    }
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // extract our Hero object from the intent
        val vaccine = intent.getParcelableExtra<Vaccination>(EXTRA_COUNTRY)
        // put each field of the Hero object into the respective widgets
        binding.textViewDetailCountryName.text = vaccine?.name ?: "Generic Country"
        binding.textViewDetailCountryName.text = vaccine?.description ?: "Generic Description"



    }

 */





}