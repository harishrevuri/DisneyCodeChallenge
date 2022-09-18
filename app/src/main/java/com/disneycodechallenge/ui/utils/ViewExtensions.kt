package com.disneycodechallenge.ui.utils

import android.view.View

fun View?.setIsClickable(isClickable: Boolean) {
    this?.isEnabled = isClickable
    this?.isActivated = isClickable
    this?.isClickable = isClickable
}

fun View?.setVisibleIfTrue(condition: () -> Boolean) {
    this?.visibility = if (condition()) View.VISIBLE else View.GONE
}

fun View?.setVisibleIfTrueOrInvisible(condition: () -> Boolean) {
    this?.visibility = if (condition()) View.VISIBLE else View.INVISIBLE
}

fun View?.setVisible() {
    this?.visibility = View.VISIBLE
}

fun View?.setInVisible() {
    this?.visibility = View.INVISIBLE
}