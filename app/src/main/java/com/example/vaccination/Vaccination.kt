package com.example.vaccination

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vaccination (
    val country: String,
    var timeline: Map<String, Int>
    ) : Parcelable, Comparable<Vaccination> {
    override fun compareTo(other: Vaccination): Map<String, Int> {
        return timeline
    }
}
