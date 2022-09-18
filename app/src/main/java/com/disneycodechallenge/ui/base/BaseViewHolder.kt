package com.disneycodechallenge.ui.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(val itemBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    abstract fun bindData(position: Int, model: T, itemView: View, itemBinding: ViewDataBinding)
}