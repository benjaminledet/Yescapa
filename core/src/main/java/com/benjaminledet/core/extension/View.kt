package com.benjaminledet.core.extension

import android.view.View
import com.benjaminledet.core.utils.SafeClickListener

/**
 * Prevent double clicks
 */
fun View.onSafeClick(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}