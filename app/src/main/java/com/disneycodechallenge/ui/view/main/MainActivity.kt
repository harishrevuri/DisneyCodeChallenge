package com.disneycodechallenge.ui.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import com.disneycodechallenge.databinding.ActivityMainBinding
import com.disneycodechallenge.ui.utils.*
import com.disneycodechallenge.ui.view.confirmation.view.ConfirmationActivity
import com.disneycodechallenge.ui.view.main.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainVM: MainViewModel by viewModels()
    private lateinit var _activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityMainBinding.root)

        bindUI()
        initializeVM()
        observeVM()
    }

    /**
     * Bind UI Components
     *
     */
    private fun bindUI() {
        _activityMainBinding.rvReservedGuests.adapter = mainVM.reservedGuestsAdapter
        _activityMainBinding.rvNeedReservationGuests.adapter = mainVM.needReservationGuestsAdapter

        bindClicks()
    }

    private fun bindClicks() {
        _activityMainBinding.ivClose.setOnClickListener {
            _activityMainBinding.clWarning.setInVisible()
            _activityMainBinding.btnContinue.setVisible()
        }
        _activityMainBinding.btnContinue.setOnClickListener { mainVM.buttonContinueClicked() }
        _activityMainBinding.layoutBaseToolbar.ivBack.setOnClickListener { finish() }
    }

    private fun initializeVM() {
        mainVM.initializeWithDummyArray()
    }

    /**
     * Observe ViewModel
     *
     */
    private fun observeVM() {
        observeForContinueButtonState()
        observeForWarningState()
        observeForInfoState()
        observeForNavigation()
    }

    /**
     * Observe for Info state
     *
     */
    private fun observeForInfoState() {
        mainVM.getInfoState().observe(this) { shouldEnable ->
            if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                shouldEnable?.let {
                    _activityMainBinding.tvInfo.setVisibleIfTrue { it }
                }
            }
        }
    }

    /**
     * Observe for Navigation
     *
     */
    private fun observeForNavigation() {
        mainVM.getNavigateIntent().observe(this) { intent ->
            if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                intent?.let {
                    startActivity(it)
                }
            }
        }
    }

    /**
     * Observe for warning state
     *
     */
    private fun observeForWarningState() {
        mainVM.getWarningState().observe(this) { shouldEnable ->
            if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                shouldEnable?.let {
                    _activityMainBinding.clWarning.setVisibleIfTrueOrInvisible { it }
                    _activityMainBinding.btnContinue.setVisibleIfTrue { it.not() }
                }
            }
        }
    }

    /**
     * Observe for continue button state
     *
     */
    private fun observeForContinueButtonState() {
        mainVM.getButtonState().observe(this) { shouldEnable ->
            if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                shouldEnable?.let {
                    _activityMainBinding.btnContinue.setIsClickable(it)
                }
            }
        }
    }
}