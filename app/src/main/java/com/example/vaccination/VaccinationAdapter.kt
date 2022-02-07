package com.example.vaccination

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class VaccinationAdapter(var dataSet: List<VaccinationInfo>) :
    RecyclerView.Adapter<VaccinationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCountry: TextView
        var textViewNumOfVaccinations: TextView
        val layout: ConstraintLayout


        init {
            // Define click listener for the ViewHolder's View.
            // view.findViewById looks for the id in the ViewHolder Class
            textViewCountry = view.findViewById(R.id.textView_vaccinationItem_country)
            textViewNumOfVaccinations = view.findViewById(R.id.textView_vaccinationItem_timeline)
            layout = view.findViewById(R.id.layout_vaccinationItem)

        }
    }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_vaccination, viewGroup, false)

            return ViewHolder(view)
        }


        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            // assigning all values (put on click listeners too
            val vaccine = dataSet[position]
            viewHolder.textViewCountry.text = vaccine.country
            viewHolder.textViewNumOfVaccinations.text = vaccine.timeline.toString()

            viewHolder.layout.setOnClickListener {
                // Toast.makeText(it.context, "Hi, you clicked on ${hero.name}", Toast.LENGTH_SHORT).show()
                //get context from something in viewHolder
                val context = viewHolder.layout.context
                //make the intent with context we got
                val vaccinationDetailIntent = Intent(context, VaccinationDetailActivity::class.java).apply {
                    putExtra(VaccinationDetailActivity.EXTRA_VACCINATION, vaccine)
                }
                context.startActivity(vaccinationDetailIntent)
            }

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}