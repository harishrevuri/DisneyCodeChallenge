package com.disneycodechallenge.ui.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.disneycodechallenge.databinding.ItemGuestsBinding
import com.disneycodechallenge.ui.base.BaseViewHolder
import com.disneycodechallenge.ui.view.main.interfaces.IGuestCheckedCallBack
import com.disneycodechallenge.ui.view.main.model.GuestsModel
import com.disneycodechallenge.ui.view.main.viewHolder.ItemGuestsViewHolder

class ReservedGuestsAdapter(private val callBack: IGuestCheckedCallBack, private val type: Int) : RecyclerView.Adapter<BaseViewHolder<Any>>() {

    private var arrayGuests = ArrayList<GuestsModel>()

    fun addItems(array: ArrayList<GuestsModel>) {
        arrayGuests = array
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> =
        ItemGuestsViewHolder(
            ItemGuestsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), callBack, type
        ) as BaseViewHolder<Any>

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bindData(
            position = position,
            model = arrayGuests[position],
            itemView = holder.itemView, itemBinding = holder.itemBinding
        )
    }

    override fun getItemCount() = arrayGuests.size
}