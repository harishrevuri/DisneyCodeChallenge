package com.disneycodechallenge.ui.view.main.viewModel

import android.app.Application
import android.content.Intent
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.disneycodechallenge.ui.utils.DataUtils
import com.disneycodechallenge.ui.view.confirmation.view.ConfirmationActivity
import com.disneycodechallenge.ui.view.main.adapter.ReservedGuestsAdapter
import com.disneycodechallenge.ui.view.main.interfaces.IGuestCheckedCallBack
import com.disneycodechallenge.ui.view.main.model.GuestsModel

class MainViewModel(application: Application) : AndroidViewModel(application),
    IGuestCheckedCallBack {

    private var _arrayReservedGuests = ArrayList<GuestsModel>()
    private var _arrayNeedReservationGuests = ArrayList<GuestsModel>()

    private var _buttonState = MutableLiveData<Boolean>()
    private var _infoState = MutableLiveData<Boolean>()
    private var _warningState = MutableLiveData<Boolean>()
    private var _navigateIntent = MutableLiveData<Intent>()

    fun getButtonState() = _buttonState
    fun getInfoState() = _infoState
    fun getWarningState() = _warningState
    fun getNavigateIntent() = _navigateIntent

    val reservedGuestsAdapter: ReservedGuestsAdapter by lazy {
        ReservedGuestsAdapter(this, 0)
    }

    val needReservationGuestsAdapter: ReservedGuestsAdapter by lazy {
        ReservedGuestsAdapter(this, 1)
    }

    /**
     * Initialize with dummy array
     *
     */
    fun initializeWithDummyArray() {
        _arrayReservedGuests = DataUtils.getReservedGuests()
        _arrayNeedReservationGuests = DataUtils.getNeededReservationGuests()

        reservedGuestsAdapter.addItems(_arrayReservedGuests)
        needReservationGuestsAdapter.addItems(_arrayNeedReservationGuests)
    }

    /**
     * On guest checked
     *
     * @param isChecked
     * @param position
     * @param type
     */
    override fun onGuestChecked(isChecked: Boolean, position: Int, type: Int) {
        if (type == 0) {
            _arrayReservedGuests[position].apply {
                guestChecked = isChecked
            }
            reservedGuestsAdapter.notifyItemChanged(position)
        } else {
            _arrayNeedReservationGuests[position].apply {
                guestChecked = isChecked
            }
            reservedGuestsAdapter.notifyItemChanged(position)
        }
        checkForButtonEnable()
    }

    /**
     * Check for button enable state & post the value for the same
     *
     */
    private fun checkForButtonEnable() {
        val (checkedReservedGuests, unCheckedReservedGuests) = getReservedUnReservedGuests()
        _buttonState.postValue(checkedReservedGuests.isNotEmpty() || unCheckedReservedGuests.isNotEmpty())
        _infoState.postValue(checkedReservedGuests.isEmpty())
        _warningState.postValue(false)
    }

    /**
     * Method to get Reserved & UnReserved
     *
     * @return
     */
    private fun getReservedUnReservedGuests(): Pair<List<GuestsModel>, List<GuestsModel>> {
        val checkedReservedGuests =
            _arrayReservedGuests.filter { guestsModel -> guestsModel.guestChecked }
        val unCheckedReservedGuests =
            _arrayNeedReservationGuests.filter { guestsModel -> guestsModel.guestChecked }
        return Pair(checkedReservedGuests, unCheckedReservedGuests)
    }

    /**
     * Method to check Guests selected are mixed or not.
     *
     * @return
     */
    private fun checkMixedContent(): Boolean {
        val (checkedReservedGuests, unCheckedReservedGuests) = getReservedUnReservedGuests()
        return checkedReservedGuests.isNotEmpty() && unCheckedReservedGuests.isNotEmpty()
    }

    private fun getBundleIntent() = bundleOf("mixedContent" to checkMixedContent())

    /**
     * Handle Continue Click
     *
     */
    fun buttonContinueClicked() {
        val (checkedReservedGuests, unCheckedReservedGuests) = getReservedUnReservedGuests()
        _warningState.postValue(checkedReservedGuests.isEmpty() && unCheckedReservedGuests.isNotEmpty())
        if (checkedReservedGuests.isEmpty() && unCheckedReservedGuests.isNotEmpty()) {
            // NO - OP
        } else {
            _navigateIntent.postValue(
                Intent(
                    getApplication(),
                    ConfirmationActivity::class.java
                ).apply {
                    putExtras(getBundleIntent())
                })
        }
    }
}