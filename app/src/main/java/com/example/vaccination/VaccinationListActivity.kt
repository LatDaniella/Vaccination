package com.example.vaccination

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vaccination.databinding.ActivityVaccinationListBinding

class VaccinationListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVaccinationListBinding
    lateinit var adapter: VaccinationAdapter

    companion object {
        val TAG = "VaccinationListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*
        // TODO: Load heroes.json into a List<Hero> using Gson
        val inputStream = resources.openRawResource(R.raw.heroes)
        val jsonString = inputStream.bufferedReader().use{
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<List<Hero>>(){ }.type
        val heroesList = gson.fromJson<List<Hero>>(jsonString, type)
        Log.d(TAG, "onCreate: \$heroesList")


 */
        val vaccineList = mutableListOf<Vaccination>()
        vaccineList.add(Vaccination("Fake 1",
            sortedMapOf<String, Int>(
                Pair("1/23/22", 100),
                Pair("1/24/22", 105),
                Pair("1/25/22", 110)
            )
        ))
        vaccineList.add(Vaccination("Fake 2",
            sortedMapOf<String, Int>(
                Pair("1/23/22", 50000),
                Pair("1/24/22", 60000),
                Pair("1/25/22", 70000)
            )
        ))

        //Create our adapter and fill the recycler view
        adapter = VaccinationAdapter(vaccineList)
        binding.recyclerViewVaccinationItem.adapter = adapter
        binding.recyclerViewVaccinationItem.layoutManager = LinearLayoutManager(this)


    }


}