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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        val inputStream = resources.openRawResource(R.raw.heroes)
        val jsonString = inputStream.bufferedReader().use{
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<List<VaccinationInfo>>(){ }.type
        val heroesList = gson.fromJson<List<VaccinationInfo>>(jsonString, type)
        Log.d(TAG, "onCreate: \$vaccinationList")


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
                adapter = VaccinationAdapter(vaccineList)
                binding.recyclerViewVaccinationItem.adapter = adapter
                binding.recyclerViewVaccinationItem.layoutManager = LinearLayoutManager(this@VaccinationListActivity)
            }

            override fun onFailure(call: Call<List<VaccinationInfo>>, t: Throwable) {
                Log.d(TAG, "onFailure :${t.message}")
            }
        }
        )

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_sorting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.name_menu -> {
                Toast.makeText(this, "Hi, you clicked on name menu", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedBy { it.country }
                adapter.notifyDataSetChanged()
                // anytime the data set is changing / we change it, must notify the adapter
                true
            }

            R.id.totalVaccinated_menu -> {
                Toast.makeText(this, "Hi, you clicked on total vaccinated menu", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedBy{
                    val key = it.timeline.lastKey()
                    it.timeline[key]
                }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.largestIncrease_menu -> {
                Toast.makeText(this, "Hi, you clicked on largest increase in 10 menu", Toast.LENGTH_SHORT).show()
                adapter.dataSet = adapter.dataSet.sortedBy{
                    val firstKey = it.timeline.firstKey()
                    val lastKey = it.timeline.lastKey()
                    it.timeline[lastKey]!! - it.timeline[firstKey]!!
                }
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




}