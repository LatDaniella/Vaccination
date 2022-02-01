package com.example.vaccination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vaccination.databinding.ActivityVaccinationDetailBinding

class VaccinationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVaccinationDetailBinding

    companion object {
        val EXTRA_VACCINATION = "The vaccination"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // extract our Hero object from the intent
        val vaccination = intent.getParcelableExtra<VaccinationInfo>(EXTRA_VACCINATION)
        // put each field of the Hero object into the respective widgets
        binding.textViewDetailCountryName.text = vaccination?.country ?: "Generic Country"
        val lastDay = vaccination?.timeline?.lastKey()
        val lastDayNumber = vaccination?.timeline?.get(lastDay).toString()
        binding.textViewDetailNumOfVacctinations.text = lastDayNumber


    }
}
