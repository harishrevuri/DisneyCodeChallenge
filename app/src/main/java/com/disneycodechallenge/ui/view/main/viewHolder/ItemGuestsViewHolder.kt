package com.disneycodechallenge.ui.view.main.viewHolder

import android.view.View
import androidx.databinding.ViewDataBinding
import com.disneycodechallenge.databinding.ItemGuestsBinding
import com.disneycodechallenge.ui.base.BaseViewHolder
import com.disneycodechallenge.ui.view.main.interfaces.IGuestCheckedCallBack
import com.disneycodechallenge.ui.view.main.model.GuestsModel

class ItemGuestsViewHolder(
    viewDataBinding: ViewDataBinding,
    private val callback: IGuestCheckedCallBack,
    private val type: Int
) : BaseViewHolder<GuestsModel>(viewDataBinding) {

    override fun bindData(
        position: Int,
        model: GuestsModel,
        itemView: View,
        itemBinding: ViewDataBinding
    ) {
        (itemBinding as ItemGuestsBinding).apply {
            dataModel = model
            itemBinding.cbGuest.setOnCheckedChangeListener { compoundButton, b ->
                if (compoundButton.isPressed) {
                    callback.onGuestChecked(b, position, type)
                }
            }
        }
    }
}