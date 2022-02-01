package com.example.vaccination

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vaccination.databinding.ActivityVaccinationListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        var vaccineList = listOf<VaccinationInfo>()
        /*
        vaccineList.add(VaccinationInfo("Fake 1",
            sortedMapOf<String, Int>(
                Pair("1/23/22", 100),
                Pair("1/24/22", 105),
                Pair("1/25/22", 110)
            )
        ))
        vaccineList.add(VaccinationInfo("Fake 2",
            sortedMapOf<String, Int>(
                Pair("1/23/22", 50000),
                Pair("1/24/22", 60000),
                Pair("1/25/22", 70000)
            )
        ))

         */

        val vaccineApi = RetrofitHelper.getInstance().create(Covid19Service::class.java)
        val vaccineCall = vaccineApi.getVaccinations(10)
        // this is an asynchronous call

        // make sure that Callback is in retrofit
        // use implement members
        // it would be better to type "vaccineCall.enqueue(object : Callback<List<VaccinationInfo>> {} )"
        // because you would have to change a lot of things you make it personal to that code
        vaccineCall.enqueue(object : Callback<List<VaccinationInfo>> {
            override fun onResponse(
                call: Call<List<VaccinationInfo>>,
                response: Response<List<VaccinationInfo>>
            ) {
                // Here you know you got information back from the server
                // So any code that relies upon having this information
                // has to go here
                Log.d(TAG, "OnResponse: ${response.body()}")
                vaccineList = response.body() ?: listOf<VaccinationInfo>()
                val adapter = VaccinationAdapter(vaccineList)
                binding.recyclerViewVaccinationItem.adapter = adapter
                binding.recyclerViewVaccinationItem.layoutManager = LinearLayoutManager(this@VaccinationListActivity)
            }

            override fun onFailure(call: Call<List<VaccinationInfo>>, t: Throwable) {
                Log.d(TAG, "onFailure :${t.message}")
            }

        }
        )

    }


}