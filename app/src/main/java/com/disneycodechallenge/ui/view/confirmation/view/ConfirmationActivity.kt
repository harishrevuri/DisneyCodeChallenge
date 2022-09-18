package com.disneycodechallenge.ui.view.confirmation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import com.disneycodechallenge.databinding.ActivityConfirmBinding
import com.disneycodechallenge.ui.view.confirmation.viewModel.ConfirmationViewModel

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var _activityConfirmationBinding: ActivityConfirmBinding
    private val confirmationVM: ConfirmationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityConfirmationBinding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(_activityConfirmationBinding.root)
        confirmationVM.parseBundle(intent.extras)
        observeVM()
    }

    private fun observeVM() {
        confirmationVM.getScreenTitle().observe(this) { (screenTitle, imageResource) ->
            if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                _activityConfirmationBinding.tvConfirmation.text = screenTitle
                _activityConfirmationBinding.ivStatus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        imageResource
                    )
                )
            }
        }
    }

}