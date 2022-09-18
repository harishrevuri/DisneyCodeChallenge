package com.disneycodechallenge.ui.utils

import com.disneycodechallenge.ui.view.main.model.GuestsModel

object DataUtils {

    fun getReservedGuests() = ArrayList<GuestsModel>().apply {
        add(GuestsModel("Ellen Gibson"))
        add(GuestsModel("Frank Gibson"))
        add(GuestsModel("Marie Gibson"))
        add(GuestsModel("Jerry Gibson"))
        add(GuestsModel("Gary Gibson"))
        add(GuestsModel("Frank Gibson"))
        add(GuestsModel("Gideon Gibson"))
    }

    fun getNeededReservationGuests() = ArrayList<GuestsModel>().apply {
        add(GuestsModel("Mark Gibson"))
        add(GuestsModel("Julie Gibson"))
        add(GuestsModel("Steve Gibson"))
        add(GuestsModel("Ken Gibson"))
        add(GuestsModel("Nicole Gibson"))
    }

}