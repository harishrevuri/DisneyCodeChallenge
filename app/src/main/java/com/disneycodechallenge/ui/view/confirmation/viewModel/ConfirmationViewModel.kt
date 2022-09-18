package com.disneycodechallenge.ui.view.confirmation.viewModel

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.disneycodechallenge.R

class ConfirmationViewModel(application: Application) : AndroidViewModel(application) {

    private var _guestsConflict = MutableLiveData<Pair<String, Int>>()

    fun getScreenTitle() = _guestsConflict

    fun parseBundle(extras: Bundle?) {
        extras?.let { bundleExtras ->
            val mixedContent =
                bundleExtras.getBoolean("mixedContent", false)
            if (mixedContent) {
                _guestsConflict.postValue("Conflict Screen" to R.drawable.ic_help)
            } else {
                _guestsConflict.postValue("Confirmation Screen" to R.drawable.ic_check)
            }
        }
    }
}